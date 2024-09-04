package com.example.fullstackdemo310824.exception;

public class AccountcreationFailedException extends Exception{ //checked exception (we use extends with exception that'y this is checked exception
    //if use extends RuntimeException it undergoes to unchecked exception ==== Checked Exception=extends Exception, ====Unchecked Exception=extends RuntimeException
    public String message;

    public AccountcreationFailedException(String msg){
        this.message=msg;

    }

}
