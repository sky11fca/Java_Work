package labWork.model;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "countries")
@NamedQuery(name = "Country.findByName", query = "SELECT c FROM Country c Where c.name LIKE :name")

public class Country
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true, length = 3)
    private String code;

    @ManyToOne
    @JoinColumn(name="continent_id", nullable = false)
    private Continents continent;

    @OneToMany(mappedBy="country", cascade=CascadeType.ALL)
    private List<City> cities;

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

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", continent=" + continent +
                '}';
    }
}
