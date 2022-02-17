package com.JuanMoleroD.tictactoe.exception;

public class NotFoundException extends Exception{

    private String exceptionMessage;

    public NotFoundException(String exceptionMessage){
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }
}
