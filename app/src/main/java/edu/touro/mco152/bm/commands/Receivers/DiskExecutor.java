package edu.touro.mco152.bm.commands.Receivers;

/**
 * Contract for disk read/write capabilites to be used as commands
 */
public interface DiskExecutor {
    public void write();
    public void read();
}
