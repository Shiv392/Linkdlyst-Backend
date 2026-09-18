package com.example.Linkdlyst.Utils.Exceptions;

public class TooManyRequestException extends  RuntimeException{
    public TooManyRequestException(String message){
        super(message);
    }
}
