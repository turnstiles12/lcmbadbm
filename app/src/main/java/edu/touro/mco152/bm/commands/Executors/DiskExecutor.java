package edu.touro.mco152.bm.commands.Executors;

import edu.touro.mco152.bm.observer.Subject;

/**
 * Contract for disk read/write capabilites to be used as commands
 */
public interface DiskExecutor extends Subject{
    public void write();
    public void read();
}
