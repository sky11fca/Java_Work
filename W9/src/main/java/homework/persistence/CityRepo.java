package homework.persistence;

import homework.model.City;
import homework.model.Country;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Level;

public class CityRepo extends AbstractRepo<City>
{
    public CityRepo() {
        super();
    }

    public List<City> findByCountryName(String countryName)
    {
        EntityManager entityManager = getEntityManager();
        try{
            TypedQuery<City> query = entityManager.createQuery("SELECT c from City c where c.country.name = :countryName", City.class);
            query.setParameter("countryName", countryName);
            return query.getResultList();
        }
        finally{
            entityManager.close();
        }
    }

    public List<City> findByCapital() {
        EntityManager entityManager = getEntityManager();
        try{
            TypedQuery<City> query = entityManager.createNamedQuery("City.findByCapital", City.class);
            return query.getResultList();
        }
        catch(Exception e){
            logger.log(Level.SEVERE, e.getMessage());
            throw new RuntimeException(e);
        }
        finally{
            entityManager.close();
        }
    }
}
