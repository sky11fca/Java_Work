package homework;


/**
 * Class of the object of type Runway
 */
public class Runway
{
    /**
     * The runway ID;
     */
    private int id;

    /**
     * Constructor of the object of type Runway
     * @param id int: Runway ID;
     */
    public Runway(int id) {
        this.id = id;
    }

    /**
     * Getter for the Runway ID
     * @return int: The Runway ID
     */
    public int getId() {
        return id;
    }

    /**
     * Overriden toString method
     * @return String: Details about the Runway object
     */
    @Override
    public String toString() {
        return "Runway{" +
                "id=" + id +
                '}';
    }
}
