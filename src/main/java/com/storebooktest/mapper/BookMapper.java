package com.storebooktest.mapper;

import com.storebooktest.dto.request.BookRequest;
import com.storebooktest.dto.request.BookUpdateRequest;
import com.storebooktest.entity.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book toBook(BookRequest bookRequest);
    Book toBook(BookUpdateRequest bookUpdateRequest);
}
