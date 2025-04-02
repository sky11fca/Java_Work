package labWork;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        ImageRepo repo = new ImageRepo();

        List<String> tags1 = List.of("nature", "landscape");
        List<String> tags2 = List.of("people", "portrait");

        Image image1 = new Image("img1", LocalDate.of(2023, 5, 15), tags1, Path.of("/home/skullface/git/java/W5/TestingImages/51mKd533drL.jpg"));
        Image image2 = new Image("img2", LocalDate.of(2023, 5, 15), tags2, Path.of("/home/skullface/git/java/W5/TestingImages/5908q8fyl0091.jpg"));

        repo.addImage(image1);
        repo.addImage(image2);

        System.out.println("ALL IMAGES ADDED: ");
        repo.getAllImages().forEach(System.out::println);

        try{
            System.out.println("\n Displaying images...");
            repo.displayImage("img1");
            repo.displayImage("img2");
        } catch (Exception e) {
            System.out.println("ERROR: "+ e.getMessage());
        }
        //It doesn't work, will error out, I will provide example images later
    }
}
