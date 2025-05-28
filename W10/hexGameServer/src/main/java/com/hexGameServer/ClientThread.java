package com.hexGameServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

public class ClientThread extends Thread
{
    private Socket socket;
    private GameServer server;
    private PrintWriter out;
    private BufferedReader in;
    private String clientName;

    public ClientThread(GameServer server, Socket socket)
    {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run()
    {
        try{
            in  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            String request;
            while((request=in.readLine())!=null){
                System.out.println("Received: " + request);
                String response = processRequest(request);
                out.println(response);

                if(response.equals("Server stopped")){
                    break;
                }
            }
        }
        catch(IOException e){
            System.err.println("Communication error with client");
        }
        finally {
            try{
                socket.close();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    private String processRequest(String request){
        String[] parts = request.split(" ");
        String command = parts[0];

        try{
            switch(command){
                case "register":
                    if(parts.length < 2) return "Error: Missing parameters";
                    clientName = parts[1];
                    return "Success: Registered as "+clientName;
                case "stop":
                    server.stop();
                    return "Server stopped";
                case "create":
                    if(parts.length < 3) return "Error: Invalid create command";
                    int size = Integer.parseInt(parts[1]);
                    long time = Long.parseLong(parts[2]);
                    return server.createGame(size, time, clientName);

                case "join":
                    if(parts.length < 2) return "Error: Invalid join command";
                    return server.joinGame(parts[1], clientName);

                case "move":
                    if(parts.length < 3) return "Error: Invalid move command";
                    int row = Integer.parseInt(parts[2]);
                    int col = Integer.parseInt(parts[3]);
                    return server.submitMove(parts[1], clientName, row, col);

                case "status":
                    if(parts.length < 2) return "Error: Invalid status command";
                    return server.getGameStatus(parts[1]);

                case "list":
                    Map<String, GameSession> games = server.getAvailableGames();
                    if(games.isEmpty()) return "Error: No available games";
                    StringBuilder sb = new StringBuilder();
                    for(Map.Entry<String, GameSession>entry : games.entrySet()){
                        sb.append(entry.getKey()).append(" - ").append(entry.getValue()).append("\n");
                    }
                    return sb.toString();
                default:
                    return "Server received the request: " + request;

            }
        } catch(NumberFormatException e){
            return "Error: Invalid number format";
        } catch(Exception e){
          return "Error: " + e.getMessage();
        }
    }
}
