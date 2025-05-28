package labWork.Entities;

import javax.persistence.*;

@Entity
@Table(name = "city")

public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean capital;

    private Double latitude;
    private Double longitude;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    public City() {
    }

    public City(long id, String name, boolean capital, Double latitude, Double longitude, Country country) {
        this.id = id;
        this.name = name;
        this.capital = capital;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCapital() {
        return capital;
    }

    public void setCapital(boolean capital) {
        this.capital = capital;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
