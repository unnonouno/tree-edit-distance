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
		p2 = new ForestPair(new Forest(dummy), null);
		Assert.assertNotSame(p1, p2);
		
		p2 = new ForestPair(null, new Forest(dummy));
		Assert.assertNotSame(p1, p2);
		
		p2 = new ForestPair(new Forest(dummy), new Forest(dummy));
		Assert.assertNotSame(p1, p2);
	}
	
	@Test
	public void testTrivial() {
		Tree tree = new TreeImpl(new int[]{-1, 0, 0, 1, 3, 1});
		Forest root = new Forest(tree);
		ForestPair p1 = new ForestPair(root, root);
		ForestPair p2 = new ForestPair(root, root);
		
		Assert.assertEquals(p1, p2);
		Assert.assertEquals(p1.hashCode(), p2.hashCode());

		p1 = new ForestPair(root, root.getInside());
		p2 = new ForestPair(root, root.getInside());
		Assert.assertEquals(p1, p2);
		Assert.assertEquals(p1.hashCode(), p2.hashCode());
	}
}
