package homework.commands;

import homework.exceptions.BadCommand;
import homework.exceptions.BadDataType;
import homework.model.ImageRepo;

public class Remove implements Command
{
    private final ImageRepo repository;

    public Remove(ImageRepo repository)
    {
        this.repository = repository;
    }

    @Override
    public void execute(String[] args) throws BadCommand, BadDataType
    {
        if(args.length != 1)
        {
            throw new BadCommand("USAGE: Remove <name>");
        }

        String name = args[0];
        if(repository.removeImage(name))
        {
            System.out.println("Removed image " + name);
        }
        else throw new BadDataType("Cannot remove image " + name+ " ARE YOU HIGH?");
    }

    @Override
    public String getDescription() {
        return "Removes image from the repository";
    }
}
