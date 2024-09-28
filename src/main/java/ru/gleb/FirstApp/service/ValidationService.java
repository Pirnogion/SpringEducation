package ru.gleb.FirstApp.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.gleb.FirstApp.exception.UnsupportedCodeException;
import ru.gleb.FirstApp.exception.ValidationFailedException;

@Service
public interface ValidationService {

    void isValid(BindingResult bindingResult) throws ValidationFailedException, UnsupportedCodeException;
}
