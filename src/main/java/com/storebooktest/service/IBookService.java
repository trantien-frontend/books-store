package com.storebooktest.service;

import com.storebooktest.dto.request.BookRequest;
import com.storebooktest.dto.request.BookUpdateRequest;
import com.storebooktest.dto.response.PageDTO;
import com.storebooktest.entity.Book;
import org.springframework.data.domain.PageRequest;

public interface IBookService {
    PageDTO<Book> getBooks(PageRequest pageRequest);

    Book getBookById(long id);

    Book addBook(BookRequest book);

    Book updateBook(Long bookId, BookUpdateRequest bookUpdateRequest);
    void deleteBook (long bookId);
}
