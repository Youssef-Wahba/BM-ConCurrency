package com.bm.concurrency.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class CurrencyApiException extends RuntimeException{
    private HttpStatus httpStatus;
    private String message;
    public CurrencyApiException(HttpStatus httpStatus, String message){
        super(message);
        this.httpStatus = httpStatus;
        this.message = message;
    }

}
