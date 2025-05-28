package com.hexGameServer;

import java.util.HashMap;
import java.util.Map;

public class GameSession {
    private String gameId;
    private int boardSize;
    private long timeControl;
    private String player1;
    private String player2;
    private boolean isPlayer1Turn = true;
    private HexGame game;
    private Map<String, Long> playerTimes;
    private long lastMoveTime;
    private boolean gameOver=false;
    private String winner = null;


    public GameSession(String gameId, int boardSize, long timeControl, String creator) {
        this.gameId = gameId;
        this.boardSize = boardSize;
        this.timeControl = timeControl;
        this.player1 = creator;
        this.game = new HexGame(boardSize);
        this.playerTimes = new HashMap<>();
        this.playerTimes.put(player1, timeControl);
        this.lastMoveTime = System.currentTimeMillis();

    }

    public synchronized boolean addPlayer(String player) {
        if(player2!=null) return false;
        player2=player;
        playerTimes.put(player2, timeControl);
        return true;
    }

    public synchronized boolean isFull() {
        return player2!=null;
    }

    public synchronized String makeMove(String player, int row, int col) {
        if(gameOver) return "Error: Game over! Winner is " + winner;

        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - lastMoveTime;
        lastMoveTime = currentTime;

        String currentPlayer = isPlayer1Turn ? player1 : player2;
        if(!player.equals(currentPlayer)) {
            return "Error: Not your turn!";
        }

        playerTimes.put(currentPlayer, playerTimes.get(currentPlayer)-elapsedTime);

        if(playerTimes.get(currentPlayer)<=0) {
            gameOver = true;
            winner = isPlayer1Turn ? player2 : player1;
            return "Error: Timed out! Winner is " + winner;
        }

        try{
            if(!game.makeMove(row, col, isPlayer1Turn ? Player.PLAYER1 : Player.PLAYER2 )){
                return "Error: Invalid move";
            }
        }
        catch(IllegalArgumentException e){
            return "Error: " + e.getMessage();
        }

        if(game.hasPlayerWon(isPlayer1Turn ? Player.PLAYER1 : Player.PLAYER2)){
            gameOver = true;
            winner = player;
            return "Success: Move accepted! Winner is " + winner;
        }

        isPlayer1Turn = !isPlayer1Turn;
        return "Success: Move accepted!";
    }
    public synchronized String getStatus() {
        StringBuilder sb = new StringBuilder();
        sb.append("Game ID: ").append(gameId).append("\n");
        sb.append("Players: ").append(player1).append(" vs ").append(player2).append("\n");
        sb.append("Current turn: ").append(isPlayer1Turn ? player1 : player2).append("\n");
        sb.append("Time remaining:\n");
        sb.append("  ").append(player1).append(": ").append(playerTimes.get(player1) / 1000).append("s\n");
        if (player2 != null) {
            sb.append("  ").append(player2).append(": ").append(playerTimes.get(player2) / 1000).append("s\n");
        }
        sb.append("Board:\n").append(game.toString());

        if (gameOver) {
            sb.append("\nGame over. Winner: ").append(winner);
        }

        return sb.toString();
    }

    @Override
    public String toString() {
        return String.format("%s (%dx%d) %s vs %s", gameId, boardSize, boardSize, player1,
                player2 != null ? player2 : "waiting");
    }
}
