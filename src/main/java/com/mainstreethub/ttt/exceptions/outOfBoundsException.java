package com.mainstreethub.ttt.exceptions;

import io.dropwizard.jersey.errors.ErrorMessage;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class outOfBoundsException extends WebApplicationException{
	public outOfBoundsException(){
		super(Response.status(403).entity(new ErrorMessage(999,"Attempted to place a piece in an out-of-bounds position!")).type(MediaType.APPLICATION_JSON).build());
	}
}