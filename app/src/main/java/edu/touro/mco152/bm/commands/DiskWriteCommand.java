package edu.touro.mco152.bm.commands;

import edu.touro.mco152.bm.commands.Receivers.DiskReceiver;
/**
 * Executes write DiskReceiver commands
 */
public class DiskWriteCommand implements DiskCommand {
    private DiskReceiver command;

    public DiskWriteCommand(DiskReceiver command) {
        this.command = command;
    }
    
    public void execute() {
        command.write();
    }
}