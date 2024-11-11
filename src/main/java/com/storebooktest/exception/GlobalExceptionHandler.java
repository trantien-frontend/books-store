package com.storebooktest.exception;

import com.storebooktest.dto.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiResponse> handleRuntimeException(RuntimeException runtimeException) {
        log.error("RuntimeException: ", runtimeException);
        return new ResponseEntity<>(ApiResponse.builder().code(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode()).message(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage()).build(), ErrorCode.UNCATEGORIZED_EXCEPTION.getStatusCode());
    }

    @ExceptionHandler(value = DataAccessException.class)
    public ResponseEntity<ApiResponse> handleDatabaseAccessException(DataAccessException dataAccessException) {
        log.error("DatabaseAccessException: ", dataAccessException);
        return new ResponseEntity<>(ApiResponse.builder().code(ErrorCode.DATABASE_ACCESS_EXCEPTION.getCode()).message(ErrorCode.DATABASE_ACCESS_EXCEPTION.getMessage()).build(), ErrorCode.DATABASE_ACCESS_EXCEPTION.getStatusCode());
    }

    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<ApiResponse> handleAppException(AppException appException) {
        ErrorCode errorCode = appException.getErrorCode();
        return new ResponseEntity<>(ApiResponse.builder().message(errorCode.getMessage()).code(errorCode.getCode()).build(), errorCode.getStatusCode());
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        log.error("MethodArgumentTypeMismatchException", exception.getMessage());
        return new ResponseEntity<>(ApiResponse.builder().code(ErrorCode.INVALID_PARAMETER.getCode()).message(ErrorCode.INVALID_PARAMETER.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new LinkedHashMap<>();
        BindingResult bindingResult = exception.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        fieldErrors.forEach(fieldError -> errors.put(fieldError.getField(), fieldError.getDefaultMessage()));
        return new ResponseEntity<>(ApiResponse.builder().body(errors).code(ErrorCode.INVALID_PARAMETER.getCode()).message(ErrorCode.INVALID_PARAMETER.getMessage()).build(), ErrorCode.INVALID_PARAMETER.getStatusCode());
    }
}
