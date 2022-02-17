package com.JuanMoleroD.tictactoe.exception;

public class InvalidGameException extends Exception{

    private String exceptionMessage;

    public InvalidGameException(String exceptionMessage){
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }
}
