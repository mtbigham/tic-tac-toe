package com.mainstreethub.ttt.health;

import com.mainstreethub.ttt.game.Cell;

import com.codahale.metrics.health.HealthCheck;

public class CellHealthCheck extends HealthCheck {
    private final Cell test;

    public CellHealthCheck(Cell cell) {
        test = cell;
    }

    @Override
    protected Result check() throws Exception {
        //test each possible cell value, and make sure Cell can't be any other value
        
        Cell test = Cell.EMPTY;
        
        if(test.name().equals("EMPTY")){
            test = Cell.X;
            if(test.name().equals("X")){
                test = Cell.O;
                if(test.name().equals("O")){
                    return Result.healthy();
                }
            }
        }
        return Result.unhealthy("Invalid Cell value");
    }
}