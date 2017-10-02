package list;

public class LockDList extends DList{
	public void lockNode(DListNode node){
		if(node instanceof LockDListNode)
			((LockDListNode)node).isLocked = true;
	}
	
	@Override
	protected DListNode newNode(Object item, DListNode prev, DListNode next) {
		return new LockDListNode(item, prev, next);
	}

	@Override
	public void remove(DListNode node) {
		if(node instanceof LockDListNode){
			LockDListNode lockDListNode = (LockDListNode) node;
			if(!lockDListNode.isLocked)
				super.remove(lockDListNode);
		}
	}
}