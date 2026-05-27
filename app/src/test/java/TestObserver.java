import org.eclipse.persistence.internal.libraries.asm.Handle;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.touro.mco152.bm.App;
import edu.touro.mco152.bm.SwingBenchMarkUI;
import edu.touro.mco152.bm.SwingUserNotifier;
import edu.touro.mco152.bm.commands.DiskCommand;
import edu.touro.mco152.bm.commands.DiskReadCommand;
import edu.touro.mco152.bm.commands.DiskWriteCommand;
import edu.touro.mco152.bm.commands.Executors.DiskHandler;
import edu.touro.mco152.bm.externalsys.SlackManager;
import edu.touro.mco152.bm.observer.*;
import edu.touro.mco152.bm.persist.DatabasePersistenceObserver;
import edu.touro.mco152.bm.persist.DiskRun;

/**
 * JUnit tests to test Observer pattern used in badbm
 */
public class TestObserver {
    static boolean observerFlag = false;
    static class CreateObserver implements Observer {
        @Override
        public void update(DiskRun run) {
            observerFlag = true;
        }
    }
    @Test
    public void initTest() {
        App.setupDefaultAsPerProperties();
        DiskHandler receiver = new DiskHandler(App.numOfMarks, App.numOfBlocks,
             App.blockSizeKb, "SEQUENTIAL", new TestBenchmarkUI(), new SwingUserNotifier(), 
             App.dataDir);
        receiver.register(new DatabasePersistenceObserver());
        receiver.register(new CreateObserver());
        App.writeTest = true;
        App.readTest = true;
        receiver.write();
    }
    @Test
    public void testSlack() {
        SlackManager manager = new SlackManager("BadBM");
        Boolean result = manager.postMsg2OurChannel(":white_check_mark: Test message successfully sent");
        Assertions.assertTrue(result);
    }
    @AfterAll
    public static void testObserver() {
        Assertions.assertTrue(observerFlag);
    }
}
