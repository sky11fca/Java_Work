package homework;

/**
 * Freighter class derived from Aircraft and implements CargoCapable interface
 */
public class Freighter extends Aircraft implements CargoCapable
{
    /**
     * The capacity of the Freighter in Tons
     */
    private double capacity;
    private int wingSpan;

    /**
     * Freighter Constructor
     * @param name String: aircraft name
     * @param model String: aircraft model
     * @param tailNumber int: The aircraft tail number
     * @param capacity double: Freighter capacity
     * @param wingSpan int: Freighter wing durability
     */
    public Freighter(String name, String model, int tailNumber, double capacity, int wingSpan) {
        super(name, model, tailNumber);
        this.capacity = capacity;
        this.wingSpan = wingSpan;
    }

    /**
     * Getter for the Freighter capacity overwritten from the Interface
     * @return double: Freighter capacity
     */
    @Override
    public double getCapacity() {
        return capacity;
    }

    /**
     * Setter for the Freighter capacity overwritten from the Interface
     * @param capacity double: Freighter Capacity
     */
    @Override
    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    /**
     * Setter for the Freighter wing durability
     * @return int: Wing life span
     */
    public int getWingSpan() {
        return wingSpan;
    }

    /**
     * Setter for Freighter wing durability
     * @param wingSpan int: Wing life span
     */
    public void setWingSpan(int wingSpan) {
        this.wingSpan = wingSpan;
    }

    /**
     * Overriden toString method
     * @return String: Details about the Freighter object
     */
    @Override
    public String toString() {
        return "Freighter{" +
                "capacity=" + capacity +
                ", wingSpan=" + wingSpan +
                '}' + super.toString();
    }
}
