package labWork.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

public abstract class AbstractRepo<T>
{
    private final Class<T> entityClass;

    protected AbstractRepo(Class<T> entityClass)
    {
        this.entityClass = entityClass;
    }

    protected EntityManager getEntityManager()
    {
        return PersistenceManager.getEntityManagerFactory().createEntityManager();
    }

    public void create(T entity)
    {
        EntityManager em = getEntityManager();
        EntityTransaction tx = null;

        try
        {
            tx = em.getTransaction();
            tx.begin();
            em.persist(entity);
            tx.commit();
        }
        catch (Exception e)
        {
            if(tx!=null && tx.isActive())
            {
                tx.rollback();
            }

            throw e;
        }
        finally
        {
            em.close();
        }
    }

    public Optional<T> findById(Long id)
    {
        EntityManager em = getEntityManager();
        T entity = em.find(entityClass, id);
        em.close();
        return Optional.ofNullable(entity);
    }

    public List<T> findByName(String name)
    {
        EntityManager em = getEntityManager();
        List<T> result = em.createNamedQuery(entityClass.getSimpleName() +".findByName", entityClass).setParameter("name", "%" + name + "%").getResultList();
        em.close();
        return result;
    }
}
