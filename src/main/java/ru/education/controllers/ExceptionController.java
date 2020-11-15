package ru.education.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.education.exceptions.*;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(EntityIllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseEntity handleEntityIllegalArgumentException(EntityIllegalArgumentException ex) {
        return createErrorResponseEntity(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorResponseEntity handleEntityAlreadyExistsException(EntityAlreadyExistsException ex) {
        return createErrorResponseEntity(ex, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EntityConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorResponseEntity handleEntityConflictException(EntityConflictException ex) {
        return createErrorResponseEntity(ex, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EntityHasDetailsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorResponseEntity handleEntityHasDetailsException(EntityHasDetailsException ex) {
        return createErrorResponseEntity(ex, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponseEntity handleEntityNotFoundException(EntityNotFoundException ex) {
        return createErrorResponseEntity(ex, HttpStatus.NOT_FOUND);
    }

    private static ErrorResponseEntity createErrorResponseEntity(BaseException ex, HttpStatus httpStatus) {
        return new ErrorResponseEntity(ex.getMessage(), httpStatus.getReasonPhrase(), httpStatus.value());
    }
}
