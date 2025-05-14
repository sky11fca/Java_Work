package homework.persistence;

import homework.model.Continents;
import homework.model.Country;

import javax.persistence.EntityManager;
import java.util.List;

public class CountryRepo extends AbstractRepo<Country>
{
    public CountryRepo() {
        super(Country.class);
    }
}
