package homework;

/**
 * Abstract Aircraft class
 */
abstract class Aircraft implements Comparable<Aircraft>
{
    /**
     * Name of the aircraft
     */
    protected String name;
    /**
     * Model of the aircraft
     */
    protected String model;
    /**
     * The tail number
     */
    protected int tailNumber;

    /**
     * Constructor of the aircraft
     *
     * @param name       String: Aircraft name
     * @param model      String: Aircraft model
     * @param tailNumber int: Aircraft's tail number
     */
    public Aircraft(String name, String model, int tailNumber) {
        this.name = name;
        this.model = model;
        this.tailNumber = tailNumber;
    }

    /**
     * getter for the aircraft name
     * @return String: aircraft name
     */

    public String getName() {
        return name;
    }

    /**
     * Setter for the aircraft name
     * @param name String: aircraft name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the aircraft model
     * @return String aircraft model
     */
    public String getModel() {
        return model;
    }

    /**
     * Setter for the aircraft model
     * @param model String aircraft model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Getter for the aircraft's tail number
     * @return int: aircraft's tail number
     */
    public int getTailNumber() {
        return tailNumber;
    }

    /**
     * Setter for the aircraft's tail number
     * @param tailNumber int: aircraft's tail number
     */

    public void setTailNumber(int tailNumber) {
        this.tailNumber = tailNumber;
    }

    /**
     * Overriden toString method
     * @return String: Details of the Aircraft object
     */
    @Override
    public String toString() {
        return "Aircraft{" +
                "name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", tailNumber=" + tailNumber +
                '}';
    }

    /**
     * Overriden compareTo method
     * @param o Aircraft: an aircraft object
     * @return int: Any int if an object is lexico-gramaticaly different, 0 if is the same
     */
    @Override
    public int compareTo(Aircraft o)
    {
        return this.model.compareTo(o.getModel());
    }
}
