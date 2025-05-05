package labWork.persistence;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceManager {

    private static EntityManagerFactory emf;

    public PersistenceManager() {
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("WorldCitiesPU");
        }
        return emf;
    }

    public static void closeEntityManagerFactory()
    {
        if(emf != null && emf.isOpen())
        {
            emf.close();
        }
    }
}
