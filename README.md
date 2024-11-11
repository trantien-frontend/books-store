# Book Management API

This is a simple CRUD API for managing books built using Spring Boot and MySQL. The API supports the following operations:
- Create a new book
- Retrieve a book's details by ID
- Retrieve a list of all books
- Update book details
- Delete a book by ID
It includes proper validation, error handling, and uses a MySQL database.

## Table of Contents
1. [Technologies](#technologies)
2. [Setup Instructions](#setup-instructions)
3. [API Endpoints](#api-endpoints)
4. [Validation and Error Handling](#validation-and-error-handling)
5. [Project Structure](#project-structure)

   
# Technologies
- Backend Framework: Spring Boot
- Database: MySQL
- Programming Language: Java
  
# Setup Instructions
Prerequisites
Ensure you have the following installed:

- Java 11 or higher
- MySQL Database
- Maven (or Gradle, if you prefer)
- IDE (IntelliJ IDEA, Eclipse, or Visual Studio Code)

1. Clone the Repository
```bash
   https://github.com/trantien-frontend/books-store.git
   cd book-management-api
```
2. Set Up the Database
   2.1 Install MySQL and start the MySQL service if it's not already running.
   2.2 Create the database (e.g., bookstore)
   ``` sql
   create database `bookstore`; 
   use `bookstore`;
   ```
   2.3 Run the SQL script
   ``` sql
    CREATE TABLE Books (
    id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    title NVARCHAR(255) NOT NULL,
    author NVARCHAR(255) NOT NULL,
    published_date DATE NOT NULL,
    isbn VARCHAR(13) UNIQUE NOT NULL,
    price DECIMAL(10 , 2 ) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ); 
   
    alter table Books
    add constraint check_price_book Check (price > 0);

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
   ```
4. Configure Database Connection
``` yaml
    Server:
      servlet:
        context-path: /api/v1
      port: 8080

    spring:
      datasource:
        username: your-db-username
        password: your-db-password
        url: jdbc:mysql://localhost:3306/bookstore
        driver-class-name: com.mysql.cj.jdbc.Driver
      jpa:
        hibernate:
          ddl-auto: update
        show-sql: true
        database-platform: org.hibernate.dialect.MySQLDialect

    springdoc:
      swagger-ui:
        path: /swagger-ui.html
```
5. Install Dependencies
``` Bash
    mvn clean install 
```
6. Run the Application
``` Bash
  mvn spring-boot:run
```
# API Endpoints
Here are the available API endpoints for CRUD operations
1. Create a Book
- POST: /api/v1/books
- Request body: 
``` json
  {
    "code": 1000,
    "message": "Add new book success",
    "body": {
        "id": 1,
        "title": "The Great Gatsby",
        "author": "F. Scott Fitzgerald",
        "publishedDate": "1925-04-10",
        "isbn": "9780743273565",
        "price": 10.5,
        "createAt": "2024-11-11T15:19:07.0411735",
        "updatedAt": "2024-11-11T15:19:07.0411735"
    }
}
```
2. Retrieve a Book by ID
- GET: /api/v1/books/{id}
- Request body:
  ``` json
  {
      "code": 1000,
      "body": {
          "id": 1,
          "title": "The Great Gatsby",
          "author": "F. Scott Fitzgerald",
          "publishedDate": "1925-04-10",
          "isbn": "9780743273565",
          "price": 10.50,
          "createAt": "2024-11-11T15:19:07",
          "updatedAt": "2024-11-11T15:19:07"
  }
  ```
3. Get All Books
- GET: /api/v1/books
* page (optinal): By default, the page is 1 if no parameter is provided
* limit (optional): By default, the page is 5 if no parameter is provided
- Request body:
``` json
  {
    "code": 1000,
    "body": {
        "data": [
            {
                "id": 1,
                "title": "The Great Gatsby",
                "author": "F. Scott Fitzgerald",
                "publishedDate": "1925-04-10",
                "isbn": "9780743273565",
                "price": 10.50,
                "createAt": "2024-11-11T15:19:07",
                "updatedAt": "2024-11-11T15:19:07"
            }
        ],
        "totalElement": 1,
        "currentPage": 1,
        "pageSize": 10
    }
}
```
4. Update a Book
- PUT: /api/v1/books/{id}
- Request body:
``` json 
  {
    "code": 1000,
    "message": "Update book success",
    "body": {
        "id": 1,
        "title": "The  Gatsby",
        "author": "F. Scott Fitzgerald",
        "publishedDate": "1925-04-10",
        "isbn": "9780743273565",
        "price": 10.50,
        "createAt": "2024-11-11T15:19:07",
        "updatedAt": "2024-11-11T16:31:47.9757292"
    }
}
```
5. Delete a Book
- DEL: /api/v1/books/{id}
- Request body:
``` json
{
    "code": 1000,
    "message": "Delete book success"
}
```
# Validation and Error Handling
- ISBN is validated to ensure it's a valid format and is unique.
- Price must be a positive value.
- All required fields are validated for presence.
Example of error response:
``` json
{
    "code": 103,
    "message": "Invalid Parameter",
    "body": {
        "isbn": "Invalid formate isbn",
        "title": "Title cannot be smaller than 10 characters",
        "publishedDate": "Published date cannot be in the future",
        "author": "Author cannot be empty"
    }
}
```
# Project Structure
``` Bash
├───store-book-test
  ├───src
│   ├───main
│   │   ├───java
│   │   │   └───com
│   │   │       └───storebooktest
│   │   │           ├───controller
│   │   │           ├───dto
│   │   │           │   ├───request
│   │   │           │   └───response
│   │   │           ├───entity
│   │   │           ├───exception
│   │   │           ├───mapper
│   │   │           ├───repository
│   │   │           ├───service
│   │   │           │   └───impl
│   │   │           ├───ultils
│   │   │           └───validator
│   │   └───resources
│   │       ├───static
│   │       └───templates
│   └───test
│       └───java
│           └───com
│               └───storebooktest
└───target
    ├───classes
    │   └───com
    │       └───storebooktest
    │           ├───controller
    │           ├───dto
    │           │   ├───request
    │           │   └───response
    │           ├───entity
    │           ├───exception
    │           ├───mapper
    │           ├───repository
    │           ├───service
    │           │   └───impl
    │           ├───ultils
    │           └───validator
    └───generated-sources
        └───annotations
            └───com
                └───storebooktest
                    └───mapper
```
