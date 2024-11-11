package com.storebooktest.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageDTO<T> {
    List<T> data;
    long totalElement;
    int currentPage;
    int pageSize;

    public PageDTO(Page<T> page) {
        data = page.getContent();
        totalElement = page.getTotalElements();
        currentPage = page.getPageable().getPageNumber() + 1;
        pageSize = page.getSize();
    }
}
