package com.ded.locadora.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException (ResourceNotFoundException rnfe) {
        ResourceNotFoundDetails resourceNotFoundDetails = ResourceNotFoundDetails.Builder.newBuilder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Resource not found")
                .detail(rnfe.getMessage())
                .developerMessage(rnfe.getClass().getName())
                .build();
        return new ResponseEntity<>(resourceNotFoundDetails, HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(BadRequestException.class)
//    public ResponseEntity<?> badRequestException (BadRequestException bre) {
//        ResourceNotFoundDetails resourceNotFoundDetails = ResourceNotFoundDetails.Builder.newBuilder()
//                .timestamp(new Date().getTime())
//                .status(HttpStatus.BAD_REQUEST.value())
//                .title("Resource not found")
//                .detail(bre.getMessage())
//                .developerMessage(bre.getClass().getName())
//                .build();
//        return new ResponseEntity<>(resourceNotFoundDetails, HttpStatus.BAD_REQUEST);
//    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException manvException,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        List<FieldError> fieldErrors = manvException.getBindingResult().getFieldErrors();
        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(","));
        String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));
        ValidationErrorDetails veDetails = ValidationErrorDetails.Builder.newBuilder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Field Validation Error")
                .detail("Validation failed for arguments")
                .developerMessage(manvException.getClass().getName())
                .field(fields)
                .fieldMessage(fieldsMessage)
                .build();
        return new ResponseEntity<>(veDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> responseStatusException (ResponseStatusException rse) {
        ResourceNotFoundDetails resourceNotFoundDetails = ResourceNotFoundDetails.Builder.newBuilder()
                .timestamp(new Date().getTime())
                .status(rse.getStatus().value())
                .title(rse.getReason())
                .detail(rse.getMessage())
                .developerMessage(rse.getClass().getName())
                .build();
        return new ResponseEntity<>(resourceNotFoundDetails, rse.getStatus());
    }
}
