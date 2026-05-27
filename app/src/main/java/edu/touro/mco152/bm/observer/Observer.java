package edu.touro.mco152.bm.observer;

import edu.touro.mco152.bm.persist.DiskRun;

/**
 * Observs Subject and updates when notified.
 * <br>
 * Implemented clases will be notified when a benchmark is complete
 */
public interface Observer {
    void update(DiskRun run);
}
