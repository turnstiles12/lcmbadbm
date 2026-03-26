package edu.touro.mco152.bm;

public interface Worker {
    void execute();
    boolean cancel(boolean mayInterruptIfRunning);
    boolean isDone();
    boolean isCancelled();
    void setProgress(int progress);
    void publish(DiskMark disk);
    public Boolean get();
}
