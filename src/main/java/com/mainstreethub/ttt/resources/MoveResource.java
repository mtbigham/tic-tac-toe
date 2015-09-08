package com.mainstreethub.ttt.resources;

import com.mainstreethub.ttt.game.Game;
import com.mainstreethub.ttt.game.Move;

import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class MoveResource{
	private Game game;
	
	public MoveResource(Game game){
		this.game = game;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response placeMove(Move move) throws Exception{
		game.placeMove(move.getRow(), move.getCol(), move.getName());
		return Response.status(Response.Status.CREATED).build();
	}
}