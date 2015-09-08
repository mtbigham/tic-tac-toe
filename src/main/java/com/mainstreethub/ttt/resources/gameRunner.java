package com.mainstreethub.ttt.resources;

import com.mainstreethub.ttt.game.Game;

import com.google.common.base.Optional;
import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Path("/GameRunner")
@Produces(MediaType.APPLICATION_JSON)
public class GameRunner{
	
	@Context
	private UriInfo uriInfo;
	
	//Dropwizard resource classes are used by multiple threads
	//so we should use a thread-safe collection to save game data!
	private List<Game> gameList = Collections.synchronizedList(new ArrayList<Game>());
	private AtomicInteger gameID = new AtomicInteger(0);
	
	@POST
	//Creation of a new game
	public Response createGame(){
			Game game = new Game(gameID.incrementAndGet());
			gameList.add(game);
			return Response.created(new GameResource(game, uriInfo).getUri()).build();
	}
	
	@Path("{id}")
	//Linking a new game
	public GameResource getGame(@PathParam("id") int id){	
		for(Game g : gameList){
			if(g.getID() == id){
				return new GameResource(g, uriInfo);
			}
		}
		
		return null;
	}
}