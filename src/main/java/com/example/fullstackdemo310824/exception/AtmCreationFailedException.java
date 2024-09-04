package com.example.fullstackdemo310824.exception;

public class AtmCreationFailedException extends Exception{
    public String message;

    public AtmCreationFailedException(String msg){
        this.message=msg;

    }

}
