package list;

public class LockDListNode extends DListNode{
	LockDListNode(Object i, DListNode p, DListNode n) {
		super(i, p, n);
		isLocked = false;
	}

	protected boolean isLocked;
}