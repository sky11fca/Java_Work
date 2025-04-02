package homework.model;


import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ImageRepo
{
    private final List<Image> images = new ArrayList<>();

    public void addImage(Image image)
    {
        images.add(image);
    }

    public boolean removeImage(String name)
    {
        return images.removeIf(img -> img.name().equals(name));
    }

    public void updateImage(String name, Image newImage)
    {
        removeImage(name);
        addImage(newImage);
    }
    public Optional<Image> findImage(String name)
    {
        return images.stream().filter(img -> img.name().equals(name)).findFirst();
    }



    public void displayImage(String imageName) throws IOException
    {
        for (Image image : images)
        {
            if(image.name().equals(imageName))
            {
                if(Desktop.isDesktopSupported())
                {
                    Desktop desktop = Desktop.getDesktop();
                    if(image.filePath().toFile().exists())
                    {
                        desktop.open(image.filePath().toFile());
                        System.out.println("Image opened: " + image);
                    }
                    else
                    {
                        System.out.println("Image not opened: " + image.filePath());
                    }
                }
                else
                {
                    System.out.println("Desktop is not supported");
                }
                return;
            }
        }
        System.out.println("Image not found: " + imageName);
    }

    public List<Image> getAllImages() {
        return new ArrayList<>(images);
    }

    public void clear()
    {
        images.clear();
    }

}
