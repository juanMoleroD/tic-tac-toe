package com.JuanMoleroD.tictactoe.service;


import com.JuanMoleroD.tictactoe.exception.InvalidGameException;
import com.JuanMoleroD.tictactoe.exception.InvalidParamException;
import com.JuanMoleroD.tictactoe.exception.NotFoundException;
import com.JuanMoleroD.tictactoe.model.Game;
import com.JuanMoleroD.tictactoe.model.GameStatus;
import com.JuanMoleroD.tictactoe.model.Player;
import com.JuanMoleroD.tictactoe.repository.GameRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.JuanMoleroD.tictactoe.model.GameStatus.IN_PROGRESS;
import static com.JuanMoleroD.tictactoe.model.GameStatus.NEW;

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



}
