CREATE TABLE Categories (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(100) NOT NULL UNIQUE
);


CREATE TABLE Users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       passwordHash VARCHAR(255) NOT NULL,
                       role VARCHAR(50) DEFAULT 'customer'
);

CREATE TABLE Books (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       author VARCHAR(255) NOT NULL,
                       price DECIMAL(10, 2) NOT NULL,
                       stock INT DEFAULT 0,
                       description TEXT,
                       category_id INT,
                       CONSTRAINT fk_book_category
                           FOREIGN KEY (category_id) REFERENCES Categories(id)
                               ON DELETE SET NULL
);

CREATE TABLE CartItems (
                           user_id INT,
                           book_id INT,
                           quantity INT NOT NULL DEFAULT 1,
                           unitPrice DECIMAL(10, 2) NOT NULL,
                           PRIMARY KEY (user_id, book_id),
                           CONSTRAINT fk_cart_user
                               FOREIGN KEY (user_id) REFERENCES Users(id)
                                   ON DELETE CASCADE,
                           CONSTRAINT fk_cart_book
                               FOREIGN KEY (book_id) REFERENCES Books(id)
                                   ON DELETE CASCADE
);