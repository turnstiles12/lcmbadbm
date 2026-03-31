import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.Assertions;
import edu.touro.mco152.bm.*;
import edu.touro.mco152.bm.persist.DiskRun;

public class WorkerTest {
    /**
     * B of BICEP: Boundary negative number and large number and 0 for edge/corner case
     * R of CORRECT: range - are values within bounds?
     * randInt should always return value within [min,max]
     * E of BICEP: Error conditions - force errors and check handling
     * min must be less than max, so error will be thrown if it is greater than max
     */
    @ParameterizedTest
    @ValueSource(ints = {1,-1,55,0,934785937})
    public void testUtil(int number) {
        Assertions.assertEquals(number, Util.randInt(number,number));
        int testInt = Util.randInt(number, number + 30) ;
        Assertions.assertTrue(testInt >= number && testInt <= number + 30);
        Assertions.assertThrows(Exception.class, () -> {
            Util.randInt(number + 30, number);
        });
    }
    /**
     * Tests R of Right-BICEP: are results correct?
     * Makes sure calculation is done correctly and variables are set properly
     * C of BICEP: Cross-check - verify with an alternate formula
     * Checks formula used in targetTxSizeKb is same as targetMarkSizeKb() * numOfMarks
     * P of BICEP: Performance: time-sensitive operations
     * Makes sure that App cancels benchmark in time
     */
    @Test
    public void testApp() {
        Assertions.assertEquals(512 * 32, App.targetMarkSizeKb());
        Assertions.assertEquals(App.targetMarkSizeKb() * 25, App.targetTxSizeKb());
        long startTime = System.currentTimeMillis();
        App.targetTxSizeKb();
        Assertions.assertTrue(System.currentTimeMillis() - startTime < 1000);
    }
    /** R of CORRECT: Reference - one method that references another
     * is changed when one method is changed
     * E of CORRECT: Existence - what if there is no info or null
     */
    @Test
    public void testDiskRun() {
        DiskRun dr = new DiskRun();
        dr.setBlockSize(0);
        Assertions.assertEquals(0, dr.getBlockSize());
        dr.setDiskInfo(null);
        /**
         * Broken test - diskInfo is null
         */
        Assertions.assertNotNull(dr.getDiskInfo());
    }
}
