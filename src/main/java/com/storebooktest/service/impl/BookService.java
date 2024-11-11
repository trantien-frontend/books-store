package com.storebooktest.service.impl;

import com.storebooktest.dto.request.BookRequest;
import com.storebooktest.dto.request.BookUpdateRequest;
import com.storebooktest.dto.response.PageDTO;
import com.storebooktest.entity.Book;
import com.storebooktest.exception.AppException;
import com.storebooktest.exception.ErrorCode;
import com.storebooktest.mapper.BookMapper;
import com.storebooktest.repository.IBookRepository;
import com.storebooktest.service.IBookService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BookService implements IBookService {
    IBookRepository bookRepository;
    BookMapper bookMapper;

    /**
     * @param pageRequest The pagination infomation, including page number and size.
     * @Return a {@Link PageDTO} containing a list of {@Link Book}objects corresponding
     * to the requested page.
     */
    @Override
    public PageDTO<Book> getBooks(PageRequest pageRequest) {
        log.info("Getting books with page {} and size {}", pageRequest.getPageNumber(), pageRequest.getPageSize());
        Page<Book> page = bookRepository.findAll(pageRequest);
        log.info("Successfully retrieved {} books out of {} total",
                page.getNumberOfElements(),
                page.getTotalElements());
        return new PageDTO<>(page);
    }

    @Override
    public Book getBookById(long id) {
        log.info("Fetching book with ID: {}", id);
        Book book = bookRepository.findById(id).orElseThrow(() -> {
            log.error("Book not found by ID: {}", id);
            throw new AppException(ErrorCode.BOOK_NOT_FOUND);
        });
        log.info("Book found: {}", book.getId());
        return book;
    }

    @Override
    public Book addBook(BookRequest bookRequest) {
        log.info("Attempting to add a new book with ISBN: {}", bookRequest.getIsbn());
        boolean isExitsIsbn = bookRepository.existsByIsbn(bookRequest.getIsbn());
        if (isExitsIsbn) {
            log.error("Isbn {} already existed", bookRequest.getIsbn());
            throw new AppException(ErrorCode.ISBN_EXISTED);
        }
        Book book = bookMapper.toBook(bookRequest);
        log.info("Book with ISBN {} added successfully.", book.getIsbn());
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long bookId, BookUpdateRequest bookUpdateRequest) {
        log.info("Starting to update book with ID: {}", bookId);
        Book existingBook = bookRepository.findById(bookId).orElseThrow(() -> {
            log.error("Book with ID {} not found for update.", bookId);
            return new AppException(ErrorCode.BOOK_NOT_FOUND);
        });

        existingBook.setAuthor(bookUpdateRequest.getAuthor());
        existingBook.setPrice(bookUpdateRequest.getPrice());
        existingBook.setTitle(bookUpdateRequest.getTitle());
        existingBook.setPublishedDate(bookUpdateRequest.getPublishedDate());
        log.info("Book with ID {} updated successfully.", bookId);
        return bookRepository.save(existingBook);
    }

    @Override
    public void deleteBook(long bookId) {
        log.info("Starting to delete book with ID: {}", bookId);
        boolean isExitsBook = bookRepository.existsById(bookId);
        if (!isExitsBook) {
            throw new AppException(ErrorCode.BOOK_NOT_FOUND);
        }
        bookRepository.deleteById(bookId);
        log.info("Book with ID {} deleted successfully.", bookId);
    }
}
