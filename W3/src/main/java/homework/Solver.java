package homework;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The problem object
 */
public class Solver
{
    /**
     * Airport object that contains the list ov available runways
     */
    private Airport airport;
    /**
     * Set of distinct Flights
     */
    private Set<Flight> flights;

    /**
     * Problem constructor
     * @param airport Airport: The airport of assigned runways
     * @param flights Set: avalable flights
     */
    public Solver(Airport airport, Set<Flight> flights) {
        this.airport = airport;
        this.flights = flights;
    }

    /**
     * The problem solution of assigning flights to runway
     * @return Mapping Flight -> Runway: The scheduled flight to runway
     *  <p></p>
     * <strong>HOW THIS ALGORITHM WORKS</strong>
     * <ul>
     *     <li>For each flight from the set</li>
     *     <li>For each runway assigned by the Airport airport</li>
     *     <li>We map a flight to an available runway</li>
     *     <li>For each mapped entry</li>
     *     <li>if there is an already entry that has the same runway but is in conflict with the current flight, the flight will be mapped with next runway</li>
     * </ul>
     *
     * <strong>TIME COMPLEXITY</strong>
     * <p>The algorithm goes through all flights and runways</p>
     * As such, the time complexity is at most O(n*m)
     */
    public Map<Flight, Runway> solve()
    {
        Map<Flight, Runway> schedule = new HashMap<>();

        List<Runway> runways = airport.getRunways();

        for(Flight flight : flights)
        {
            for (Runway runway : runways)
            {
                boolean conflict = false;
                for(Map.Entry<Flight, Runway> entry : schedule.entrySet())
                {
                    if(entry.getValue().equals(runway) && entry.getKey().hasConflict(flight))
                    {
                        conflict = true;
                        break;
                    }
                }
                if(!conflict)
                {
                    schedule.put(flight, runway);
                    break;
                }
            }
        }

        return schedule;
    }
}
