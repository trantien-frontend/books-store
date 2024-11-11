package com.storebooktest.dto.request;

import com.storebooktest.validator.IsbnConstraint;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {
    @NotEmpty(message = "Title cannot be empty")
    @Size(max = 255, message = "Title cannot be longer than 255 characters")
    @Size(min = 10, message = "Title cannot be smaller than 10 characters")
    String title;

    @NotEmpty(message = "Author cannot be empty")
    @Size(max = 255, message = "Author cannot be longer than 255 characters")
    @Size(min = 5, message = "Author cannot be smaller than 5 characters")
    String author;

    @NotNull(message = "Published date is required")
    @PastOrPresent(message = "Published date cannot be in the future")
    LocalDate publishedDate;

    @NotEmpty(message = "isbn cannot be empty")
    @IsbnConstraint(message = "Invalid formate isbn")
    String isbn;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    BigDecimal price;
}
