package org.jordosi.vacationpaymentcalculator.advice;

import lombok.extern.slf4j.Slf4j;
import org.jordosi.vacationpaymentcalculator.model.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.format.DateTimeParseException;
import java.util.HashMap;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * Global exception handler for Vacation Payment Calculator API.
 * Handles validation errors, date parsing exceptions and other unexpected errors
 * @author Jordosi
 * @version 0.0.1
 *
 * @see org.jordosi.vacationpaymentcalculator.controller.VacationPaymentController
 */
@Slf4j
@ControllerAdvice
public class VacationPaymentControllerAdvice {
    /**
     * Handles validation and binding errors (e.g. @Valid failures)
     * @param ex the BindException containing validation errors
     * @return ResponseEntity with error details (HTTP 400)
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(BindException ex) {
        var errorMessage = "Validation and Binding error";
        var errors = new HashMap<String, Object>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        if (ex.getCause() instanceof DateTimeParseException) {
            errors.put("startVacationDate", "Invalid date format. Expected: yyyy-MM-dd");
        }

        log.error(errorMessage + ": ", ex);
        var errorResponse = ErrorResponse.builder()
                .errorCode(BAD_REQUEST.value())
                .errorMessage(errorMessage)
                .errors(errors)
                .build();

        return ResponseEntity.badRequest().body(errorResponse);
    }

    /**
     * Handles all other unexpected errors.
     * @param ex the caught exception
     * @return ResponseEntity with error (HTTP 500)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        return ResponseEntity.internalServerError().body(
                new ErrorResponse(INTERNAL_SERVER_ERROR.value(), ex.getMessage(), null)
        );
    }
}