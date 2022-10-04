package dev.skaringa.fizzbuzz.configuration.web;


import dev.skaringa.fizzbuzz.api.Error;
import dev.skaringa.fizzbuzz.api.ErrorCode;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Set;

@Log4j2
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({IllegalArgumentException.class})
    protected ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException ex) {
        logErrorWithStackTrace(ex);
        Set<Error> errors = toErrors(ex);
        return ResponseEntity.status(resolveStatus(errors)).body(errors);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        logErrorWithStackTrace(ex);
        Set<Error> errors = toErrors(ex);
        return ResponseEntity.status(resolveStatus(errors)).body(errors);
    }

    private void logErrorWithStackTrace(Exception ex) {
        log.error("An error occurred", ex);
    }

    private Set<Error> toErrors(Exception ex) {
        if (ex instanceof IllegalArgumentException) {
            return Set.of(toIllegalArgumentExceptionError(ex));
        } else if (ex instanceof ConstraintViolationException) {
            return Set.of(toConstraintViolationExceptionError(ex));
        }

        return Set.of(toUnexpectedError(ex));
    }

    private Error toIllegalArgumentExceptionError(Exception ex) {
        return Error.system(ErrorCode.ILLEGAL_ARGUMENT, ex.getMessage());
    }

    private Error toConstraintViolationExceptionError(Exception ex) {
        return Error.system(ErrorCode.ILLEGAL_ARGUMENT, ex.getMessage());
    }

    private Error toUnexpectedError(Exception ex) {
        return Error.unexpected(ex.getMessage());
    }

    private HttpStatus resolveStatus(Set<Error> errors) {
        if (errors.stream().anyMatch(error -> error.getCode() == ErrorCode.ILLEGAL_ARGUMENT)) {
            return HttpStatus.BAD_REQUEST;
        }

        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
