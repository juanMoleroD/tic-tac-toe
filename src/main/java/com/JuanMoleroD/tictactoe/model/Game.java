package com.JuanMoleroD.tictactoe.model;

import lombok.Data;

@Data
public class Game {

    private String gameId;
    private Player player1, player2;

    private GameStatus status;

    private int [][] board;

    private  TicToe winner;

    public void setGameId(String newGameID) {
        this.gameId = newGameID;
    }
}
