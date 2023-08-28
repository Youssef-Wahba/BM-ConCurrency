package com.bm.concurrency.exception;

import com.bm.concurrency.payload.error.ErrorDetailsDTO;
import com.bm.concurrency.payload.error.ValidationErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {
//    handling resource not found exception
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetailsDTO> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
        ErrorDetailsDTO errorDetailsDTO = new ErrorDetailsDTO(new Date(),exception.getMessage(),webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetailsDTO,HttpStatus.NOT_FOUND);
    }
//    handling currency api exception
    @ExceptionHandler(CurrencyApiException.class)
    public ResponseEntity<ErrorDetailsDTO> handleCurrencyApiException(CurrencyApiException exception, WebRequest webRequest){
        ErrorDetailsDTO errorDetailsDTO = new ErrorDetailsDTO(new Date(),exception.getMessage(),webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetailsDTO, exception.getHttpStatus());
    }
//    handling validation exception
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDTO> handleValidationException(MethodArgumentNotValidException exception){
        List<String> errors = new ArrayList<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String property = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.add(String.format("'\\%s\\' : %s",property,message));
        });
        return new ResponseEntity<>(new ValidationErrorDTO(errors),HttpStatus.BAD_REQUEST);
    }
//    handling global exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetailsDTO> handleGlobalException(Exception exception, WebRequest webRequest){
        ErrorDetailsDTO errorDetailsDTO = new ErrorDetailsDTO(new Date(),exception.getMessage(),webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetailsDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
