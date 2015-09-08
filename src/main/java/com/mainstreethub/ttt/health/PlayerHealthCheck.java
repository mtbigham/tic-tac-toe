package com.mainstreethub.ttt.health;

import com.mainstreethub.ttt.game.Player;

import com.codahale.metrics.health.HealthCheck;

public class PlayerHealthCheck extends HealthCheck {
    private final Player player;

    public PlayerHealthCheck(Player player) {
        this.player = player;
    }

    @Override
    protected Result check() throws Exception {
        //Create new player, ensure that all values are as expected
        if(player.getID() == 1 && player.getName().equals("foo")){
            return Result.healthy();
        }
        return Result.unhealthy("Player not created correctly");
    }
}