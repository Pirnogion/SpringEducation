package ru.gleb.FirstApp.exception;

import ru.gleb.FirstApp.model.ErrorCodes;
import ru.gleb.FirstApp.model.ErrorMessages;

public class ValidationFailedException extends ValidationServiceException {

    public ValidationFailedException(String message) {
        super(message, ErrorCodes.VALIDATION_EXCEPTION, ErrorMessages.VALIDATION);
    }
}
