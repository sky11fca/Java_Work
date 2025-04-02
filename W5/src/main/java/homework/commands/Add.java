package homework.commands;

import homework.exceptions.BadCommand;
import homework.exceptions.BadDataType;
import homework.model.Image;
import homework.model.ImageRepo;

import javax.imageio.ImageReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Add implements Command
{
    private final ImageRepo repository;

    public Add(ImageRepo repository)
    {
        this.repository = repository;
    }

    @Override
    public void execute(String[] args) throws BadCommand, BadDataType {
        if(args.length < 4)
        {
            throw new BadCommand("USAGE: add <name> <date> <tags> <filePath>");
        }

        try
        {
            String name = args[0];
            LocalDate date = LocalDate.parse(args[1]);
            List<String> tags = Arrays.asList(args[2].split(","));
            Path filePath = Path.of(args[3]);

            Image image = new Image( name, date, tags, filePath);
            repository.addImage(image);
            System.out.println("Added image " + name + " to repository" + image.toString());
        }
        catch(Exception e)
        {
            throw new BadDataType("Provided file is not an image: ARE YOU HIGH? ->" + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "Inserts a new image into the repository";
    }
}
