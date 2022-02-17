package com.JuanMoleroD.tictactoe.controller;

import com.JuanMoleroD.tictactoe.controller.dto.ConnectRequest;
import com.JuanMoleroD.tictactoe.exception.InvalidGameException;
import com.JuanMoleroD.tictactoe.exception.InvalidParamException;
import com.JuanMoleroD.tictactoe.exception.NotFoundException;
import com.JuanMoleroD.tictactoe.model.Game;
import com.JuanMoleroD.tictactoe.model.Gameplay;
import com.JuanMoleroD.tictactoe.model.Player;
import com.JuanMoleroD.tictactoe.service.GameService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping("/start")
    public ResponseEntity<Game> start(@RequestBody Player player){
        log.info("start game request: {}", player);
        return ResponseEntity.ok(gameService.createGame(player));
    }

    @PostMapping("/connect")
    public ResponseEntity<Game> connect(@RequestBody ConnectRequest request) throws InvalidParamException, InvalidGameException {
        log.info("connect request: {}" , request);
        return ResponseEntity.ok(gameService.connectToGame(request.getPlayer(), request.getGameId()));
    }

    @PostMapping("/connect/random")
    public ResponseEntity<Game> connectRandom(@RequestBody Player player) throws NotFoundException {
        log.info("connect random request: {}", player);
        return ResponseEntity.ok(gameService.connectToRandomGame(player));
    }

    @PostMapping("/gameplay")
    public ResponseEntity<Game> gameplay(@RequestBody Gameplay request) throws InvalidGameException, NotFoundException {
        log.info("gameplay: {}", request);
        Game game = gameService.gameplay(request);
        simpMessagingTemplate.convertAndSend("/topic/game-progress/" + game.getGameID(), game);
        return ResponseEntity.ok(game);
    }

}
