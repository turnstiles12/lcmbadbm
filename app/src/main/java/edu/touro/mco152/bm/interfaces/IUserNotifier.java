package edu.touro.mco152.bm.interfaces;

/**
 * Interface to sends messages with updates as benchmark runs
 */
public interface IUserNotifier {
    void showMessage();
    void showErrorMessage(String exMessage);
}
