package com.JuanMoleroD.tictactoe.model;

import lombok.Data;

@Data
public class Gameplay {

    private TicToe type;
    private Integer coordinateX;
    private Integer coordinateY;
    private String gameId;


}
