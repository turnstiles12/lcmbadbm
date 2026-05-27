import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.touro.mco152.bm.SwingBenchMarkUI;
import edu.touro.mco152.bm.SwingUserNotifier;
import edu.touro.mco152.bm.commands.DiskCommand;
import edu.touro.mco152.bm.commands.DiskReadCommand;
import edu.touro.mco152.bm.commands.DiskWriteCommand;
import edu.touro.mco152.bm.commands.Executors.DiskExecutor;
import edu.touro.mco152.bm.commands.Executors.DiskHandler;

public class CommandTest {
    @Test
    public void testReader() {
        DiskExecutor handler = new DiskHandler(1,1,1,"Serial",
        new SwingBenchMarkUI(),new SwingUserNotifier(), null);
        DiskCommand reader = new DiskReadCommand(handler);
        reader.execute();
        Assertions.assertEquals(reader, reader);
    }
    @Test
    public void testWriter() {
        DiskExecutor handler = new DiskHandler(1,1,1,"Serial",
        new SwingBenchMarkUI(), new SwingUserNotifier(), null);
        DiskCommand writer = new DiskWriteCommand(handler);
        writer.execute();
        Assertions.assertEquals(writer, writer);
    }
}
