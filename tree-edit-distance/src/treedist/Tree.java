package treedist;

public interface Tree {
	public static final int NOT_FOUND = -1;
	
	public int getRoot();
	
	public int getFirstChild(int nodeId);
	
	public int getNextSibling(int nodeId);
	
	public int getParent(int nodeId);
	
	public int size();
}
