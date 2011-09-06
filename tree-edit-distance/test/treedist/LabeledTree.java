package treedist;

public class LabeledTree extends TreeImpl {
	private int[] labels;
	
	public LabeledTree(int[] parents, int[] labels) {
		super(parents);

		if (parents == null || labels == null)
			throw new NullPointerException();
		if (parents.length != labels.length)
			throw new IllegalArgumentException();
		
		this.labels = labels;
	}

	public int getLabel(int nodeId) {
		return labels[nodeId];
	}
}
