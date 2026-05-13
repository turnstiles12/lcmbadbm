package edu.touro.mco152.bm;

import edu.touro.mco152.bm.interfaces.IBenchmarkUI;
import edu.touro.mco152.bm.persist.DiskRun;
import edu.touro.mco152.bm.ui.Gui;

public class SwingBenchMarkUI implements IBenchmarkUI {

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

}
