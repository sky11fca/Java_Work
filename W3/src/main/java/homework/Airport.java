package homework;

import java.util.List;

/**
 * Airport Object
 */

public class Airport
{
    /**
     * List of available runways
     */
    private List<Runway> runways;

    /**
     * Constructor of the Airport object
     * @param runways List: list of predefined runways
     */
    public Airport(List<Runway> runways) {
        this.runways = runways;
    }

    /**
     * Getter for the airport runways
     * @return List: list of available runways
     */
    public List<Runway> getRunways() {
        return runways;
    }
}
