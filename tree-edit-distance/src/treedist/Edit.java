package treedist;

import java.util.Arrays;

public class Edit {
	private int[] tree1, tree2;
	
	public Edit(Tree tree1, Tree tree2) {
		this(tree1.size(), tree2.size());
	}

	public Edit(int len1, int len2) {
		this.tree1 = new int[len1];
		this.tree2 = new int[len2];

		Arrays.fill(this.tree1, Tree.NOT_FOUND);
		Arrays.fill(this.tree2, Tree.NOT_FOUND);
	}
	
	public void setDeletion(int node1) {
		this.tree1[node1] = Tree.NOT_FOUND;
	}

	public void setInsertion(int node2) {
		this.tree2[node2] = Tree.NOT_FOUND;
	}
	
	public void setReplacement(int node1, int node2) {
		this.tree1[node1] = node2;
		this.tree2[node2] = node1;
	}
	
	public int getTree1Operation(int node1) {
		return this.tree1[node1];
	}
	
	public int getTree2Operation(int node2) {
		return this.tree2[node2];
	}

}
