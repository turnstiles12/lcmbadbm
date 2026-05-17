package edu.touro.mco152.bm.commands.Receivers;

import static edu.touro.mco152.bm.App.KILOBYTE;
import static edu.touro.mco152.bm.App.MEGABYTE;
import static edu.touro.mco152.bm.App.msg;
import static edu.touro.mco152.bm.App.numOfBlocks;
import static edu.touro.mco152.bm.App.testFile;
import static edu.touro.mco152.bm.DiskMark.MarkType.READ;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.touro.mco152.bm.App;
import edu.touro.mco152.bm.DiskMark;
import edu.touro.mco152.bm.Util;
import edu.touro.mco152.bm.interfaces.IBenchmarkUI;
import edu.touro.mco152.bm.interfaces.IUserNotifier;
import edu.touro.mco152.bm.persist.DiskRun;
import edu.touro.mco152.bm.persist.EM;
import jakarta.persistence.EntityManager;

public class DiskReader implements DiskReceiver{
    public static final int MEGABYTE = 1024 * 1024;
    private int numOfMarks;
    private int numOfBlocks;
    private int blockSizeKb;
    private File testFile = null;
    private File dataDir = null;
    private DiskMark rMark;
    private IBenchmarkUI benchmarkUI;
    private IUserNotifier notifier;
    public DiskReader(int marks, int numOfBlocks, int sizeOfBlocks, String seq,
        IBenchmarkUI benchmarkUI, IUserNotifier notifier) {
            numOfMarks = marks;
            this.numOfBlocks = numOfBlocks;
            blockSizeKb = sizeOfBlocks;
            this.benchmarkUI = benchmarkUI;
            this.notifier = notifier;
        }
    
    public void run(){
        this.read();
    }

    private void read() {

    int wUnitsComplete = 0,
            rUnitsComplete = 0,
            unitsComplete;

    int wUnitsTotal = App.writeTest ? numOfBlocks * numOfMarks : 0;
    int rUnitsTotal = App.readTest ? numOfBlocks * numOfMarks : 0;
    int unitsTotal = wUnitsTotal + rUnitsTotal;
    float percentComplete;

    int blockSize = blockSizeKb*KILOBYTE;
    byte [] blockArr = new byte [blockSize];
    for (int b=0; b<blockArr.length; b++) {
        if (b%2==0) {
            blockArr[b]=(byte)0xFF;
        }
    }

    DiskMark wMark;
    int startFileNum = App.nextMarkNumber;
        DiskRun run = new DiskRun(DiskRun.IOMode.READ, App.blockSequence);
        run.setNumMarks(App.numOfMarks);
        run.setNumBlocks(App.numOfBlocks);
        run.setBlockSize(App.blockSizeKb);
        run.setTxSize(App.targetTxSizeKb());
        run.setDiskInfo(Util.getDiskInfo(dataDir));

        msg("disk info: (" + run.getDiskInfo() + ")");

        benchmarkUI.setTitle(run.getDiskInfo());

        for (int m = startFileNum; m < startFileNum + App.numOfMarks && !benchmarkUI.isCancelled(); m++) {

            if (App.multiFile) {
                testFile = new File(dataDir.getAbsolutePath()
                        + File.separator + "testdata" + m + ".jdm");
            }
            rMark = new DiskMark(READ);  // starting to keep track of a new benchmark
            rMark.setMarkNum(m);
            long startTime = System.nanoTime();
            long totalBytesReadInMark = 0;

            try {
                try (RandomAccessFile rAccFile = new RandomAccessFile(testFile, "r")) {
                    for (int b = 0; b < numOfBlocks; b++) {
                        if (App.blockSequence == DiskRun.BlockSequence.RANDOM) {
                            int rLoc = Util.randInt(0, numOfBlocks - 1);
                            rAccFile.seek((long) rLoc * blockSize);
                        } else {
                            rAccFile.seek((long) b * blockSize);
                        }
                        rAccFile.readFully(blockArr, 0, blockSize);
                        totalBytesReadInMark += blockSize;
                        rUnitsComplete++;
                        unitsComplete = rUnitsComplete + wUnitsComplete;
                        percentComplete = (float) unitsComplete / (float) unitsTotal * 100f;
                        benchmarkUI.updateProgress((int) percentComplete);
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                String emsg = "May not have done Write Benchmarks, so no data available to read." +
                        ex.getMessage();
                notifier.showErrorMessage(emsg);
                msg(emsg);
                return;
            }
            long endTime = System.nanoTime();
            long elapsedTimeNs = endTime - startTime;
            double sec = (double) elapsedTimeNs / (double) 1000000000;
            double mbRead = (double) totalBytesReadInMark / (double) MEGABYTE;
            rMark.setBwMbSec(mbRead / sec);
            msg("m:" + m + " READ IO is " + rMark.getBwMbSec() + " MB/s    "
                    + "(MBread " + mbRead + " in " + sec + " sec)");
            App.updateMetrics(rMark);
            benchmarkUI.publish(rMark);

            run.setRunMax(rMark.getCumMax());
            run.setRunMin(rMark.getCumMin());
            run.setRunAvg(rMark.getCumAvg());
            run.setEndTime(new Date());
        }

        /*
            Persist info about the Read BM Run (e.g. into Derby Database) and add it to a GUI panel
            */
        EntityManager em = EM.getEntityManager();
        em.getTransaction().begin();
        em.persist(run);
        em.getTransaction().commit();

        benchmarkUI.addRun(run);
    }
}
