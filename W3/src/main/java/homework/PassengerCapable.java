package homework;

/**
 * Interface for Passenger Capable aircraft
 */
public interface PassengerCapable
{
    /**
     * Getter for the passenger capable aircrafts nr of seats
     * @return null;
     */
    int getSeats();

    /**
     * setter for the passenger capable aircraft nr of seats
     * @param seats int: total seats the aircraft provides
     */
    void setSeats(int seats);

    /**
     * Default method for Passenger Capable aircrafts
     * @return true
     */
    default boolean isPassengerCapable(){
        return true;
    }


}
