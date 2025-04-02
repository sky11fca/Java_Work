package homework;

import homework.commands.*;
import homework.exceptions.BadCommand;
import homework.exceptions.BadDataType;
import homework.model.Image;
import homework.model.ImageRepo;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main
{
    private final Map<String, Command> commands = new HashMap<>();
    private final ImageRepo imageRepo = new ImageRepo();

    public Main()
    {
        commands.put("add", new Add(imageRepo));
        commands.put("remove", new Remove(imageRepo));
        commands.put("update", new Update(imageRepo));
        commands.put("load", new Load(imageRepo));
        commands.put("save", new Save(imageRepo));
        commands.put("report", new Report(imageRepo));
    }

    public void run()
    {
        System.out.println("MY PERSONAL SHELL");
        System.out.println("Type help for commands");

        Scanner scanner = new Scanner(System.in);
        while(true)
        {
            System.out.print("> ");
            String line = scanner.nextLine();
            if(line.equalsIgnoreCase("exit"))
            {
                break;
            }
            else if(line.equalsIgnoreCase("help"))
            {
                commands.forEach((k, v) -> System.out.println(k + ": " + v.getDescription()));
            }

            try{
                processCommand(line);
            }
            catch(BadCommand | BadDataType e)
            {
                System.err.println("ERROR: " + e.getMessage());
            }
        }

        scanner.close();
        System.out.println("Goodbye!");
    }

    private void processCommand(String input) throws BadCommand, BadDataType
    {
        if(input.isEmpty() || input.equals("help")) return;

        String[] parts = input.split("\\s+", 2);
        String commandName = parts[0].toLowerCase();
        String[] args = parts.length > 1 ? parts[1].split("\\s+") : new String[0];

        Command command = commands.get(commandName);
        if(command == null)
        {
            throw new BadCommand("Unknown command: " + commandName);
        }
        command.execute(args);
    }


    public static void main(String[] args)
    {
        new Main().run();
    }
}
