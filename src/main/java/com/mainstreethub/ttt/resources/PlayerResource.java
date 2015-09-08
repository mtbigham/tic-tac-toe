package com.mainstreethub.ttt.resources;

import com.mainstreethub.ttt.game.Player;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Produces(MediaType.APPLICATION_JSON)
public class PlayerResource{
	private final Player player;
	
	public PlayerResource(Player player){
		this.player = player;
	}
	
	@GET
	public Player getPlayer(){
		return player;
	}
}