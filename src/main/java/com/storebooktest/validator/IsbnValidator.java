package com.storebooktest.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

public class IsbnValidator implements ConstraintValidator<IsbnConstraint, String> {

    @Override
    public void initialize(IsbnConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (Objects.isNull(value)) return false;
        String isbn = value.replaceAll("-", "");

        if (isbn.length() == 13) {
            return isValidISBN13(isbn);
        }
        if (isbn.length() == 10) {
            return isValidISBN10(isbn);
        }
        return false;
    }

    private boolean isValidISBN13(String isbn) {
        int sum = 0;
        for (int i = 0; i < 12; i++) {
            int digit = Character.getNumericValue(isbn.charAt(i));
            sum += (i % 2 == 0) ? digit : digit * 3;
        }
        int checkDigit = Character.getNumericValue(isbn.charAt(12));
        return (sum + checkDigit) % 10 == 0;
    }

    private boolean isValidISBN10(String isbn) {
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            int digit = Character.getNumericValue(isbn.charAt(i));
            sum += digit * (10 - i);
        }
        char checkChar = isbn.charAt(9);
        int checkDigit = (checkChar == 'X') ? 10 : Character.getNumericValue(checkChar);
        return (sum + checkDigit) % 11 == 0;
    }
}
