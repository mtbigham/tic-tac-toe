package com.mainstreethub.ttt.health;

import com.mainstreethub.ttt.game.Move;

import com.codahale.metrics.health.HealthCheck;

public class MoveHealthCheck extends HealthCheck {
    private final Move move;

    public MoveHealthCheck(Move move) {
        this.move = move;
    }

    @Override
    protected Result check() throws Exception {
        //Create new player, ensure that all values are as expected
        if(move.getRow() == 1 && move.getCol() == 2 && move.getName().equals("foo")){
            return Result.healthy();
        }
        return Result.unhealthy("Move not created correctly");
    }
}