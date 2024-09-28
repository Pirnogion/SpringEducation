package ru.gleb.FirstApp.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.gleb.FirstApp.exception.UnsupportedCodeException;
import ru.gleb.FirstApp.exception.ValidationFailedException;
import ru.gleb.FirstApp.model.*;
import ru.gleb.FirstApp.service.ModifyRequestService;
import ru.gleb.FirstApp.service.ModifyResponseService;
import ru.gleb.FirstApp.service.ValidationService;
import ru.gleb.FirstApp.util.DateTimeUtils;

import java.util.Date;

@Slf4j
@RestController
public class L2Controller {

    private final ValidationService validationService;
    private final ModifyResponseService modifyResponseService;
    private final ModifyRequestService modifyRequestService;

    @Autowired
    public L2Controller(
        ValidationService validationService,
        @Qualifier("ModifySystemTimeResponseService") ModifyResponseService modifyResponseService,
        @Qualifier("ModifySourceRequestService") ModifyRequestService modifyRequestService
    ) {
        this.validationService = validationService;
        this.modifyResponseService = modifyResponseService;
        this.modifyRequestService = modifyRequestService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request, BindingResult bindingResult) {
        log.info("request: {}", request);

        Response response = Response.builder()
            .uid(request.getUid())
            .operationUid(request.getOperationUid())
            .systemTime(DateTimeUtils.getCustomFormat().format(new Date()))
            .code(Codes.SUCCESS)
            .errorCode(ErrorCodes.EMPTY)
            .errorMessage(ErrorMessages.EMPTY)
            .build();

        try {
            validationService.isValid(bindingResult);
        } catch (ValidationFailedException exception) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
            response.setErrorMessage(ErrorMessages.VALIDATION);

            log.info("response: {}", response);

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (UnsupportedCodeException exception) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNSUPPORTED_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNSUPPORTED);

            log.info("response: {}", response);

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception exception) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNKNOWN);

            log.info("response: {}", response);

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        modifyResponseService.modify(response);
        modifyRequestService.modify(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
