package com.mainstreethub.ttt;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import java.util.logging.Logger;
import org.glassfish.jersey.filter.LoggingFilter;

import com.mainstreethub.ttt.resources.GameRunner;

public class tttApplication extends Application<tttConfig>{
	public static void main(String[] args) throws Exception{
		new tttApplication().run(args);
	}
	
	@Override
    public String getName() {
        return "ttt";
    }
	
	@Override
	public void run(tttConfig config, Environment environment){
		//initialize GameRunner resource
		final GameRunner gr = new GameRunner();
		environment.jersey().register(gr);
		
		environment.jersey().register( new LoggingFilter(
				Logger.getLogger(LoggingFilter.class.getName()),
				true
			)
		);
	}
}