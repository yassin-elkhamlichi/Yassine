-- 1. Create Categories Table
CREATE TABLE Categories (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(100) NOT NULL UNIQUE
);

-- 2. Create Users Table
CREATE TABLE Users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       passwordHash VARCHAR(255) NOT NULL,
                       role VARCHAR(50) DEFAULT 'customer'
);

-- 3. Create Books Table
CREATE TABLE Books (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       author VARCHAR(255) NOT NULL,
                       price DECIMAL(10, 2) NOT NULL,
                       stock INT DEFAULT 0,
                       description TEXT,
                       category_id BIGINT, -- Must match Categories.id type
                       CONSTRAINT fk_book_category
                           FOREIGN KEY (category_id) REFERENCES Categories(id)
                               ON DELETE SET NULL
);

-- 4. Create CartItems Table
CREATE TABLE CartItems (
                           user_id BIGINT, -- Must match Users.id type
                           book_id BIGINT, -- Must match Books.id type
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