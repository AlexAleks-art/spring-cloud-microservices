package com.domasalex.spring.cloud.depositservice.exception;

public class DepositServiceException extends RuntimeException{

    public DepositServiceException(String message) {
        super(message);
    }
}
