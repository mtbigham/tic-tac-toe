package com.mainstreethub.ttt.resources;

import com.google.common.base.Optional;
import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Path("/gameRunner")
@Produces(MediaType.APPLICATION_JSON)
public class gameRunner{
	//Dropwizard resource classes are used by multiple threads
	//so we should use a thread-safe collection to save game data!
	private List<String> gameList = Collections.synchronizedList(new ArrayList<String>());
	private AtomicLong count = new AtomicLong(0);
	
	@GET
	@Timed
	public String testMethod(@QueryParam("testStr") Optional<String> testStr){
		return String.format("test %s", testStr.or("TEST STRING"));
	}
}