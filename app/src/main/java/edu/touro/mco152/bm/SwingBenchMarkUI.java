package edu.touro.mco152.bm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker.StateValue;

import edu.touro.mco152.bm.interfaces.IBenchmarkUI;
import edu.touro.mco152.bm.persist.DiskRun;
import edu.touro.mco152.bm.ui.Gui;

public class SwingBenchMarkUI implements IBenchmarkUI{

    @Override
    public void updateProgress(int percent) {
        Gui.progressBar.setValue(percent);
    }

    @Override
    public void addWriteMark(DiskMark dm) {
        Gui.addWriteMark(dm);
    }

    @Override
    public void addReadMark(DiskMark dm) {
        Gui.addReadMark(dm);
    }

    @Override
    public void setTitle(String title) {
        Gui.chartPanel.getChart().getTitle().setVisible(true);
        Gui.chartPanel.getChart().getTitle().setText(title);
    }

    @Override
    public void updateLegend() {
        Gui.updateLegend();
    }

    @Override
    public void onBenchMarkComplete() {
        Gui.mainFrame.adjustSensitivity();
    }

    public void resetTestData() {
        Gui.resetTestData();
    }

    public void addRun(DiskRun dr) {
        Gui.runPanel.addRun(dr);
    }
    public final boolean isCancelled() {
        FutureTask<Boolean> future = new FutureTask<>(null);
        return future.isCancelled();
    }
    private final List<DiskMark> pendingChunks = new ArrayList<>();
    private final Object lock = new Object();
    private volatile boolean submitScheduled = false;

    public void publish(DiskMark... chunks) {
    synchronized (lock) {
        Collections.addAll(pendingChunks, chunks);
        if (!submitScheduled) {
            submitScheduled = true;
            SwingUtilities.invokeLater(this::flush);
        }
    }
}

    private void flush() {
        List<DiskMark> batch;
        synchronized (lock) {
            batch = new ArrayList<>(pendingChunks);
            pendingChunks.clear();
            submitScheduled = false;
        }
    }

}
