package ru.gleb.FirstApp.exception;

import ru.gleb.FirstApp.model.ErrorCodes;
import ru.gleb.FirstApp.model.ErrorMessages;

public class UnsupportedCodeException extends ValidationServiceException {

    public UnsupportedCodeException(String message) {
        super(message, ErrorCodes.UNSUPPORTED_EXCEPTION, ErrorMessages.UNSUPPORTED);
    }
}
