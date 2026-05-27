package edu.touro.mco152.bm;

import javax.swing.SwingUtilities;

import edu.touro.mco152.bm.interfaces.IBenchmarkUI;
import edu.touro.mco152.bm.observer.Observer;
import edu.touro.mco152.bm.persist.DiskRun;

/**
 * Updates GUI run panel using an IBenchmarkUI 
 * <br>
 * Is an observer and can be run via notifyObservers();
 */
public class GuiRunPanelObserver implements Observer{
    private IBenchmarkUI benchmarkUI;

    public GuiRunPanelObserver(IBenchmarkUI benchmarkUI) {
        this.benchmarkUI = benchmarkUI;
    }
    @Override
    public void update(DiskRun run) {
        SwingUtilities.invokeLater(() -> benchmarkUI.addRun(run));
    }
}