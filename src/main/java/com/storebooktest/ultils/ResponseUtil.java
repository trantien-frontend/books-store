package com.storebooktest.ultils;

import com.storebooktest.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {
    public static <T> ResponseEntity<ApiResponse<T>> responseSuccess(T data, String message) {
        return new ResponseEntity<>(ApiResponse.<T>builder().body(data).message(message).build(), HttpStatus.OK);
    }

    public static  ResponseEntity<ApiResponse<Void>> responseSuccess(String message) {
        return new ResponseEntity<>(ApiResponse.<Void>builder().message(message).build(), HttpStatus.OK);
    }
}
