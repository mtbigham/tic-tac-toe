package com.mainstreethub.ttt.exceptions;

import io.dropwizard.jersey.errors.ErrorMessage;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class unavailablePlacementException extends WebApplicationException{
	public unavailablePlacementException(){
		super(Response.status(403).entity(new ErrorMessage(998,"A piece already occupies that board position!")).type(MediaType.APPLICATION_JSON).build());
	}
}