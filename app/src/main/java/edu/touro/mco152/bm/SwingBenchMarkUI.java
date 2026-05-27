package edu.touro.mco152.bm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.FutureTask;

import javax.swing.SwingUtilities;

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
        return App.worker.isCancelled();
    }

    public void publish(DiskMark... chunks) {
        App.worker.publishMarks(chunks);
    }

}
