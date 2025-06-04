package com.hexGameClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;


public class GameClient
{
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 8080;
    private String playerName;

    public static void main(String[] args)
    {
        GameClient gameClient = new GameClient();
        gameClient.start();
    }

    private void start()
    {
        try(Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true))
        {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Connected to " + SERVER_ADDRESS + ":" + SERVER_PORT + "\nType help for commands");
            System.out.print("Enter name: ");
            playerName = scanner.nextLine();
            out.println("register " + playerName);
            System.out.println(in.readLine());

            while(true)
            {
                System.out.print("> ");

                String command = scanner.nextLine();

                if(command.equals("exit")){
                    break;
                }

                if(command.equals("help")){
                    printHelp();
                    continue;
                }


                out.println(command);
                String response = in.readLine();
                System.out.println(response);

            }
        }
        catch(IOException e){
            System.err.println("Communication error: " + e.getMessage());
        }
    }

    private void printHelp(){
        System.out.println("Available commands:");
        System.out.println("register <name> - Register with your player name");
        System.out.println("create <size> <time> - Create a new game with time control (in seconds)");
        System.out.println("join <gameId> - Join an existing game");
        System.out.println("move <gameId> <row> <col> - Make a move (0-based coordinates)");
        System.out.println("status <gameId> <playerName> - Check game status");
        System.out.println("list - Display List of active games");
        System.out.println("exit - Quit the game");
        System.out.println("stop - Stops server");
        System.out.println("help - Display this help message");
    }
}
