package treedist;

import junit.framework.Assert;

import org.junit.Test;

public class ForestTest {
	@Test
	public void trivial() {
		Tree t = new LabeledTree( //
				new int[] { -1, 0, 0 }, //
				new int[] { 0, 1, 2 });
		Forest f = new Forest(t);
		Assert.assertEquals(0, f.deleteHead().root());
		Assert.assertEquals(1, f.deleteHead().head());

		Assert.assertEquals(0, f.getInside().root());
		Assert.assertEquals(1, f.getInside().head());

		Assert.assertNull(f.getOutside());
	}
	
	@Test
	public void noHeadChild() {
		Tree t = new TreeImpl(new int[] {-1, 0, 0});
		Forest f = new Forest(t);
		// f = (0 (1 2))
		
		f = f.deleteHead();
		// f = (1) (2)
		
		f = f.deleteHead();
		// f = (2)
		
		Assert.assertEquals(2, f.head());
	}

	@Test
	public void equalTest() {
		Tree t = new LabeledTree( //
				new int[] { -1, 0, 0 }, //
				new int[] { 0, 1, 2 });

		Forest root = new Forest(t);
		Assert.assertEquals(root, new Forest(t));
		Assert.assertEquals(root.hashCode(), new Forest(t).hashCode());

		Assert.assertEquals(root.getInside(), root.getInside());
		Assert.assertEquals(root.getInside().hashCode(), root.getInside()
				.hashCode());

		// null
		Assert.assertEquals(root.getOutside(), root.getOutside());

		Assert.assertEquals(root.deleteHead(), root.deleteHead());
		Assert.assertEquals(root.deleteHead().hashCode(), root.deleteHead()
				.hashCode());
	}
}
