package edu.touro.mco152.bm.commands;

import edu.touro.mco152.bm.commands.Executors.DiskExecutor;

/**
 * Executes read DiskReceiver commands
 */
public class DiskReadCommand implements DiskCommand {
     private DiskExecutor command;

    public DiskReadCommand(DiskExecutor command) {
        this.command = command;
    }

    @Override
    public void execute() {
        command.read();
    }
    
}
