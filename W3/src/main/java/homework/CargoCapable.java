package homework;

/**
 * Interface for CargoCapable aircraft
 */
public interface CargoCapable
{
    /**
     * Getter for the CargoCapable aircraft capacity
     * @return null
     */
    double getCapacity();

    /**
     * Setter for the Cargo Capable aircraft capacity
     * @param capacity double: max capacity of the aircraft
     */
    void setCapacity(double capacity);

    /**
     * Default boolean method for cargo capable aircrafts
     * @return true
     */
    default boolean isPassengerCapable(){
        return true;
    }
}
