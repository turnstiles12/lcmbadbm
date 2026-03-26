package edu.touro.mco152.bm;

public class BenchWorker implements Worker {

    @Override
    public void execute() {

    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }
    public Boolean get() {
        return false;
    }

    public void setProgress(int progress) {

    }
    public void publish(DiskMark disk) {

    }

}