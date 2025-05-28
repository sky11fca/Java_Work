package com.hexGameServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameServer
{
    private static final int PORT = 8080;
    private ServerSocket serverSocket;
    private boolean running = false;
    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    private Map<String, GameSession> gameSessions = new HashMap<>();
    private int nextGameId=1;

    public static void main(String[] args)
    {
        GameServer server = new GameServer();
        server.init();
        server.waitForClients();
    }

    private void init()
    {
        try{
            serverSocket = new ServerSocket(PORT);
            running = true;
            System.out.println("Server started on port " + PORT);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    private void waitForClients()
    {
        while(running)
        {
            try{
                System.out.println("Waiting for client on port " + PORT);
                Socket socket = serverSocket.accept();
                executorService.submit(new ClientThread(this, socket));
            }
            catch(IOException e)
            {
                System.err.println("Accept failed:");
            }
        }
        executorService.shutdown();
        System.out.println("Server stopped");
    }


    public synchronized String createGame(int boardSize, long timeControl, String creator)
    {
        String gameId = "game" + nextGameId++;
        GameSession game = new GameSession(gameId, boardSize, timeControl, creator);
        gameSessions.put(gameId, game);
        return gameId;
    }

    public synchronized String joinGame(String gameId, String player)
    {
        GameSession game = gameSessions.get(gameId);
        if(game==null){
            return "Error: Game not found";
        }
        if(game.addPlayer(player)){
            return "Success: Joined game " + gameId;
        }
        return "Error: Could not join game";
    }

    public synchronized String submitMove(String gameId, String player, int row, int col)
    {
        GameSession game = gameSessions.get(gameId);
        if(game==null){
            return "Error: Game not found";
        }
        return game.makeMove(player, row, col);
    }

    public synchronized String getGameStatus(String gameId)
    {
        GameSession game = gameSessions.get(gameId);
        if(game==null){
            return "Error: Game not found";
        }
        return game.getStatus();
    }

    public synchronized void stop(){
        running = false;
        try{
            serverSocket.close();
        }
        catch(IOException e){
            System.err.println("Error closing server socket: " + e.getMessage());
        }
    }

    public synchronized Map<String, GameSession> getAvailableGames(){
        Map<String, GameSession> available = new HashMap<>();

        for(Map.Entry<String, GameSession> entry : gameSessions.entrySet()){
            if(!entry.getValue().isFull()){
                available.put(entry.getKey(), entry.getValue());
            }
        }

        return available;
    }

}
