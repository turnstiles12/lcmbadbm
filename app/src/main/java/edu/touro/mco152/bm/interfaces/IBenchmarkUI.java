package edu.touro.mco152.bm.interfaces;

import edu.touro.mco152.bm.DiskMark;
import edu.touro.mco152.bm.persist.DiskRun;

/**
 * Interface used to track benchmark data on GUI
 */
public interface IBenchmarkUI {
    void updateProgress(int percent);
    void addWriteMark(DiskMark dm);
    void addReadMark(DiskMark dm);
    void setTitle(String title);
    void updateLegend();
    void onBenchMarkComplete();
    void resetTestData();
    void addRun(DiskRun dr);
    void publish(DiskMark... chunks);
    boolean isCancelled();
}
