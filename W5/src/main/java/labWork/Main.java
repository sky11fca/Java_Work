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

        Image image1 = new Image("Fat Fuck", LocalDate.of(2023, 5, 15), tags1, Path.of("/home/skullface/Pictures/how-and-when-was-this-fat-fuck-a-thing-v0-d1olvd0zz4vd1.jpeg"));
        Image image2 = new Image("MLG Drones", LocalDate.of(2023, 5, 15), tags2, Path.of("/home/skullface/Pictures/MLG Drones (NO BLURR).png"));

        repo.addImage(image1);
        repo.addImage(image2);

        System.out.println("ALL IMAGES ADDED: ");
        repo.getAllImages().forEach(System.out::println);

        try{
            System.out.println("\n Displaying images...");
            repo.displayImage("Fat Fuck");
            repo.displayImage("MLG Drones");
        } catch (Exception e) {
            System.out.println("ERROR: "+ e.getMessage());
        }

    }
}
