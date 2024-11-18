package com.cookease.cook_ease.domain.exception;

public class ValidationException extends RuntimeException{

    public ValidationException(String message){
        super(message);
    }

}
