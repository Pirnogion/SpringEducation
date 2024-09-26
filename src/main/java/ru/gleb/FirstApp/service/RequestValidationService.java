package ru.gleb.FirstApp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.gleb.FirstApp.exception.UnsupportedCodeException;
import ru.gleb.FirstApp.exception.ValidationFailedException;

import java.util.Objects;

@Slf4j
@Service
public class RequestValidationService implements ValidationService {

    @Override
    public void isValid(BindingResult bindingResult) throws ValidationFailedException, UnsupportedCodeException {

        var hasUniqueIdViolation = bindingResult.getFieldErrors().stream().anyMatch( (error) -> Objects.equals(error.getCode(), "UniqueId") );
        if (hasUniqueIdViolation) {
            log.error("Unsupported code error!");
            throw new UnsupportedCodeException(bindingResult.getFieldError().toString());
        }

        if (bindingResult.hasErrors()) {
            log.error("Validation error!");
            throw new ValidationFailedException(bindingResult.getFieldError().toString());
        }
    }
}
