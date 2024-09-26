package ru.gleb.FirstApp.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.gleb.FirstApp.exception.UnsupportedCodeException;
import ru.gleb.FirstApp.exception.ValidationFailedException;

import java.util.Objects;

@Service
public class RequestValidationService implements ValidationService {

    @Override
    public void isValid(BindingResult bindingResult) throws ValidationFailedException, UnsupportedCodeException {

        var hasUniqueIdViolation = bindingResult.getFieldErrors().stream().anyMatch( (error) -> Objects.equals(error.getCode(), "UniqueId") );
        if (hasUniqueIdViolation) {
            throw new UnsupportedCodeException(bindingResult.getFieldError().toString());
        }

        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult.getFieldError().toString());
        }
    }
}
