package treedist;

import junit.framework.Assert;

import org.junit.Test;

public class SubForestTest {
	@Test
	public void trivial() {
		Tree t = new LabeledTree( //
				new int[] { -1, 0, 0 }, //
				new int[] { 0, 1, 2 });
		SubForest f = new SubForest(t);
		Assert.assertEquals(0, f.deleteHead().root());
		Assert.assertEquals(1, f.deleteHead().head());

		Assert.assertEquals(0, f.getHeadChild().root());
		Assert.assertEquals(1, f.getHeadChild().head());

		Assert.assertNull(f.getSibling());
	}

	@Test
	public void equalTest() {
		Tree t = new LabeledTree( //
				new int[] { -1, 0, 0 }, //
				new int[] { 0, 1, 2 });

		SubForest root = new SubForest(t);
		Assert.assertEquals(root, new SubForest(t));
		Assert.assertEquals(root.hashCode(), new SubForest(t).hashCode());

		Assert.assertEquals(root.getHeadChild(), root.getHeadChild());
		Assert.assertEquals(root.getHeadChild().hashCode(), root.getHeadChild()
				.hashCode());

		// null
		Assert.assertEquals(root.getSibling(), root.getSibling());

		Assert.assertEquals(root.deleteHead(), root.deleteHead());
		Assert.assertEquals(root.deleteHead().hashCode(), root.deleteHead()
				.hashCode());
	}
}
