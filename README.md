# tic-tac-toe
Testing out Dropwizard by making a curl-playable tic-tac-toe game

###Note

I'm using curl through Windows Powershell, so some steps may be slightly different if you are working on another OS

#####Example

Curl POST commands require that any " characters must be escaped (/") in my implementation, but I'm not sure if this is required for curl commands on *nix.

###Setup

1.  Navigate to the tic-tac-toe folder using commandline.
2.  Build the Maven package: ```mvn package```
3.  Start the Dropwizard server: ```java -jar .\target\ttt-0.0.1-SNAPSHOT.jar server```

###How to Play
1. Create new game: ```curl -v -d '{}' http://localhost:8080/GameRunner/```
  * The response's location header shows the uri of the most recently created game
  * A successful call will return ```201 Created```
  * The new game call will continue to create new games and return their uri's if used multiple times
2. Check current game status: ```curl -v http://localhost:8080/GameRunner/{uri}```
  * Replace {uri} with the integer identifying the game you'd like to view
  * A successful call will return ```200 OK```
  * Returns:
    * ```id```: The game's ID, equivalent to the value of {uri}
    * ```status```: The current status of the game. (PLAYING, X_WIN, O_WIN, DRAW)
    * ```numMoves```: The number of moves that have been made
    * ```players```: An array containing names and id's of players that have been created and assigned to this game
    * ```nextPlayer```: An integer denoting which player id needs to play next (-1 if no players added yet)
    * ```board```: A 2D array containing the current value of each cell on the board
      * The board can be more easily read by imagining that the three arrays are stacked vertically with coordinates of the form (row, column):
        * [ 0,0 | 0,1 | 0,2 ]
        * [ 1,0 | 1,1 | 1,2 ]
        * [ 2,0 | 2,1 | 2,2 ]
      * ```EMPTY```: No player has played a piece at this location yet
      * ```X```: An X piece is located at this position
      * ```O```: An O piece is located at this position
3. Create Players: ```curl -v --data '{name}' http://localhost:8080/GameRunner/{uri}/players```
  * The response's location header shows the uri of this player once created
  * A successful player creation will return ```201 Created```
  * Games will return an error code if an attempt is made to play a move without any players assigned to that game
4. Create Moves: ```curl -v -H "Content-Type: application/json" --data '{\"col\":{col},\"row\":{row},\"name\":\"{playerName}\"}' http://localhost:8080/GameRunner/{uri}/move```
  * Replace {row} and {col} with integers denoting the position that you'd like to place a piece at, and {playerName} with the name of one of the two players assigned to the game
  * A successful move will return ```201 Created```
  * An unsuccessful move will return a code and a message denoting what went wrong (Not your turn/invalid position/unavailable position/game over)
5. Now repeat until you either check the game's state and see that status no longer equals PLAYING, or until a move request returns a Game Over message!
