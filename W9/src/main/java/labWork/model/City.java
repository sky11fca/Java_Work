package labWork.model;

import javax.persistence.*;

@Entity
@Table(name = "city")
@NamedQuery(name = "City.findByName",
        query = "SELECT c FROM City c WHERE c.name LIKE :name")

public class City
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;


    private boolean capital;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longidude;

    public City() {
    }

    public City(String name, Country country, boolean capital, double latitude, double longitude)
    {
        this.name = name;
        this.country = country;
        this.capital = capital;
        this.latitude = latitude;
        this.longidude = longitude;
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

    public double getLongidude() {
        return longidude;
    }

    public void setLongitude(double longidude) {
        this.longidude = longidude;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, (%s, %s) %s", name, country.getName(), latitude, longidude, capital ? "[Capital]" : "");
    }
}
