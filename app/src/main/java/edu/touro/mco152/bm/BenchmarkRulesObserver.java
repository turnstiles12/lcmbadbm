package edu.touro.mco152.bm;

import edu.touro.mco152.bm.externalsys.SlackManager;
import edu.touro.mco152.bm.observer.Observer;
import edu.touro.mco152.bm.persist.DiskRun;

/**
 * Enforces benchmark rules when benchmark is finished
 */
public class BenchmarkRulesObserver implements Observer{
    private static final double THRESHOLD = 1.03;

    @Override
    public void update(DiskRun run) {
        if (run.getIoMode() == DiskRun.IOMode.READ && run.getRunMax() > run.getRunAvg() * THRESHOLD) {
            SlackManager manager = new SlackManager("BadBM");
            manager.postMsg2OurChannel(":smile: Read benchmark max (" + run.getRunMax() + "Mb/s)"
        + " is >3% above average (" + run.getRunAvg() + ")");
        }
    }
    
}
