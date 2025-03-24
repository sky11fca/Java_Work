package labWork;


import org.w3c.dom.ls.LSOutput;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Main
{
    public static void main(String[] args)
    {
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

        TreeSet<Location> friendlyLocation = Arrays.stream(locations).filter(location -> location.getType()==LocationType.FRIENDLY).collect(Collectors.toCollection(TreeSet::new));
        System.out.println("All friendly locations by name: ");
        friendlyLocation.forEach(System.out::println);

        LinkedList<Location> enemyLocation = Arrays.stream(locations).filter(location -> location.getType()==LocationType.ENEMY).sorted(Comparator.comparing(Location::getName)).collect(Collectors.toCollection(LinkedList::new));

        System.out.println("Enemy locations: ");

        enemyLocation.forEach(System.out::println);
    }
}
