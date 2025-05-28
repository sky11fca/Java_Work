package homework.model;

import homework.model.City;
import homework.model.Continents;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "countries")

@NamedQueries({
        @NamedQuery(name = "Country.findByName", query = "SELECT c FROM Country c Where c.name LIKE :name"),
        @NamedQuery(name = "Country.findByCode", query = "SELECT c FROM Country c WHERE c.code = :code")
})



public class Country
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, unique = true, length = 3)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="continent_id", nullable = false)
    private Continents continent;

    @OneToMany(mappedBy="country", cascade=CascadeType.ALL, orphanRemoval = true)
    private List<City> cities = new ArrayList<>();

    public Country() {
    }

    public Country(String name, String code, Continents continent) {
        this.name = name;
        this.code = code;
        this.continent = continent;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Continents getContinent() {
        return continent;
    }

    public void setContinent(Continents continent) {
        this.continent = continent;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public void addCity(City city) {
        cities.add(city);
        city.setCountry(this);
    }

    public void removeCity(City city) {
        cities.remove(city);
        city.setCountry(null);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return id == country.id && Objects.equals(name, country.name) && Objects.equals(code, country.code) && Objects.equals(continent, country.continent) && Objects.equals(cities, country.cities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, continent, cities);
    }

    @Override
    public String toString() {
        return "Country{" + "id=" + id + ", name='" + name + '\'' +
                ", code='" + code + '\'' + '}';
    }
}
