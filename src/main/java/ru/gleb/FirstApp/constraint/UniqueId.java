package ru.gleb.FirstApp.constraint;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueIdValidator.class)
@Documented
public @interface UniqueId {

    String message() default "{firstapp.UniqueId.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
