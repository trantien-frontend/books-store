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

alter table Books
add constraint check_price_book Check (price > 0);

INSERT INTO Books (title, author, published_date, isbn, price)
VALUES 
('The Great Gatsby', 'F. Scott Fitzgerald', '1925-04-10', '9780743273565', 10.99),
('To Kill a Mockingbird', 'Harper Lee', '1960-07-11', '9780061120084', 12.99);
