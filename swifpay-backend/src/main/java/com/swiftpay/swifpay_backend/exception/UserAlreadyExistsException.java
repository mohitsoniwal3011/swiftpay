package com.swiftpay.swifpay_backend.exception;

public class UserAlreadyExistsException extends  Exception{
    
    public UserAlreadyExistsException(String message) {
        super(message);
    }

}
