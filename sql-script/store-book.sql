create database `store-book`; 
use `store-book`; 

CREATE TABLE Books (
    id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    title NVARCHAR(255) NOT NULL,
    author NVARCHAR(255) NOT NULL,
    published_date DATE NOT NULL,
    isbn VARCHAR(13) UNIQUE NOT NULL,
    price DECIMAL(10 , 2 ) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


INSERT INTO Books (title, author, published_date, isbn, price)
VALUES
('The Great Gatsby', 'F. Scott Fitzgerald', '1925-04-10', '1234567890', 10.99),
('To Kill a Mockingbird', 'Harper Lee', '1960-07-11', '1234567891', 12.99),
('1984', 'George Orwell', '1949-06-08', '1234567892', 15.50),
('The Catcher in the Rye', 'J.D. Salinger', '1951-07-16', '1234567893', 14.25),
('Moby-Dick', 'Herman Melville', '1851-10-18', '1234567894', 18.75),
('Pride and Prejudice', 'Jane Austen', '1813-01-28', '1234567895', 9.99),
('War and Peace', 'Leo Tolstoy', '1869-01-01', '1234567896', 22.00),
('The Hobbit', 'J.R.R. Tolkien', '1937-09-21', '1234567897', 11.50),
('Crime and Punishment', 'Fyodor Dostoevsky', '1866-01-01', '1234567898', 13.99),
('The Odyssey', 'Homer', '0001-01-01', '1234567899', 17.40);