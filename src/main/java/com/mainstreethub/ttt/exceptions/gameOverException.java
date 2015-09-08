package com.mainstreethub.ttt.exceptions;

import io.dropwizard.jersey.errors.ErrorMessage;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class gameOverException extends WebApplicationException{
	public gameOverException(String status){
		super(Response.status(403).entity(new ErrorMessage(996,"The Game is over: "+status)).type(MediaType.APPLICATION_JSON).build());
	}
}