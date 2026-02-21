INSERT INTO Categories (name)
VALUES ('Fiction'),
       ('Science Fiction'),
       ('Biography'),
       ('Technology'),
       ('History');
INSERT INTO Users (email, passwordHash, role)
VALUES ('alice.smith@email.com', '11111111', 'USER'),
       ('bob.jones@email.com', '11111111', 'USER'),
       ('admin.user@bookshop.com', '11111111', 'ADMIN'),
       ('charlie.brown@email.com', '11111111...', 'USER'),
       ('diana.prince@email.com', '11111111', 'USER');
INSERT INTO Books (title, author, price, stock, description, category_id)
VALUES ('The Great Gatsby', 'F. Scott Fitzgerald', 15.99, 50, 'A classic tale of the Roaring Twenties.', 1),
       ('Dune', 'Frank Herbert', 22.50, 30, 'An epic science fiction masterpiece set on Arrakis.', 2),
       ('Steve Jobs', 'Walter Isaacson', 18.00, 15, 'The authorized self-titled biography of Steve Jobs.', 3),
       ('Clean Code', 'Robert C. Martin', 45.00, 25, 'A handbook of agile software craftsmanship.', 4),
       ('Sapiens', 'Yuval Noah Harari', 20.00, 40, 'A brief history of humankind.', 5);
