package com.mainstreethub.ttt;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

import com.mainstreethub.ttt.resources.gameRunner;

public class tttApplication extends Application<tttConfig>{
	public static void main(String[] args) throws Exception{
		new tttApplication().run(args);
	}
	
	@Override
	public void run(tttConfig config, Environment environment){
		//initialize gameRunner resource
		final gameRunner gr = new gameRunner();
		environment.jersey().register(gr);
	}
}