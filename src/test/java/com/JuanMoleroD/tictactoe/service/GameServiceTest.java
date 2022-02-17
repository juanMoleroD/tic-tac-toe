package com.JuanMoleroD.tictactoe.service;

import com.JuanMoleroD.tictactoe.model.Game;
import com.JuanMoleroD.tictactoe.model.TicToe;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class GameServiceTest {

    GameService testClass = new GameService();

    @Test
    void checkWinner() {
        Integer integer = 1;
        int [][] testBoard = {{1,1,1},{2,2,0},{}};

        assertTrue(testClass.checkWinner(testBoard , TicToe.X));
    }
}