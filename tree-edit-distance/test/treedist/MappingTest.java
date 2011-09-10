package treedist;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

public class MappingTest {
	@Test
	public void get() {
		Mapping m = new Mapping(3, 3);
		// 0 <-> nil
		// 1 <-> 2
		// 2 <-> 0
		// nil <-> 1
		m.setDeletion(0);
		m.setInsertion(1);
		m.setReplacement(1, 2);
		m.setReplacement(2, 0);

		Assert.assertEquals(Arrays.asList(0), m.getAllDeletion());
		Assert.assertEquals(Arrays.asList(1), m.getAllInsertion());
		List<int[]> r = m.getAllReplacement();
		Assert.assertEquals(2, r.size());
		Assert.assertEquals(1, r.get(0)[0]);
		Assert.assertEquals(2, r.get(0)[1]);
		Assert.assertEquals(2, r.get(1)[0]);
		Assert.assertEquals(0, r.get(1)[1]);
	}
}
