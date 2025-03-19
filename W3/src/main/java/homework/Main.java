package homework;

import java.time.LocalTime;
import java.util.*;

/**
 * The main object
 */
public class Main
{
    /**
     * The program's entry point
     * @param args String[]: The given command line arguments
     */
    public static void main(String[] args)
    {
        Airliner a1 = new Airliner("A1", "model1", 3,300, 250);
        Airliner a2 = new Airliner("A2", "model2", 3, 300, 150);
        Airliner a3 = new Airliner("A3", "model3", 5, 300, 100);

        Freighter f1 = new Freighter("F1", "model1", 1,300.0, 20);
        Freighter f2 = new Freighter("F2", "model2", 2,300.0, 50);
        Freighter f3 = new Freighter("F3", "model3", 3, 300.0, 90);

        Drone d1 = new Drone("D1", "model1", 5,50, 100, 24);
        Drone d2 = new Drone("D2", "model2", 9, 50, 100, 24);
        Drone d3 = new Drone("D3", "model3", 8, 50, 100, 72);

        List<Aircraft> cargoAircrafts = new ArrayList<>();

        cargoAircrafts.add(f1);
        cargoAircrafts.add(f2);
        cargoAircrafts.add(f3);

        List<Runway> runways = new ArrayList<>();
        runways.add(new Runway(1));
        runways.add(new Runway(2));
        runways.add(new Runway(3));
        runways.add(new Runway(4));
        runways.add(new Runway(5));

        Airport airport = new Airport(runways);

        Set<Flight> flights = new HashSet<>();
        flights.add(new Flight("F1", a1, new Pair<>(LocalTime.of(10,0), LocalTime.of(10, 30))));
        flights.add(new Flight("F2", a2, new Pair<>(LocalTime.of(9,0), LocalTime.of(10, 15))));
        flights.add(new Flight("F3", a3, new Pair<>(LocalTime.of(11,0), LocalTime.of(12, 30))));

        flights.add(new Flight("F4", f1, new Pair<>(LocalTime.of(13,0), LocalTime.of(14, 30))));
        flights.add(new Flight("F5", f2, new Pair<>(LocalTime.of(17,0), LocalTime.of(18, 30))));
        flights.add(new Flight("F6", f3, new Pair<>(LocalTime.of(16,0), LocalTime.of(17, 30))));

        flights.add(new Flight("F7", d1, new Pair<>(LocalTime.of(19,0), LocalTime.of(19, 30))));
        flights.add(new Flight("F8", d2, new Pair<>(LocalTime.of(20,0), LocalTime.of(21, 30))));
        flights.add(new Flight("F9", d3, new Pair<>(LocalTime.of(0,0), LocalTime.of(2, 30))));

        Solver solution = new Solver(airport, flights);

        Map<Flight, Runway> schedule = solution.solve();

        for(Map.Entry<Flight, Runway> entry : schedule.entrySet())
        {
            System.out.println(entry.getKey() + " Assigned to " + entry.getValue());
        }

    }
}
