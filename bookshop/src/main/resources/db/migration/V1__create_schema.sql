-- 1. Create Categories Table
CREATE TABLE categories (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(100) NOT NULL UNIQUE
);

-- 2. Create Users Table
CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(50) DEFAULT 'customer'
);

-- 3. Create Books Table
CREATE TABLE books (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       author VARCHAR(255) NOT NULL,
                       price DECIMAL(10, 2) NOT NULL,
                       stock INT DEFAULT 0,
                       description TEXT,
                       category_id BIGINT, -- Must match categories.id type
                       CONSTRAINT fk_book_category
                           FOREIGN KEY (category_id) REFERENCES categories(id)
                               ON DELETE SET NULL
);

-- 4. Create CartItems Table
CREATE TABLE cart_items (
                           user_id BIGINT, -- Must match users.id type
                           book_id BIGINT, -- Must match books.id type
                           quantity INT NOT NULL DEFAULT 1,
                           unit_price DECIMAL(10, 2) NOT NULL,
                           PRIMARY KEY (user_id, book_id),
                           CONSTRAINT fk_cart_user
                               FOREIGN KEY (user_id) REFERENCES users(id)
                                   ON DELETE CASCADE,
                           CONSTRAINT fk_cart_book
                               FOREIGN KEY (book_id) REFERENCES books(id)
                                   ON DELETE CASCADE
);