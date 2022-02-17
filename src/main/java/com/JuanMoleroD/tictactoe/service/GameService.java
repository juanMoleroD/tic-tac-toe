package com.JuanMoleroD.tictactoe.service;


import com.JuanMoleroD.tictactoe.model.Game;
import com.JuanMoleroD.tictactoe.model.GameStatus;
import com.JuanMoleroD.tictactoe.model.Player;
import com.JuanMoleroD.tictactoe.repository.GameRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

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


}
