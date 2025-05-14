package homework.persistence;

import homework.model.City;
import homework.model.Country;

import javax.persistence.EntityManager;
import java.util.List;

public class CityRepo extends AbstractRepo<City>
{
    public CityRepo() {
        super(City.class);
    }
}
