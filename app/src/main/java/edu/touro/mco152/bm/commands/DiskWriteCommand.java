package edu.touro.mco152.bm.commands;

import edu.touro.mco152.bm.commands.Receivers.DiskExecutor;
/**
 * Executes write DiskReceiver commands
 */
public class DiskWriteCommand implements DiskCommand {
    private DiskExecutor command;

    public DiskWriteCommand(DiskExecutor command) {
        this.command = command;
    }
    
    public void execute() {
        command.write();
    }
}