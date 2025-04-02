package homework.commands;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import homework.exceptions.BadCommand;
import homework.exceptions.BadDataType;
import homework.model.Image;
import homework.model.ImageRepo;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Load  implements Command
{
    private final ImageRepo repository;
    private final ObjectMapper mapper;

    public Load(ImageRepo repository) {
        this.repository = repository;
        this.mapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Override
    public void execute(String[] args) throws BadCommand, BadDataType {
        if(args.length != 1)
        {
            throw new BadCommand("USAGE: load <filePath>");
        }
        try{
            String json = Files.readString(Path.of(args[0]));
            List<Image> images = mapper.readValue(json, new TypeReference<List<Image>>(){});
            repository.clear();
            images.forEach(repository::addImage);
            System.out.println("Loaded " + images.size() + " images");
        }
        catch(Exception e)
        {
            throw new BadDataType("Load failed " + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "Load Images from the binary file";
    }
}
