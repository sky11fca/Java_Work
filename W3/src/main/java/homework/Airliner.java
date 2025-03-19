package homework;

/**
 * Airliner Class that is derived from Aircrafts and implements Passenger Capable interface
 */
public class Airliner extends Aircraft implements PassengerCapable
{
    /**
     * Number of seats
     */
    private int seats;
    /**
     * Number of passengers
     */
    private int nrOfPassengers;

    /**
     * Airliner constructor
     * @param name String: Aircraft name
     * @param model String: Aircraft type
     * @param tailNumber int: Aircraft tail number
     * @param seats int: Airliner seats
     * @param nrOfPassengers int: Airliner current passengers
     */
    public Airliner(String name, String model, int tailNumber,int seats, int nrOfPassengers) {
        super(name, model, tailNumber);
        this.seats = seats;
        this.nrOfPassengers = nrOfPassengers;
    }

    /**
     * Getter for the Airliner overwritten from the interface
     * @return int nr of seats
     */
    @Override
    public int getSeats() {
        return seats;
    }

    /**
     * Setter for the Airliner, overwritten from the interface
     * @param seats int: nr of seats
     */
    @Override
    public void setSeats(int seats) {
        this.seats = seats;
    }

    /**
     * Getter for airliner current passengers
     * @return int: number of current passengers
     */
    public int getNrOfPassengers() {
        return nrOfPassengers;
    }

    /**
     * Setter for the airliner current passengers
     * @param nrOfPassengers int: number of passengers
     */
    public void setNrOfPassengers(int nrOfPassengers) {
        this.nrOfPassengers = nrOfPassengers;
    }

    /**
     * Overriden toString method
     * @return Details about the Airliner object
     */
    @Override
    public String toString() {
        return "Airliner{" +
                "seats=" + seats +
                ", nrOfPassengers=" + nrOfPassengers +
                '}' + super.toString();
    }
}
