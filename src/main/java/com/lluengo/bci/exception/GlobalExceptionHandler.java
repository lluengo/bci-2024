package com.lluengo.bci.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserFoundedException.class)
    public ResponseEntity<?> resourceNotFoundHandling(UserFoundedException exception, WebRequest request){
        ErrorDetail errorDetail =
                new ErrorDetail(new Date(), HttpStatus.CONFLICT.value(), exception.getMessage());
        return new ResponseEntity<>(errorDetail, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandling(Exception exception, WebRequest request){
        ErrorDetail errorDetail =
                new ErrorDetail(new Date(), HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
        return new ResponseEntity<>(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> globalExceptionHandling(MethodArgumentNotValidException exception, WebRequest request){

        String mensaje = Arrays.stream(exception.getDetailMessageArguments())
                .filter(Objects::nonNull)
                .map(Object::toString)
                .map(s -> s.replaceAll("\\[|\\]", ""))
                .filter(s -> !s.isEmpty())
                .collect(Collectors.joining(", "));

        ErrorDetail errorDetail = new ErrorDetail(new Date(), HttpStatus.BAD_REQUEST.value(), mensaje);
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailValidationException.class)
    public ResponseEntity<?> globalExceptionHandling(EmailValidationException exception, WebRequest request){
        ErrorDetail errorDetail =
                new ErrorDetail(new Date(), HttpStatus.CONFLICT.value(), exception.getMessage());
        return new ResponseEntity<>(errorDetail, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PasswordException.class)
    public ResponseEntity<?> globalExceptionHandling(PasswordException exception, WebRequest request){
        ErrorDetail errorDetail =
                new ErrorDetail(new Date(), HttpStatus.CONFLICT.value(), exception.getMessage());
        return new ResponseEntity<>(errorDetail, HttpStatus.CONFLICT);
    }
}
