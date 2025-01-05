package com.domasalex.spring.cloud.billservice.exception;

public class BillNotFoundException extends RuntimeException {

    public BillNotFoundException(String message) {
        super(message);
    }
}
