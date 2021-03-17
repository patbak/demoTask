package com.one2tribe.demo.exception;

public class TooBigParamNumberFromUserException extends Exception{
    public TooBigParamNumberFromUserException(String errorMessage){
        super(errorMessage);
    }
}
