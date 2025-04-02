package homework.model;

import java.io.Serializable;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

/**
 * Record for an object of type Image
 * @param name String: Name of the image
 * @param date LocalDate: Date of birth to a file
 * @param tags List of Strings: Asociated image tags
 * @param filePath Path: The absolute path of the file
 */

public record Image(String name, LocalDate date, List<String> tags, Path filePath) implements Serializable {
    @Override
    public String toString() {
        return "Image{" +
                "name='" + name + '\'' +
                ", date=" + date +
                ", tags=" + tags +
                ", filePath=" + filePath +
                '}';
    }
}
