package treedist;

import junit.framework.Assert;

import org.junit.Test;

public class TreeEditDistanceTest {
	class ScoreImpl implements EditScore {
		private final LabeledTree tree1, tree2;

		public ScoreImpl(LabeledTree tree1, LabeledTree tree2) {
			this.tree1 = tree1;
			this.tree2 = tree2;
		}

		@Override
		public double replace(int node1, int node2) {
			if (tree1.getLabel(node1) == tree2.getLabel(node2)) {
				return 0;
			} else {
				return 4;
			}
		}

		@Override
		public double insert(int node2) {
			return 3;
		}

		@Override
		public double delete(int node1) {
			return 2;
		}
	}

	@Test
	public void testSmall() {
		// (0 (1) (2))
		LabeledTree t1 = new LabeledTree( //
				new int[] { -1, 0, 0 }, //
				new int[] { 0, 1, 2 });
		// (0 (1))
		LabeledTree t2 = new LabeledTree( //
				new int[] { -1, 0 }, //
				new int[] { 0, 1 });
		// (0 (3 (1)) (2))
		LabeledTree t3 = new LabeledTree( //
				new int[] { -1, 2, 0, 0 }, //
				new int[] { 0, 1, 3, 2 });
		// (0 (1) (3))
		LabeledTree t4 = new LabeledTree( //
				new int[] { -1, 0, 0 }, //
				new int[] { 0, 1, 3 });
		// (0 (1) (3 (2)))
		LabeledTree t5 = new LabeledTree( //
				new int[] { -1, 0, 0, 2 }, //
				new int[] { 0, 1, 3, 2 });

		// delete
		TreeEditDistance dist = new TreeEditDistance(new ScoreImpl(t1, t2));
		Assert.assertEquals(2.0, dist.calc(t1, t2));

		// insert
		dist = new TreeEditDistance(new ScoreImpl(t1, t3));
		Assert.assertEquals(3.0, dist.calc(t1, t3));
		dist = new TreeEditDistance(new ScoreImpl(t1, t5));
		Assert.assertEquals(3.0, dist.calc(t1, t5));

		// replace
		dist = new TreeEditDistance(new ScoreImpl(t1, t4));
		Assert.assertEquals(4.0, dist.calc(t1, t4));
	}
}
