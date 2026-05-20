package edu.touro.mco152.bm.commands;

import edu.touro.mco152.bm.commands.Receivers.DiskReceiver;

/**
 * Executes read DiskReceiver commands
 */
public class DiskReadCommand implements DiskCommand {
     private DiskReceiver command;

    public DiskReadCommand(DiskReceiver command) {
        this.command = command;
    }

    @Override
    public void execute() {
        command.read();
    }
    
}
