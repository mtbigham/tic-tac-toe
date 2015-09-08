package com.mainstreethub.ttt.exceptions;

import io.dropwizard.jersey.errors.ErrorMessage;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class noPlayersException extends WebApplicationException{
	public noPlayersException(){
		super(Response.status(403).entity(new ErrorMessage(995,"The game has no players, please add players.")).type(MediaType.APPLICATION_JSON).build());
	}
}