package homework.model;

import homework.model.Country;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "continents")


@NamedQueries({
        @NamedQuery(name = "Continents.findByName", query="SELECT c FROM Continents c where c.name LIKE :name"),
        @NamedQuery(name = "Continents.findAll", query="SELECT c FROM Continents c")
})


public class Continents
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @OneToMany(mappedBy="continent", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<Country> countries = new ArrayList<>();

    public Continents() {
    }

    public Continents(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public String getName() {
        return name;
    }

    public void addCountry(Country country) {
        countries.add(country);
        country.setContinent(this);
    }

    public void removeCountry(Country country) {
        countries.remove(country);
        country.setContinent(null);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Continents that = (Continents) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(countries, that.countries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, countries);
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
