package homework.persistence;

import homework.model.Continents;
import homework.model.Country;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Level;

public class CountryRepo extends AbstractRepo<Country>
{
    public CountryRepo() {
        super();
    }

    public List<Country> findByContinentName(String continentName)
    {
        EntityManager entityManager = getEntityManager();
        try{
            TypedQuery<Country> query = entityManager.createQuery("SELECT c from Country c Where c.continent.name = :continentName", Country.class);
            query.setParameter("continentName", continentName);
            return query.getResultList();
        }
        finally{
            entityManager.close();
        }
    }

    public Country findByCode(String code)
    {
        EntityManager entityManager = getEntityManager();
        try{
            TypedQuery<Country> query = entityManager.createNamedQuery("Country.findByCode", Country.class);
            query.setParameter("code", code);
            return query.getSingleResult();
        }
        catch (Exception e)
        {
            logger.log(Level.SEVERE, e.getMessage());
            throw new RuntimeException(e);
        }
        finally {
            entityManager.close();
        }
    }
}
