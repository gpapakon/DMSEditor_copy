package Matrices.Data;


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import java.util.Stack;

public class AbstractDSMStack {
	private final BooleanProperty wasModified = new SimpleBooleanProperty(false);
	private Stack<MatrixChange> undoStack;
	private Stack<MatrixChange> redoStack;

	public BooleanProperty getWasModified2() {
		return wasModified;
	}

	public void setUndoStack(Stack<MatrixChange> undoStack) {
		this.undoStack = undoStack;
	}

	public void setRedoStack(Stack<MatrixChange> redoStack) {
		this.redoStack = redoStack;
	}

	/**
	* Clears the wasModified flag. Used for when matrix has been saved to a file. Does not add changes to the stack
	*/
	public final void clearWasModifiedFlag() {
		this.wasModified.set(false);
	}

	/**
	* Sets the was modified flag. Does not add changes to the stack
	*/
	public final void setWasModified() {
		wasModified.set(true);
	}

	/**
	* Returns whether the wasModified flag is set or not. Used to check if matrix has been saved
	* @return  if the matrix has been modified, true if it has, false if not
	*/
	public final boolean getWasModified() {
		return wasModified.getValue();
	}

	/**
	* Returns whether there are changes to be redone
	* @return  if changes are on the redo stack
	*/
	public final boolean canRedo() {
		return redoStack.size() > 0;
	}

	/**
	* Adds a change to the undo stack so that it can be handled
	* @param change  the change object to handle
	*/
	public final void addChangeToStack(MatrixChange change) {
		change.runFunction();
		undoStack.push(change);
		if (undoStack.size() > AbstractDSMData.MAX_UNDO_HISTORY) {
			undoStack.remove(0);
		}
		setWasModified();
	}

	/**
	* Undoes changes until the last checkpoint (checkpoint is not included). Pops changes from the undo stack and pushes them to the redo stack
	*/
	public final void undoToCheckpoint() {
		int iter = 0;
		while (true) {
			if (undoStack.size() > 0) {
				MatrixChange change = undoStack.peek();
				if (change.isCheckpoint() && iter > 0) {
					break;
				}
				undoStack.pop();
				change.runUndoFunction();
				redoStack.push(change);
				iter += 1;
			} else {
				break;
			}
		}
		setWasModified();
	}

	/**
	* Redoes changes that are on the redo stack to the next checkpoint (checkpoint is included).
	*/
	public final void redoToCheckpoint() {
		while (true) {
			if (redoStack.size() > 0) {
				MatrixChange change = redoStack.peek();
				redoStack.pop();
				change.runFunction();
				undoStack.push(change);
				if (change.isCheckpoint()) {
					break;
				}
			} else {
				break;
			}
		}
		setWasModified();
	}

	/**
	* Sets the top of the undo stack as a checkpoint and clears the redo stack because redoing after an operation could go horribly wrong
	*/
	public final void setCurrentStateAsCheckpoint() {
		if (!undoStack.isEmpty()) {
			undoStack.peek().setCheckpoint(true);
		}
		redoStack.clear();
	}

	/**
	* clears both undo and redo stacks (useful for instantiation of the class)
	*/
	public final void clearStacks() {
		undoStack.clear();
		redoStack.clear();
	}

	/**
	* Returns whether there are changes to be undone
	* @return  if changes are on the undo stack
	*/
	public final boolean canUndo() {
		return undoStack.size() > 0;
	}
}