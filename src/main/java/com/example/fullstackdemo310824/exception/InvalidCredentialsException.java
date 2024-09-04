package com.example.fullstackdemo310824.exception;

public class InvalidCredentialsException extends Exception{
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
