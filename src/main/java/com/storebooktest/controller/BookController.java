package com.storebooktest.controller;

import com.storebooktest.dto.request.BookRequest;
import com.storebooktest.dto.request.BookUpdateRequest;
import com.storebooktest.dto.response.ApiResponse;
import com.storebooktest.dto.response.PageDTO;
import com.storebooktest.entity.Book;
import com.storebooktest.service.IBookService;
import com.storebooktest.ultils.PaginationUtils;
import com.storebooktest.ultils.ResponseUtil;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping(path = "/books")
@Validated
public class BookController {
    IBookService bookService;
    PaginationUtils paginationUtils;

    @GetMapping
    public ResponseEntity<ApiResponse<PageDTO<Book>>> getBooks(@RequestParam(required = false, defaultValue = "0") String page, @RequestParam(required = false, defaultValue = "10") String limit) {
        log.info("Start method getBooks in BookController with page={}, limit={}", page, limit);
        PageRequest pageRequest = paginationUtils.buildPageRequest(page, limit);
        PageDTO<Book> data = bookService.getBooks(pageRequest);
        log.info("Successfully fetched books. Total pages: {}", data.getTotalElement());
        return ResponseUtil.responseSuccess(data, null);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<ApiResponse<Book>> getBookById(@PathVariable Long bookId) {
        log.info("Fetching book with id: {}", bookId);
        Book book = bookService.getBookById(bookId);
        return ResponseUtil.responseSuccess(book, null);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Book>> addBook(@RequestBody @Valid BookRequest bookRequest) {
        log.info("Adding new book with title: {}", bookRequest.getTitle());
        Book book = bookService.addBook(bookRequest);
        log.info("New book added with ID: {}", book.getId());
        return ResponseUtil.responseSuccess(book, "Add new book success");
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<ApiResponse<Book>> updateBook(@PathVariable Long bookId, @Valid @RequestBody BookUpdateRequest bookUpdateRequest) {
        log.info("Updating book with id: {}", bookId);
        Book book = bookService.updateBook(bookId, bookUpdateRequest);
        log.info("Book updated successfully with id: {}", book.getId());
        return ResponseUtil.responseSuccess(book, "Update book success");
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<ApiResponse<Void>> deleteBook(@PathVariable Long bookId) {
        log.info("Deleting book with id: {}", bookId);
        bookService.deleteBook(bookId);
        log.info("Book with id {} deleted successfully", bookId);
        return ResponseUtil.responseSuccess("Delete book success");
    }
}
