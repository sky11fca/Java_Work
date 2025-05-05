package labWork.persistence;

import labWork.model.City;
import labWork.model.Country;

import javax.persistence.EntityManager;
import java.util.List;

public class CityRepo extends AbstractRepo<City>
{
    public CityRepo() {
        super(City.class);
    }

    public List<City> findByCountry(Country country)
    {
        EntityManager em = getEntityManager();

        try
        {
            return em.createQuery("SELECT c FROM City c WHERE c.country = :country", City.class).setParameter("country", country).getResultList();
        }
        finally
        {
            em.close();
        }

    }

    public List<City> findCapitals()
    {
        EntityManager em = getEntityManager();

        try
        {
            return em.createQuery("SELECT c FROM City c WHERE c.capital = true", City.class).getResultList();
        }
        finally
        {
            em.close();
        }

    }
}
