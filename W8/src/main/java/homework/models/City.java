package homework.models;

public class City
{
    private int id;
    private String name;
    private Country country;
    private boolean capital;
    private double latitude;
    private double longitude;

    public City(int id, String name, Country country, boolean capital, double latitude, double longitude)
    {
        this.id = id;
        this.name = name;
        this.country = country;
        this.capital = capital;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public boolean isCapital() {
        return capital;
    }

    public void setCapital(boolean capital) {
        this.capital = capital;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, (%s, %s) %s", name, country.getName(), latitude, longitude, capital ? "[Capital]" : "");
    }
}
