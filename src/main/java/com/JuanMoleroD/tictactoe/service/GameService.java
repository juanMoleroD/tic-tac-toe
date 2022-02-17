package com.JuanMoleroD.tictactoe.service;


import com.JuanMoleroD.tictactoe.exception.InvalidGameException;
import com.JuanMoleroD.tictactoe.exception.InvalidParamException;
import com.JuanMoleroD.tictactoe.exception.NotFoundException;
import com.JuanMoleroD.tictactoe.model.*;
import com.JuanMoleroD.tictactoe.repository.GameRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.JuanMoleroD.tictactoe.model.GameStatus.*;

@Service
@AllArgsConstructor
public class GameService {

    public Game createGame(Player player){
        Game game = new Game();
        game.setBoard(new int[3][3]);
        game.setGameId(UUID.randomUUID().toString());
        game.setPlayer1(player);
        game.setStatus(NEW);
        GameRepository.getInstance().setGame(game);
        return game;

    }

    public Game connectToGame(Player player2, String gameId) throws InvalidGameException, InvalidParamException {
        if (!GameRepository.getInstance().getGames().containsKey(gameId)){
            throw new InvalidParamException("Game not found with that ID");
        }
        Game game = GameRepository.getInstance().getGames().get(gameId);

        if (game.getPlayer2() != null) {
            throw new InvalidGameException("Game already full");
        }

        game.setPlayer2(player2);
        game.setStatus(IN_PROGRESS);
        GameRepository.getInstance().setGame(game);
        return game;
    }

    public Game connectToRandomGame(Player player2) throws NotFoundException{
        Game game = GameRepository.getInstance().getGames().values().stream()
                .filter(it -> it.getStatus().equals(NEW)).findFirst()
                .orElseThrow(() -> new NotFoundException("Game Not Found"));
        game.setPlayer2(player2);
        game.setStatus(IN_PROGRESS);
        GameRepository.getInstance().setGame(game);
        return game;
    }

    public Game gameplay(Gameplay gameplay) throws NotFoundException, InvalidGameException {
        if (!GameRepository.getInstance().getGames().containsKey(gameplay.getGameId())) {
            throw new NotFoundException("Game not found");
        }

        Game game = GameRepository.getInstance().getGames().get(gameplay.getGameId());
        if (game.getStatus().equals(FINISHED)){
            throw new InvalidGameException("Game has already ended");
        }

        int[][] board = game.getBoard();
        board[gameplay.getCoordinateX()][gameplay.getCoordinateY()] = gameplay.getType().getValue();

        checkWinner(game.getBoard(), TicToe.X);
        checkWinner(game.getBoard(), TicToe.O);

        GameRepository.getInstance().setGame(game);

        return game;
    }

    private boolean checkWinner(int[][] board, TicToe ticToe) {
        int[] boardFlatArray = new int[9];
        int counterIndex = 0;

        for(int i =0; i<board.length; i++) {
            for(int j = 0; j <board[i].length; j++ ){
                boardFlatArray[counterIndex] = board[i][j];
                counterIndex++;
            }
        }

        int [][] winCombinations =  {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,7}, {0,4,8}, {2,4,6}};

        for(int i = 0; i<winCombinations.length; i++) {
            int counter = 0;
            for (int j = 0; j < winCombinations[i][j]; j++) {
                if(boardFlatArray[winCombinations[i][j]] == ticToe.getValue()) {
                    counter ++;
                    if (counter == 3) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
