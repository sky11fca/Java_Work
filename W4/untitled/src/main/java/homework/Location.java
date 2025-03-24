package homework;

/**
 * Location Class
 */
public class Location implements Comparable<Location>
{
    /**
     * Location name
     */
    private String name;
    /**
     * Location type: FRIENDLY, NORMAL, ENEMY
     */
    private LocationType type;

    /**
     * Constructor of an object of type Location
     * @param name String: Name of the location
     * @param type LocationType: Location type
     */
    public Location(String name, LocationType type) {
        this.name = name;
        this.type = type;
    }

    /**
     * getter for the name
     * @return String: Location name
     */
    public String getName() {
        return name;
    }

    /**
     * getter for the location type
     * @return LocationType: Location type
     */
    public LocationType getType() {
        return type;
    }

    /**
     * Overriden compareTo method:
     * @param location the object to be compared.
     * @return int: Any integer if the name of the location is different with the parameter given, 0 otherwise
     */
    @Override
    public int compareTo(Location location) {
        return this.name.compareTo(location.getName());
    }

    /**
     * Overriden toString method
     * @return String: returns all information about the Location object
     */
    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
