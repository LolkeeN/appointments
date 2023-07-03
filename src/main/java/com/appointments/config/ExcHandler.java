package com.appointments.config;

import com.appointments.exception.ClientNotFoundException;
import com.appointments.exception.DepartmentNotFoundException;
import com.appointments.exception.EmailAlreadyInUseException;
import com.appointments.exception.InvalidPasswordException;
import com.appointments.exception.NotLoggedInException;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.ConstraintViolationException;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExcHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> constraintViolationExceptionException(ConstraintViolationException e) {
        var response = Response.builder()
                .code(400)
                .message(e.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<?> constraintViolationExceptionException(ClientNotFoundException e) {
        var response = Response.builder()
                .code(400)
                .message(e.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<?> constraintViolationExceptionException(DepartmentNotFoundException e) {
        var response = Response.builder()
                .code(400)
                .message(e.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(EmailAlreadyInUseException.class)
    public ResponseEntity<?> constraintViolationExceptionException(EmailAlreadyInUseException e) {
        var response = Response.builder()
                .code(400)
                .message(e.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<?> constraintViolationExceptionException(InvalidPasswordException e) {
        var response = Response.builder()
                .code(400)
                .message(e.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NotLoggedInException.class)
    public ResponseEntity<?> constraintViolationExceptionException(NotLoggedInException e) {
        var response = Response.builder()
                .code(403)
                .message(e.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @Data
    @Builder
    private static class Response {
        private int code;
        private String message;
    }

    @Data
    @Builder
    private static class MethodArgumentResponse {
        private int code;
        private String field;
        private String message;
    }

    @Data
    @Builder
    private static class InternalServerErrorResponse {
        private String exceptionClass;
        private String message;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String trace;
    }

}
