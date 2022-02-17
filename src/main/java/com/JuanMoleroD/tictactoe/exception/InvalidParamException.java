package com.JuanMoleroD.tictactoe.exception;

public class InvalidParamException extends Exception{

    private String exceptionMessage;

    public InvalidParamException(String exceptionMessage){
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }
}
