package com.boilerplate.demo.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistsColumnValidator.class)
@Documented
public @interface ExistsColumn {

    Class<?> entityClass();

    String columnName();

    String message() default "Invalid foreign key";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
