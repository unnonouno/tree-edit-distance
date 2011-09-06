package treedist;

import junit.framework.Assert;

import org.junit.Test;

public class SubForestTest {
	@Test
	public void trivial() {
		Tree t = new LabeledTree( //
				new int[]{-1, 0, 0}, //
				new int[]{0, 1, 2});
		SubForest f = new SubForest(t);
		Assert.assertEquals(0, f.deleteHead().root());
		Assert.assertEquals(1, f.deleteHead().head());

		Assert.assertEquals(0, f.getHeadChild().root());
		Assert.assertEquals(1, f.getHeadChild().head());

		Assert.assertNull(f.getSibling());
	}
}
