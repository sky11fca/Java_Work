package com.hexGameClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class GameClient
{
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 8080;
    private boolean running;

    public static void main(String[] args)
    {
        GameClient client = new GameClient();
        client.start();
    }

    private void start()
    {
        try(Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in)))
        {
            running = true;

            System.out.println("Connected to " + SERVER_ADDRESS + ":" + SERVER_PORT + "Type exit to quit.");

            while(running)
            {
                System.out.print("Enter command: ");
                String command = consoleInput.readLine();

                if(command == null || command.equals("exit"))
                {
                    running = false;
                    break;
                }
                out.println(command);

                String respone = in.readLine();

                System.out.println("Response: " + respone);

                if(respone != null && respone.equals("Server Stopped"))
                {
                    running = false;
                }
            }
        }
        catch (UnknownHostException e)
        {
            System.err.println("Unknown host: " + SERVER_ADDRESS + ":" + SERVER_PORT);
        } catch (IOException e) {
            System.err.println("I/O exception: " + e);
        }
        System.out.println("Client stopped");
    }
}
