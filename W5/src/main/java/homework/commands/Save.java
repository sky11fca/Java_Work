package homework.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import homework.exceptions.BadCommand;
import homework.exceptions.BadDataType;
import homework.model.Image;
import homework.model.ImageRepo;

import java.io.*;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Save implements Command
{
    private final ImageRepo repository;
    private final ObjectMapper mapper;

    public Save(ImageRepo repository) {
        this.repository = repository;
        this.mapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Override
    public void execute(String[] args) throws BadCommand, BadDataType {
        if (args.length != 1) {
            throw new BadCommand("USAGE: Save <filePath>");
        }

        try
        {
            String json = mapper.writeValueAsString(repository.getAllImages());
            Files.writeString(Path.of(args[0]), json);
            System.out.println("Saved "+repository.getAllImages().size()+" images");
        }
        catch (Exception e)
        {
            throw new BadDataType("Save failed " + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "Saves images into a binary file";
    }
}
