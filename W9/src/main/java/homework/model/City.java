package homework.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "city")

@NamedQueries({
        @NamedQuery(name = "City.findByName", query = "SELECT c FROM City c WHERE c.name LIKE :name"),
        @NamedQuery(name = "City.findByCapital", query = "SELECT c FROM City c WHERE c.capital = true")
})



public class City
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 50)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;


    private boolean capital;

    @Column(nullable = false, precision = 10, scale = 7)
    private double latitude;

    @Column(nullable = false, precision = 10, scale = 7)
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
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return id == city.id && capital == city.capital && Double.compare(latitude, city.latitude) == 0 && Double.compare(longidude, city.longidude) == 0 && Objects.equals(name, city.name) && Objects.equals(country, city.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, country, capital, latitude, longidude);
    }

    @Override
    public String toString() {
        return "City{" + "id=" + id + ", name='" + name + '\'' +
                ", capital=" + capital + ", latitude=" + latitude +
                ", longitude=" + longidude + '}';
    }
}
