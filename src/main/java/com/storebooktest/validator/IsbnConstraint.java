package com.storebooktest.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = {IsbnValidator.class})
public @interface IsbnConstraint {
    String message() default "Invalid ISBN";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
