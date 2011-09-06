package treedist;

import java.util.HashSet;
import java.util.Set;

public class TreeValidator {
	/**
	 * Validate that the given tree has no loop, and that all child-parent pairs
	 * are consistent.
	 * 
	 * @param tree
	 * @return
	 */
	public static void validate(Tree tree) {
		validate(tree, new HashSet<Integer>(), tree.getRoot());
	}

	private static void validate(Tree tree, Set<Integer> visited, int node) {
		if (visited.contains(node)) {
			throw new IllegalArgumentException("This tree has a loop. Node "
					+ node + " appears two times.");
		}
		visited.add(node);
		for (int child = tree.getFirstChild(node); child != Tree.NOT_FOUND; child = tree
				.getNextSibling(child)) {
			int parent = tree.getParent(child);
			if (parent != node) {
				String message = String.format(
						"Node %d is a child of node %d, but its parent is %d.",
						child, node, parent);
				throw new IllegalArgumentException(message);
			}
			validate(tree, visited, child);
		}
	}
}
