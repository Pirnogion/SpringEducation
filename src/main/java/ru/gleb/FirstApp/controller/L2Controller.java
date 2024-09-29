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
import ru.gleb.FirstApp.exception.ValidationServiceException;
import ru.gleb.FirstApp.model.*;
import ru.gleb.FirstApp.service.*;
import ru.gleb.FirstApp.util.DateTimeUtils;

import java.util.Date;

@Slf4j
@RestController
public class L2Controller {

    private final ValidationService validationService;
    private final ModifyResponseService modifyResponseService;
    private final ModifyRequestService modifyRequestService;
    private final AnnualBonusService annualBonusService;
    private final QuarterlyBonusService quarterlyBonusService;

    @Autowired
    public L2Controller(
        ValidationService validationService,
        @Qualifier("ModifySystemTimeResponseService") ModifyResponseService modifyResponseService,
        @Qualifier("ModifySourceRequestService") ModifyRequestService modifyRequestService,
        AnnualBonusService annualBonusService,
        QuarterlyBonusService quarterlyBonusService
    ) {
        this.validationService = validationService;
        this.modifyResponseService = modifyResponseService;
        this.modifyRequestService = modifyRequestService;
        this.annualBonusService = annualBonusService;
        this.quarterlyBonusService = quarterlyBonusService;
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
        } catch (ValidationServiceException exception) {
            response.setCode(exception.getCode());
            response.setErrorCode(exception.getErrorCode());
            response.setErrorMessage(exception.getErrorMessage());

            log.info("response: {}", response);

            return new ResponseEntity<>(response, exception.getHttpStatus());
        } catch (Exception exception) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNKNOWN);

            log.info("response: {}", response);

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setAnnualBonus(annualBonusService.calculate(request.getPosition(), request.getSalary(), request.getBonus(), request.getWorkDays()));
        response.setQuarterlyBonus(quarterlyBonusService.calculate(request.getPosition(), request.getSalary(), request.getBonus(), request.getWorkDays()));

        modifyResponseService.modify(response);
        modifyRequestService.modify(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
