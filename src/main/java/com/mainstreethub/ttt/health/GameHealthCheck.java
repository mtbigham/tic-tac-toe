package com.mainstreethub.ttt.health;

import com.mainstreethub.ttt.game.*;
import com.mainstreethub.ttt.exceptions.*;

import com.codahale.metrics.health.HealthCheck;
import java.lang.Throwable.*;

public class GameHealthCheck extends HealthCheck {
    private final int id;

    public GameHealthCheck(int id) {
        this.id = id;
    }

    @Override
    protected Result check() throws Exception {
        //Ensure all game commands return correct values
        
        //Create new Game every time this is run (to avoid alterations caused by one run
        //causing failures in subsequent runs)
        Game game = new Game(id);
        
        //FIRST: Check all base states
        
        //check getID
        if(game.getID() != 9){
            return Result.unhealthy("ID not created correctly");
        }
        
        //check getBoard
        Cell[][] testBoard = new Cell[][]{
            new Cell[]{Cell.EMPTY,Cell.EMPTY,Cell.EMPTY},
            new Cell[]{Cell.EMPTY,Cell.EMPTY,Cell.EMPTY},
            new Cell[]{Cell.EMPTY,Cell.EMPTY,Cell.EMPTY}};
       Cell[][] gameBoard = game.getBoard();
       
       for(int i = 0; i < 3; i++){
           for(int j = 0; j < 3; j++){
               if(gameBoard[i][j] != testBoard[i][j]){
                   return Result.unhealthy("Board not created correctly");
               }
           }
       }
       
       //check getCell
       if(game.getCell(0,0) != Cell.EMPTY){
           return Result.unhealthy("Cell not returned correctly");
       }
       
       //check getStatus
       if(game.getStatus() != GameStatus.PLAYING){
           return Result.unhealthy("GameStatus not set correctly");
       }
       
       //check getPlayers
       if(game.getPlayers().size() != 0){
           return Result.unhealthy("PlayerList not initialized as empty");
       }
       
       //check getNumMoves
       if(game.getNumMoves() != 0){
           return Result.unhealthy("numMoves not correctly initialized");
       }
       
       //check getNextPlayer
       if(game.getNextPlayer() != -1){
           return Result.unhealthy("NextPlayer returned non-error value with no players in game");
       }
       
       
       //NEXT: UPDATE BOARD AND PERFORM NEW CHECKS
       
       //check player adding and PlayerList
       game.addPlayer(new Player("Taylor", 0));
       game.addPlayer(new Player("Rachel", 1));
       game.addPlayer(new Player("TEST",2));
       
       if(game.getPlayers().size() > 2){
           return Result.unhealthy("addPlayer added too many players");
       }
       else if(game.getPlayers().size() < 2){
           return Result.unhealthy("addPlayer added too few players");
       }
       else{
           //check if players were added correctly
           if(!game.getPlayers().get(0).getName().equals("Taylor") ||
               !game.getPlayers().get(1).getName().equals("Rachel")){
               return Result.unhealthy("addPlayer did not add players correctly");
           }
       }
       
       //check getNextPlayer
       if(game.getNextPlayer() != 0){
           return Result.unhealthy("NextPlayer returned wrong Next Player id");
       }
       
       //check placeMove
       try{
           //check valid move
           game.placeMove(0,0,"Taylor");
           if(game.getBoard()[0][0] != Cell.X){
               return Result.unhealthy("placeMove did not correctly update the target cell");
           }
       }
       catch(Throwable t){
           return Result.unhealthy("placeMove threw an exception");
       }
       
       //check getNextPlayer for correct update
       if(game.getNextPlayer() != 1){
           return Result.unhealthy("NextPlayer did not update after correct move");
       }
       
       //check that game state properly updates
       game.setStatus(GameStatus.X_WIN);
       if(game.getStatus() != GameStatus.X_WIN){
           return Result.unhealthy("setStatus did not set the game status correctly");
       }
       
       //check that checkStatus correctly re-sets gameStatus to playing
       game.checkStatus();
       if(game.getStatus() != GameStatus.PLAYING){
           return Result.unhealthy("checkStatus did not correctly re-set the game status");
       }
       
       return Result.healthy();
    }
    
    
}