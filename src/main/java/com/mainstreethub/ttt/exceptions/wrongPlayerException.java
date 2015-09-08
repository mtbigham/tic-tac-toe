package com.mainstreethub.ttt.exceptions;

import io.dropwizard.jersey.errors.ErrorMessage;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class wrongPlayerException extends WebApplicationException{
	public wrongPlayerException(){
		super(Response.status(403).entity(new ErrorMessage(997,"It's not your turn!")).type(MediaType.APPLICATION_JSON).build());
	}
}