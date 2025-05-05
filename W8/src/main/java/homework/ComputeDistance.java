package homework;

import homework.models.City;

public class ComputeDistance
{
    private static final double EARTH_RADIUS_KM = 6378.137;

    public static double calculateDistance(City city1, City city2)
    {
        double lat1 = city1.getLatitude();
        double lng1 = city1.getLongitude();
        double lat2 = city2.getLatitude();
        double lng2 = city2.getLongitude();

        double dlat = lat2 - lat1;
        double dlng = lng2 - lng1;

        double a = Math.pow(Math.sin(dlat / 2), 2)+
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.pow(Math.sin(dlng / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS_KM * c;
    }

    public static String getFormattedDistance(City city1, City city2)
    {
        double distance = calculateDistance(city1, city2);
        return String.format("Distance between %s and %s is: %.2f",city1.getName(), city2.getName(), distance);
    }
}
