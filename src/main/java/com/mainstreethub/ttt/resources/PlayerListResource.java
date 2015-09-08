package com.mainstreethub.ttt.resources;

import com.mainstreethub.ttt.game.Player;

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
import java.net.URI;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Produces(MediaType.APPLICATION_JSON)
public class PlayerListResource {

    private final List<Player> players;
    private final UriInfo uriInfo;

    public PlayerListResource(List<Player> players, UriInfo uriInfo) {
        this.players = players;
        this.uriInfo = uriInfo;
    }

    @POST
    public Response newPlayer(String name){
        //check current size of playerList to determine player ID
        //(max of two players means we never want ID > 1)
        if(players.size() < 2){ //not full yet
            int playerID = players.size();
            Player player = new Player(name, playerID);
            players.add(player);
            
            //create URI here since we're dealing with a list of players
            //and we want the uri to point to specifically this player
            URI playerURI = uriInfo.getAbsolutePathBuilder().path(Integer.toString(playerID)).build();
            
            return Response.created(playerURI).build();
        }
        else{ //game is full
            //Fail!
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }
    
    @Path("{id}")
	//Linking a new game
	public PlayerResource getPlayer(@PathParam("id") int id){
        //since there will only ever be 2 players, we know the id will either be 0 or 1
		
        //check out of bounds
        if(id >= 0 && id < players.size()){
            return new PlayerResource(players.get(id));
        }
        else{
		  return null;  
        }
	}
}