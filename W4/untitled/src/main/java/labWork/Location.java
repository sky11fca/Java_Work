package labWork;

public class Location implements Comparable<Location>
{
    private String name;
    private LocationType type;

    public Location(String name, LocationType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public LocationType getType() {
        return type;
    }

    @Override
    public int compareTo(Location location) {
        return this.name.compareTo(location.getName());
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
