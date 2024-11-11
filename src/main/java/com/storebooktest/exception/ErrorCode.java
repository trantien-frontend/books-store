package com.storebooktest.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    DATABASE_ACCESS_EXCEPTION(101, "Database access error occurred", HttpStatus.INTERNAL_SERVER_ERROR),
    BOOK_NOT_FOUND(102, "Book not found with given id", HttpStatus.NOT_FOUND),
    INVALID_PARAMETER(103, "Invalid Parameter", HttpStatus.BAD_REQUEST),
    ISBN_EXISTED(104, "Isbn existed", HttpStatus.BAD_REQUEST),
    BOOK_NOT_EXISTED(105, "Book not existed", HttpStatus.NOT_FOUND);

    int code;
    String message;
    HttpStatus statusCode;
}
