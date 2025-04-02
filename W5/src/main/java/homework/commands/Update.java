package homework.commands;

import homework.exceptions.BadCommand;
import homework.exceptions.BadDataType;
import homework.model.Image;
import homework.model.ImageRepo;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Update implements Command
{
    private final ImageRepo repository;

    public Update(ImageRepo repository) {
        this.repository = repository;
    }

    @Override
    public void execute(String[] args) throws BadCommand, BadDataType {
        if (args.length < 4)
        {
            throw new BadCommand("USAGE: Update <oldImage> <newImage> <date> <tags> <filePath>");
        }

        try{
            String oldName = args[0];
            String newName = args[1];
            LocalDate date = LocalDate.parse(args[2]);
            List<String> tags = Arrays.asList(args[3].split(","));
            Path filePath = Path.of(args[4]);

            Image newImage= new Image(newName, date, tags, filePath);
            repository.updateImage(oldName, newImage);
            System.out.println("Updated image " + oldName + " to " + newName);
        } catch (Exception e) {
            throw new BadDataType("Cannot update image " + args[1] + " ARE YOU HIGH?" + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "Updates an existing image int the repo";
    }
}
