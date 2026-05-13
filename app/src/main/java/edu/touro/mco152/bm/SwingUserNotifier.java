package edu.touro.mco152.bm;

import javax.swing.JOptionPane;

import edu.touro.mco152.bm.interfaces.IUserNotifier;
import edu.touro.mco152.bm.ui.Gui;

public class SwingUserNotifier implements IUserNotifier{

    @Override
    public void showMessage() {
        JOptionPane.showMessageDialog(Gui.mainFrame,
                    """
                            For valid READ measurements please clear the disk cache by
                            using the included RAMMap.exe or flushmem.exe utilities.
                            Removable drives can be disconnected and reconnected.
                            For system drives use the WRITE and READ operations\s
                            independantly by doing a cold reboot after the WRITE""",
                    "Clear Disk Cache Now", JOptionPane.PLAIN_MESSAGE);
    }
    public void showErrorMessage(String emsg) {
        JOptionPane.showMessageDialog(Gui.mainFrame, emsg, "Unable to READ", JOptionPane.ERROR_MESSAGE);
    }

}
