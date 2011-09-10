package treedist;

import java.util.Arrays;

public class TreeImpl implements Tree {
	private int root;
	private final int[] childs;
	private final int[] siblings;
	private final int[] parents;

	public TreeImpl(int root, int[] childs, int[] siblings, int[] parents) {
		this.root = root;
		this.childs = childs;
		this.siblings = siblings;
		this.parents = parents;
		TreeValidator.validate(this);
	}

	public TreeImpl(int[] parents) {
		if (parents == null)
			throw new NullPointerException();

		int n = parents.length;
		int[] childs = new int[n];
		int[] siblings = new int[n];
		int[] lastChild = new int[n];
		Arrays.fill(childs, Tree.NOT_FOUND);
		Arrays.fill(siblings, Tree.NOT_FOUND);

		int root = -1;
		for (int i = 0; i < n; ++i) {
			int p = parents[i];
			if (p == Tree.NOT_FOUND) {
				if (root != -1) {
					throw new IllegalArgumentException("duplicated root nodes");
				}
				root = i;
			} else if (childs[p] == Tree.NOT_FOUND) {
				childs[p] = i;
				lastChild[p] = i;
			} else {
				siblings[lastChild[p]] = i;
				lastChild[p] = i;
			}
		}

		this.root = root;
		this.childs = childs;
		this.siblings = siblings;
		this.parents = parents;
	}

	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder();
		this.toString(this.getRoot(), buf);
		return buf.toString();
	}

	private void toString(int root, StringBuilder buf) {
		buf.append('(');
		buf.append(String.valueOf(root));
		for (int child = this.getFirstChild(root); child != NOT_FOUND; child = this
				.getNextSibling(child)) {
			buf.append(' ');
			toString(child, buf);
		}
		buf.append(')');
	}

	@Override
	public int getRoot() {
		return root;
	}

	@Override
	public int getFirstChild(int nodeId) {
		return childs[nodeId];
	}

	@Override
	public int getNextSibling(int nodeId) {
		return siblings[nodeId];
	}

	@Override
	public int getParent(int nodeId) {
		return parents[nodeId];
	}

	@Override
	public int size() {
		return this.parents.length;
	}

}
