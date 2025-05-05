package labWork.persistence;

import labWork.model.Continents;
import labWork.model.Country;

import javax.persistence.EntityManager;
import java.util.List;

public class CountryRepo extends AbstractRepo<Country>
{
    public CountryRepo() {
        super(Country.class);
    }

    public List<Country> findByContinent(Continents continent)
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.createQuery("SELECT c from Country c WHERE c.continent = :continent", Country.class).setParameter("continent", continent).getResultList();
        }
        finally
        {
            em.close();
        }
    }

}
