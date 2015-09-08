package com.mainstreethub.ttt.health;

import com.mainstreethub.ttt.game.GameStatus;

import com.codahale.metrics.health.HealthCheck;

public class GameStatusHealthCheck extends HealthCheck {
    private final GameStatus test;

    public GameStatusHealthCheck(GameStatus status) {
        test = status;
    }

    @Override
    protected Result check() throws Exception {
        //test each possible cell value, and make sure Cell can't be any other value
        
        GameStatus test = GameStatus.PLAYING;
        
        if(test.name().equals("PLAYING")){
            test = GameStatus.X_WIN;
            if(test.name().equals("X_WIN")){
                test = GameStatus.O_WIN;
                if(test.name().equals("O_WIN")){
                    test = GameStatus.DRAW;
                    if(test.name().equals("DRAW")){
                        return Result.healthy();
                    }
                }
            }
        }
        return Result.unhealthy("Invalid GameStatus value");
    }
}