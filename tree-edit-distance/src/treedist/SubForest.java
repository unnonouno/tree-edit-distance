package treedist;

class SubForest {
	private final Tree tree;
	private final int head;
	private final int root;

	public SubForest(Tree tree) {
		if (tree == null)
			throw new NullPointerException();

		this.tree = tree;
		this.head = this.root = tree.getRoot();
	}

	private SubForest(Tree tree, int head, int root) {
		this.tree = tree;
		this.head = head;
		this.root = root;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else if (obj instanceof SubForest) {
			return this.equals((SubForest) obj);
		} else {
			return false;
		}
	}

	public boolean equals(SubForest forest) {
		return this.tree == forest.tree && this.head == forest.head
				&& this.root == forest.root;
	}

	@Override
	public int hashCode() {
		return Integer.rotateLeft(this.head, 16) ^ this.root;
	}

	private SubForest subForest(int head, int root) {
		return new SubForest(this.tree, head, root);
	}

	public int head() {
		return this.head;
	}

	public int root() {
		return this.root;
	}

	public SubForest deleteHead() {
		int child = tree.getFirstChild(head);
		if (child == Tree.NOT_FOUND) {
			return null;
		} else {
			int root = (this.root == Tree.NOT_FOUND) ? this.head : this.root;
			return this.subForest(child, root);
		}
	}

	public SubForest getHeadChild() {
		int child = this.tree.getFirstChild(this.head);
		if (child == Tree.NOT_FOUND) {
			return null;
		} else {
			return this.subForest(child, this.head);
		}
	}

	public SubForest getSibling() {
		int node = this.head;
		while (true) {
			if (node == this.root) {
				return null;
			}
			int sib = tree.getNextSibling(node);
			if (sib != Tree.NOT_FOUND) {
				return this.subForest(sib, root);
			} else {
				node = tree.getParent(node);
			}
		}

	}
}
