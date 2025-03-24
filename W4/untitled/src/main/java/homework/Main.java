package homework;


import com.github.javafaker.Faker;
import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Main Function Class
 */
public class Main
{
    /**
     * Program entry point
     * @param args String[]: Array of arguments given to the command prompt
     */
    public static void main(String[] args)
    {
        //THE LIST OF LOCATIONS
        Location[] locations = {
                new Location("Hill", LocationType.FRIENDLY),
                new Location("Water Temple", LocationType.FRIENDLY),
                new Location("Desert", LocationType.NEUTRAL),
                new Location("Haunted Manor", LocationType.NEUTRAL),
                new Location("Underwater", LocationType.NEUTRAL),
                new Location("City", LocationType.NEUTRAL),
                new Location("Forrest", LocationType.NEUTRAL),
                new Location("Mountain", LocationType.ENEMY),
                new Location("Lava Land", LocationType.ENEMY),
                new Location("Lava Temple", LocationType.ENEMY),
                new Location("NULL", LocationType.ENEMY),
        };

        List<Location> locationList = Arrays.asList(locations);

        //USING STREAMS TO PUT IN A TREESET ALL FRIENDLY LOCATIOMS
        System.out.println("Printing the friendly locations set");
        Set<Location> friendlyLocations = locationList.stream()
                .filter(l -> l.getType() == LocationType.FRIENDLY)
                .collect(Collectors.toCollection(TreeSet::new));

        friendlyLocations.forEach(System.out::println);

        //USING STREAMS TO PUT IN A LINKED LIST ALL ENEMY LOCATIONS AND SORT BY TYPE AND NAME
        System.out.println("Printing the enemy locations set");
        List<Location> enemyLocations = locationList.stream()
                .filter(loc -> loc.getType()==LocationType.ENEMY)
                .sorted(Comparator.comparing(Location::getType).thenComparing(Location::getName))
                .collect(Collectors.toCollection(LinkedList::new));

        enemyLocations.forEach(System.out::println);

        //GENERATING NEW LOCATIONS NAME WITH JAVA FAKER;

        Faker faker = new Faker();

        LocationType[] types = LocationType.values();

        Location[] randomLocations = {
                new Location("Hogwards", LocationType.FRIENDLY),
                new Location(faker.harryPotter().location(), types[(int)(Math.random() * types.length)]),
                new Location(faker.harryPotter().location(), types[(int)(Math.random() * types.length)]),
                new Location(faker.harryPotter().location(), types[(int)(Math.random() * types.length)])
        };

        List<Location> randomLocationList = Arrays.asList(randomLocations);

        randomLocationList.forEach(System.out::println);
        //USING LIBRARY JGRAPHT TO DETERMINE THE FASTEST ROUTE STARTING FROM HOGWARDS
        //CONSTRUCTING THE GRAPH
        Graph<Location, DefaultWeightedEdge> hpGraph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
        Location start = randomLocationList.get(0);

        hpGraph.addVertex(randomLocationList.get(0));
        hpGraph.addVertex(randomLocationList.get(1));
        hpGraph.addVertex(randomLocationList.get(2));
        hpGraph.addVertex(randomLocationList.get(3));

        DefaultWeightedEdge e1 = hpGraph.addEdge(randomLocationList.get(0), randomLocationList.get(1));
        DefaultWeightedEdge e2 = hpGraph.addEdge(randomLocationList.get(0), randomLocationList.get(2));
        DefaultWeightedEdge e3 = hpGraph.addEdge(randomLocationList.get(0), randomLocationList.get(3));
        DefaultWeightedEdge e4 = hpGraph.addEdge(randomLocationList.get(2), randomLocationList.get(1));
        DefaultWeightedEdge e5 = hpGraph.addEdge(randomLocationList.get(3), randomLocationList.get(1));

        hpGraph.setEdgeWeight(e1, 5.0);
        hpGraph.setEdgeWeight(e2, 10.0);
        hpGraph.setEdgeWeight(e3, 1.0);
        hpGraph.setEdgeWeight(e4, 4.0);
        hpGraph.setEdgeWeight(e5, 3.0);

        //DECLARING THE DIJKSTRA ALGORITHM
        DijkstraShortestPath<Location, DefaultWeightedEdge> dijkstra = new DijkstraShortestPath<>(hpGraph);
        //FOR EACH LOCATION, WILL PRINT OUT ALL SHORTEST PATH FROM STARTING LOCATION TO EVERY OTHER LOCATION IN THE LIST
        randomLocationList.forEach(loc -> System.out.println(randomLocationList.get(0).getName()  + " -> " +loc.getName()+ ": " + dijkstra.getPathWeight(randomLocationList.get(0), loc)));

        //USING STREAM API TO CREATE A DATA STRUCTURE TO STORE LOCATION GROUPED BY LOCATION TYPE

        Map<LocationType, List<Location>> locationsByType = randomLocationList.stream().collect(Collectors.groupingBy(Location::getType));

        locationsByType.forEach((type, loc) -> System.out.println(type + " " + loc));

        //DISPLAYING THE SHORTEST TIME PATH FROM START TO ALL LOCATIONS BY TYPE

        locationsByType.forEach((type, loc) -> {
            System.out.println(type + " locations: ");
            loc.forEach(locate -> {
                double time = dijkstra.getPathWeight(start, locate);
                System.out.println(locate.getName() + ": " + time);
            });
        });
    }
}
