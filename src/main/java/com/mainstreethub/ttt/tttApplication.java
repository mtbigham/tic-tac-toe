package com.mainstreethub.ttt;

import com.mainstreethub.ttt.health.*;
import com.mainstreethub.ttt.game.*;

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
		
		final CellHealthCheck cellHC = new CellHealthCheck(Cell.EMPTY);
		environment.healthChecks().register("cell", cellHC);
		
		final GameStatusHealthCheck gameStatusHC = new GameStatusHealthCheck(GameStatus.PLAYING);
		environment.healthChecks().register("gameStatus", gameStatusHC);
		
		final PlayerHealthCheck playerHC = new PlayerHealthCheck(new Player("foo", 1));
		environment.healthChecks().register("player", playerHC);
		
		final MoveHealthCheck moveHC = new MoveHealthCheck(new Move(1, 2, "foo"));
		environment.healthChecks().register("move", moveHC);
		
		final GameHealthCheck gameHC = new GameHealthCheck(9);
		environment.healthChecks().register("game", gameHC);
		
		//enable console logging for the server (helps keep track of requests)
		environment.jersey().register( new LoggingFilter(
				Logger.getLogger(LoggingFilter.class.getName()),
				true
			)
		);
	}
}