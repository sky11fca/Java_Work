package homework.persistence;

import homework.persistence.PersistenceManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.lang.reflect.ParameterizedType;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.logging.Level;

//import static jdk.internal.net.http.common.Log.logError;


public abstract class AbstractRepo<T>
{
    protected static final Logger logger = Logger.getLogger(AbstractRepo.class.getName());
    private final Class<T> entityClass;

    @SuppressWarnings("unchecked")
    protected AbstractRepo()
    {
        this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected EntityManager getEntityManager(){
        return PersistenceManager.getEntityManagerFactory().createEntityManager();
    }

   public T create(T entity) {
        EntityManager em =null;
        try{
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            logger.info("Entity " + entityClass.getName() + " created");

            return entity;
        }
        catch(Exception e){
            logger.log(Level.SEVERE, e.getMessage());
            if(em != null && em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw new RuntimeException(e);
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
        catch(Exception e){
            logger.log(Level.SEVERE, e.getMessage());
            throw new RuntimeException(e);
        }
        finally{
            em.close();
        }
   }

   public List<T> findByName(String name) {
        EntityManager em = getEntityManager();
        try{
            Instant start = Instant.now();
            TypedQuery<T> query = em.createNamedQuery(entityClass.getSimpleName() + ".findByName", entityClass);
            query.setParameter("name", "%"+name+"%");
            List<T> list = query.getResultList();

            Duration duration = Duration.between(start, Instant.now());
            logger.info("Time taken to find " + name + " in " + duration.toMillis() + " ms");
            return list;
        }
        catch(Exception e){
            logger.log(Level.SEVERE, e.getMessage());
            throw new RuntimeException(e);
        }
        finally{
            em.close();
        }
   }

   public T update(T entity) {
        EntityManager em = null;

        try{
            em = getEntityManager();
            em.getTransaction().begin();
            T merger = em.merge(entity);
            em.getTransaction().commit();
            logger.info("Entity " + entityClass.getName() + " updated");
            return merger;
        }
        catch(Exception e){
            logger.log(Level.SEVERE, e.getMessage());
            if(em != null && em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw new RuntimeException(e);
        }
        finally{
            if(em != null){
                em.close();
            }
        }
   }

   public void delete(Object id) {
        EntityManager em = null;
        try{
            em = getEntityManager();
            em.getTransaction().begin();
            T entity = em.find(entityClass, id);
            if(entity != null){
                em.remove(entity);
                em.getTransaction().commit();
                logger.info("Entity " + entityClass.getName() + " deleted");
            }
        }
        catch(Exception e){
            logger.log(Level.SEVERE, e.getMessage());
            if(em != null && em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw new RuntimeException(e);
        }
        finally{
            if(em != null){
                em.close();
            }
        }
   }

   public List<T> findAll() {
        EntityManager em = getEntityManager();
        try{
            Instant start = Instant.now();
            TypedQuery<T> query = em.createNamedQuery(entityClass.getSimpleName() + ".findAll", entityClass);
            List<T> list = query.getResultList();
            Duration duration = Duration.between(start, Instant.now());
            logger.info("Time taken to find " + entityClass.getName() + " in " + duration.toMillis() + " ms");
            return list;
        }
        catch(Exception e){
            logger.log(Level.SEVERE, e.getMessage());
            throw new RuntimeException(e);
        }
        finally{
            em.close();
        }
   }
}
