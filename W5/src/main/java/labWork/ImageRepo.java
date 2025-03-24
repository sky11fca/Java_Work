package labWork;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageRepo
{
    private final List<Image> images = new ArrayList<>();

    /**
     * Method to add an image
     * @param image Image: Image object
     */
    public void addImage(Image image)
    {
        images.add(image);
        System.out.println("Added image: " + image);
    }

    /**
     * Method of displayig an image
     * @param imageName
     * @throws IOException
     */

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
}
