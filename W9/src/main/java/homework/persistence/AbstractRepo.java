package homework.persistence;

import homework.persistence.PersistenceManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.logging.Level;

//import static jdk.internal.net.http.common.Log.logError;


public abstract class AbstractRepo<T>
{
    private static final Logger logger = Logger.getLogger(AbstractRepo.class.getName());
    private final Class<T> entityClass;

    protected AbstractRepo(Class<T> entityClass)
    {
        this.entityClass = entityClass;
    }

    protected EntityManager getEntityManager(){
        return PersistenceManager.getEntityManagerFactory().createEntityManager();
    }

   public void create(T entity) {
        EntityManager em =null;
        try{
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            logger.info("Entity " + entityClass.getName() + " created");
        }
        catch(Exception e){
            logger.log(Level.SEVERE, e.getMessage());
            if(em != null && em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw e;
        }
        finally{
            if(em != null){
                em.close();
            }
        }
   }

   public T findById(Object id) {
        EntityManager em = getEntityManager();
        try{
            return em.find(entityClass, id);
        }
        finally{
            em.close();
        }
   }

   public List<T> findByName(String name) {
        EntityManager em = getEntityManager();
        try{
            String querryName = entityClass.getSimpleName() + ".findByName";
            TypedQuery<T> query = em.createNamedQuery(querryName, entityClass);
            query.setParameter("name", "%"+name+"%");
            return query.getResultList();
        }
        finally{
            em.close();
        }
   }
}
