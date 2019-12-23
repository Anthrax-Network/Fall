package me.hackusatepvp.fall.games;

import lombok.Getter;

@Getter
public enum GameState {
    STOP("stop"),
    WAITING("waiting"),
    STARTING("starting"),
    GAME("game"),
    RUNNING("running");

    private String toString;

    GameState(String toString){
        this.toString = toString;
    }

    @Override
    public String toString(){
        return this.toString;
    }
}
