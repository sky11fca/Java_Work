package com.hexGameServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread
{
    private Socket socket;
    private GameServer gameServer;
    private PrintWriter out;

    public ClientThread(Socket socket, GameServer gameServer)
    {
        this.socket = socket;
        this.gameServer = gameServer;
    }

    @Override
    public void run() {
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            String request;

            while(!socket.isClosed() && (request = in.readLine())!=null)
            {
                System.out.println("Recv request: " + request);

                if(request.equals("stop"))
                {
                    out.println("Server Stopped");
                    gameServer.stop();
                    break;
                }
                else
                {
                    out.println("Server Received: " + request);
                }
            }
        } catch (IOException e) {
            System.err.println("Client Error: " + e.getMessage());
        }
        finally {
            try{
                socket.close();
            } catch (IOException e) {
                System.err.println("Client Error: " + e.getMessage());
            }
        }
    }
}
