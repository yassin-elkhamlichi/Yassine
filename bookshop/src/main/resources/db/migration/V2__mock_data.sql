-- 1. Insert Categories
INSERT INTO Categories (name)
VALUES ('Fiction'),
       ('Science Fiction'),
       ('Biography'),
       ('Technology'),
       ('History');

-- 2. Insert Users
INSERT INTO Users (email, passwordHash, role)
VALUES ('alice.smith@email.com', '11111111', 'CUSTOMER'),
       ('bob.jones@email.com', '11111111', 'CUSTOMER'),
       ('admin.user@bookshop.com', '11111111', 'ADMIN'),
       ('charlie.brown@email.com', '11111111', 'CUSTOMER'),
       ('diana.prince@email.com', '11111111', 'CUSTOMER');

-- 3. Insert Books using Subqueries for category_id
INSERT INTO Books (title, author, price, stock, description, category_id)
VALUES ('The Great Gatsby', 'F. Scott Fitzgerald', 15.99, 50, 'A classic tale of the Roaring Twenties.',
        (SELECT id FROM Categories WHERE name = 'Fiction')),
       ('Dune', 'Frank Herbert', 22.50, 30, 'An epic science fiction masterpiece set on Arrakis.',
        (SELECT id FROM Categories WHERE name = 'Science Fiction')),
       ('Steve Jobs', 'Walter Isaacson', 18.00, 15, 'The authorized self-titled biography of Steve Jobs.',
        (SELECT id FROM Categories WHERE name = 'Biography')),
       ('Clean Code', 'Robert C. Martin', 45.00, 25, 'A handbook of agile software craftsmanship.',
        (SELECT id FROM Categories WHERE name = 'Technology')),
       ('Sapiens', 'Yuval Noah Harari', 20.00, 40, 'A brief history of humankind.',
        (SELECT id FROM Categories WHERE name = 'History'));