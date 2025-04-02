package homework.commands;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import homework.exceptions.BadCommand;
import homework.exceptions.BadDataType;
import homework.model.ImageRepo;

import freemarker.template.Configuration;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class Report implements Command
{
    private final ImageRepo repository;
    private final Configuration cfg;

    public Report(ImageRepo repository) {
        this.repository = repository;
        this.cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setClassForTemplateLoading(Report.class, "/");
    }

    @Override
    public void execute(String[] args) throws BadCommand, BadDataType {
        if (args.length != 1) {
            throw new BadCommand("USAGE: report <outputPath>");
        }

        try
        {
            Map<String, Object> data = new HashMap<>();
            data.put("images", repository.getAllImages());
            data.put("timestamp", new java.util.Date());

            Template template = cfg.getTemplate("report.ftl");
            File outputFile = new File(args[0]);
            try(Writer writer = new FileWriter(outputFile))
            {
                template.process(data, writer);
            }

            if(Desktop.isDesktopSupported())
            {
                Desktop.getDesktop().browse(outputFile.toURI());
            }

            System.out.println("REPORT SUCCESSFULLY GENERATED: "+ outputFile.getAbsolutePath());

        }
        catch(IOException | TemplateException e)
        {
            throw new BadDataType("Cannot generate report: "+ e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "Generates an HTML report of all images";
    }
}
