package com.JuanMoleroD.tictactoe.controller.dto;

import com.JuanMoleroD.tictactoe.model.Player;
import lombok.Data;

@Data
public class ConnectRequest {
    private Player player;
    private String gameId;

}
