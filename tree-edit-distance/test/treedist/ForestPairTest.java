package treedist;

import junit.framework.Assert;

import org.junit.Test;

public class ForestPairTest {
	@Test
	public void nullEquals() {
		ForestPair p1 = new ForestPair(null, null);
		ForestPair p2 = new ForestPair(null, null);
		
		Assert.assertEquals(p1, p2);
		Assert.assertEquals(p1.hashCode(), p2.hashCode());
		
		Tree dummy = new TreeImpl(new int[0]);
		p2 = new ForestPair(new SubForest(dummy), null);
		Assert.assertNotSame(p1, p2);
		
		p2 = new ForestPair(null, new SubForest(dummy));
		Assert.assertNotSame(p1, p2);
		
		p2 = new ForestPair(new SubForest(dummy), new SubForest(dummy));
		Assert.assertNotSame(p1, p2);
	}
	
	@Test
	public void testTrivial() {
		Tree tree = new TreeImpl(new int[]{-1, 0, 0, 1, 3, 1});
		SubForest root = new SubForest(tree);
		ForestPair p1 = new ForestPair(root, root);
		ForestPair p2 = new ForestPair(root, root);
		
		Assert.assertEquals(p1, p2);
		Assert.assertEquals(p1.hashCode(), p2.hashCode());

		p1 = new ForestPair(root, root.getHeadChild());
		p2 = new ForestPair(root, root.getHeadChild());
		Assert.assertEquals(p1, p2);
		Assert.assertEquals(p1.hashCode(), p2.hashCode());
	}
}
