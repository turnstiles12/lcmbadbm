import edu.touro.mco152.bm.DiskMark;
import edu.touro.mco152.bm.interfaces.IBenchmarkUI;
import edu.touro.mco152.bm.persist.DiskRun;

/**
 * Test class to be able use IBenchmarkUI wihtout fully implementing every method
 */
public class TestBenchmarkUI implements IBenchmarkUI {
    
    @Override
    public boolean isCancelled() {
        return false; 
    }

    @Override
    public void updateProgress(int percent) {}

    @Override
    public void addWriteMark(DiskMark dm) {}

    @Override
    public void addReadMark(DiskMark dm) {}

    @Override
    public void setTitle(String title) {}

    @Override
    public void updateLegend() {}

    @Override
    public void onBenchMarkComplete() {}

    @Override
    public void resetTestData() {}

    @Override
    public void addRun(DiskRun dr) {}

    @Override
    public void publish(DiskMark... chunks) {}

}