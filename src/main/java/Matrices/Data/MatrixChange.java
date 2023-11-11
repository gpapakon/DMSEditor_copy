package Matrices.Data;

public class MatrixChange {
    private final Runnable doFunction;
    private final Runnable undoFunction;
    private boolean checkpoint;

    public MatrixChange(Runnable doFunction, Runnable undoFunction, boolean checkpoint) {
        this.doFunction = doFunction;
        this.undoFunction = undoFunction;
        this.checkpoint = checkpoint;
    }

    public void runFunction() {
        doFunction.run();
    }

    public void runUndoFunction() {
        undoFunction.run();
    }

    public void setCheckpoint(boolean isCheckpoint) {
        checkpoint = isCheckpoint;
    }

    public boolean isCheckpoint() {
        return checkpoint;
    }

}