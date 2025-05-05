package labWork.model;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "continents")
@NamedQuery(name = "Continents.findByName", query="SELECT c FROM Continents c where c.name LIKE :name")

public class Continents
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy="continent", cascade = CascadeType.ALL)
    private List<Country> countries;

    public Continents() {
    }

    public Continents(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
