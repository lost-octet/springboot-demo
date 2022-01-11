package com.lostoctet.restservices.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.Set;

//The annotation @ControllerAdvice makes exception handling here application to all Controllers
@ControllerAdvice

//Exception handled in the Global Exception handling class shall not be handled at other locations and vice-versa.
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    //Overriding the method for handling the exception MethodArgumentNotValidException
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        CustomErrorDetails customError= new CustomErrorDetails(new Date(),
                "MethodArgNotValid from Global Exception",
                ex.getMessage());

        return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
    }

    //Overriding the method for handling the exception HttpRequestMethodNotSupported
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        CustomErrorDetails customError= new CustomErrorDetails(new Date(),
                "HTTP Method not valid from Global Exception",
                ex.getMessage());

        return new ResponseEntity<>(customError, HttpStatus.METHOD_NOT_ALLOWED);
    }

    //Handling the Custom Exception at Global level. In this case the Exception UserNameNotFoundException
    @ExceptionHandler(UserNameNotFoundException.class)
    public final ResponseEntity<Object> handleUserNameNotFoundException(UserNameNotFoundException ex, WebRequest request) {
        CustomErrorDetails customError= new CustomErrorDetails(new Date(), ex.getMessage(), request.getDescription(true));
        return new ResponseEntity<>(customError, HttpStatus.NOT_FOUND);
    }

    //Handling the exception ConstraintViolationException at Global Level
    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        CustomErrorDetails customError= new CustomErrorDetails(new Date(), ex.getMessage(), request.getDescription(true));
        return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
    }


}
