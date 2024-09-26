package ru.gleb.FirstApp.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.gleb.FirstApp.exception.UnsupportedCodeException;
import ru.gleb.FirstApp.exception.ValidationFailedException;
import ru.gleb.FirstApp.model.Request;
import ru.gleb.FirstApp.model.Response;
import ru.gleb.FirstApp.service.ValidationService;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class L2Controller {

    private final ValidationService validationService;

    @Autowired
    public L2Controller(ValidationService validationService) {
        this.validationService = validationService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request, BindingResult bindingResult) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        Response response = Response.builder()
            .uid(request.getUid())
            .operationUid(request.getOperationUid())
            .systemTime(simpleDateFormat.format(new Date()))
            .code("success")
            .errorCode("")
            .errorMessage("")
            .build();

        try {
            validationService.isValid(bindingResult);
        } catch (ValidationFailedException exception) {
            response.setCode("failed");
            response.setErrorCode("ValidationException");
            response.setErrorMessage("Ошибка валидации");

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (UnsupportedCodeException exception) {
            response.setCode("failed");
            response.setErrorCode("UnsupportedException");
            response.setErrorMessage(String.format("[%s] Некорректное значение поля 'uid'", response.getErrorCode()));

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception exception) {
            response.setCode("failed");
            response.setErrorCode("UnknownException");
            response.setErrorMessage("Произошла непредвиденная ошибка");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
