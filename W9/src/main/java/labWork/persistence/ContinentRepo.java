package labWork.persistence;

import labWork.model.Continents;

import javax.persistence.EntityManager;
import java.util.Optional;

public class ContinentRepo extends AbstractRepo<Continents>
{
    public ContinentRepo() {
        super(Continents.class);
    }

    public Optional<Continents> findByNameExact(String name)
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.createQuery("SELECT c FROM Continents c WHERE c.name = :name", Continents.class).setParameter("name", name).getResultStream().findFirst();
        }
        finally
        {
            em.close();
        }
    }
}
