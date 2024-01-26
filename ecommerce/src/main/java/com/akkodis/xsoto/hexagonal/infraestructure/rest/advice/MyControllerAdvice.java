package com.akkodis.xsoto.hexagonal.infraestructure.rest.advice;

import com.akkodis.xsoto.hexagonal.infraestructure.constant.PricesInfraestructureConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.akkodis.xsoto.hexagonal.infraestructure.adapter.exception.PricesException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyControllerAdvice {
    private static final Logger logger = LoggerFactory.getLogger(MyControllerAdvice.class);

    @ExceptionHandler(PricesException.class)
    public ResponseEntity<String> handleEmptyInput(PricesException emptyInputException) {
        logException(emptyInputException);
        return new ResponseEntity<String>(emptyInputException.getErrorMessage(), emptyInputException.getErrorCode());
    }

    private void logException(PricesException e) {
        logger.error(PricesInfraestructureConstant.ErrorMessages.ERROR_DETECTED, e.getMessage(), e);
    }

}
