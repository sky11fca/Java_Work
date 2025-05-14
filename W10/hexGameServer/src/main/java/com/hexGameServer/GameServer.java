package com.hexGameServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer
{
    private static final int PORT = 8080;

    private ServerSocket serverSocket;
    private boolean running = true;

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
            System.err.println("Could not start server on port " + PORT);
        }
    }

    private void waitForClients()
    {
        while(running)
        {
            try{
                System.out.println("Waiting for client on port " + PORT);
                Socket socket = serverSocket.accept();
                new ClientThread(socket, this).start();
            }
            catch(IOException e)
            {
                if(running)
                {
                    System.err.println("Could not accept client on port " + e.getMessage());
                }
            }
        }
    }

    public void stop() throws IOException{
        running = false;
        serverSocket.close();
        System.out.println("Server stopped");
    }

    public boolean isRunning()
    {
        return running;
    }

}
