package edu.touro.mco152.bm.commands;

import edu.touro.mco152.bm.commands.Receivers.DiskReceiver;

public class DiskWriteCommand implements DiskCommands {
    private DiskReceiver command;

    public DiskWriteCommand(DiskReceiver command, int marks, int numOfBlocks, int sizeOfBlocks, String seq ) {
        this.command = command;
    }
    
    public void execute() {
        command.run();
    }
}