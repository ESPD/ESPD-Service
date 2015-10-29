package eu.europa.ec.grow.espd.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.oxm.MarshallingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by vigi on 10/29/15:11:55 AM.
 */
@ControllerAdvice
@Slf4j
class GlobalControllerExceptionHandler {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "MarshallingException occurred")
    @ExceptionHandler(MarshallingException.class)
    void handleMarshallingException(MarshallingException e) {
        log.error("MarshallingException handler executed.", e);
        //returning 400 error code
    }

}
