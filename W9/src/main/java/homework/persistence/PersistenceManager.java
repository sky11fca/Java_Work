package homework.persistence;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceManager
{
    private static final Logger logger = Logger.getLogger(PersistenceManager.class.getName());
    private static EntityManagerFactory emf;

    public PersistenceManager() {
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        if (emf == null) {
           try{
               emf = Persistence.createEntityManagerFactory("WorldCitiesPU");
               logger.info("EntityManagerFactory created");
           }
           catch (Exception e) {
               logger.log(Level.SEVERE, "Error creating EntityManagerFactory", e);
               throw new RuntimeException(e);
           }

        }
        return emf;
    }

    public static void closeEntityManagerFactory()
    {
        if(emf != null && emf.isOpen())
        {
            emf.close();
            emf = null;
            logger.info("EntityManagerFactory closed");
        }
    }
}
