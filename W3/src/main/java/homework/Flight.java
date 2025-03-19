package homework;

import java.time.LocalTime;

/**
 * Flight class
 */
public class Flight
{
    /**
     * Flight ID
     */
    private String flightID;
    /**
     * The aircraft which is scheduled to land
     */
    private Aircraft aircraft;
    /**
     * The interval which the aircraft should land
     */
    private Pair<LocalTime, LocalTime> timePair;

    /**
     * Constructor of an object of type Flight
     * @param flightID String: The Flight's id
     * @param aircraft Aircraft: The aircraft scheduled to land
     * @param timePair Pair: The time interval which is supposed to land
     */
    public Flight(String flightID, Aircraft aircraft, Pair<LocalTime, LocalTime> timePair) {
        this.flightID = flightID;
        this.aircraft = aircraft;
        this.timePair = timePair;
    }

    /**
     * Getter for the flight id
     * @return String: The id of the Flight
     */

    public String getFlightID() {
        return flightID;
    }

    /**
     * Getter for the aircraft
     * @return Aircraft: The aircraft object
     */

    public Aircraft getAircraft() {
        return aircraft;
    }

    /**
     * Getter for the Time interval
     * @return Pair: Time interval for landing
     */

    public Pair<LocalTime, LocalTime> getTimePair() {
        return timePair;
    }

    /**
     * Special method to detect conflicting flight landings
     * We say 2 intervals are in conflict if for any 2 time intervals are overlapping (The interval intersect)
     * @param f Flight: Another flight
     * @return boolean: <p>true: both flights have overlapping time intervals;</p> <p>false: otherwise</p>
     */
    public boolean hasConflict(Flight f)
    {
        return !(this.timePair.getFirst().isBefore(f.getTimePair().getSecond())) || !(f.getTimePair().getSecond().isBefore(this.timePair.getSecond()));
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightID='" + flightID + '\'' +
                ", aircraft=" + aircraft +
                ", timePair=" + timePair +
                '}';
    }
}
