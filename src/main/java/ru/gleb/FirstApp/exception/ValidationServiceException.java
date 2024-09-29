package ru.gleb.FirstApp.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import ru.gleb.FirstApp.model.Codes;
import ru.gleb.FirstApp.model.ErrorCodes;
import ru.gleb.FirstApp.model.ErrorMessages;

@Getter
public class ValidationServiceException extends Exception {
    private final ErrorCodes errorCode;
    private final ErrorMessages errorMessage;
    private final HttpStatus httpStatus;
    private final Codes code;

    public ValidationServiceException(String message, ErrorCodes errorCode, ErrorMessages errorMessage) {
        super(message);

        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.code = Codes.FAILED;
    }

    public ValidationServiceException(String message, ErrorCodes errorCode, ErrorMessages errorMessage, HttpStatus httpStatus, Codes code) {
        super(message);

        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
        this.code = code;
    }
}
