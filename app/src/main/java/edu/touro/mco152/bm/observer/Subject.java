package edu.touro.mco152.bm.observer;

/**
 * Subject interface for Observer pattern
 * <br>
 * <p>Holds list of observers and updates them when necessary</p>
 */
public interface Subject {
    /**
     * Adds Observer to list of Observers
     * @param o the Observer to be added to list of Observers
     */
    void register(Observer o);
    /**
     * Removes Observer from list of Observers
     * @param o the Observer to be removed from list of Observers
     */
    void unregister(Observer o);
    /**
     * Notifies all observers when benchmark is complete
     */
    void notifyObservers();
}
