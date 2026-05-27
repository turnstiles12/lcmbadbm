package edu.touro.mco152.bm.persist;

import edu.touro.mco152.bm.observer.Observer;
import jakarta.persistence.EntityManager;

/**
 * Observer that saves completed benchmark data
 */
public class DatabasePersistenceObserver implements Observer {
    public void update(DiskRun run) {
        EntityManager em = EM.getEntityManager();
        em.getTransaction().begin();
        em.persist(run);
        em.getTransaction().commit();
    }
}
