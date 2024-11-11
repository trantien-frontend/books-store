package com.storebooktest.ultils;


import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class PaginationUtils {
    public static final int DEFAULT_PAGE = 0;
    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final int DEFAULT_MAX_SIZE = 20;

    public PageRequest buildPageRequest(String _page, String _limit) {
        int page = parseInteger(_page, DEFAULT_PAGE);
        page = Math.max(DEFAULT_PAGE, page - 1);

        int limit = parseInteger(_limit, DEFAULT_PAGE_SIZE);
        limit = Math.min(Math.max(1, limit), DEFAULT_MAX_SIZE);

        return PageRequest.of(page, limit);
    }

    private Integer parseInteger(String data, int defaultNumber) {
        try {
            return Integer.parseInt(data);
        } catch (NumberFormatException exception) {
            return defaultNumber;
        }
    }
}
