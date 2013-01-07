package jrsmith.util;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests behavior of the IdStore class.
 * 
 * @author Joshua Smith
 * 
 */
public class IdStoreTest extends AbstractIntegerIdStoreTest {

    /*
     * (non-Javadoc)
     * 
     * @see jrsmith.util.AbstractIntegerIdStoreTest#createEmptyStore()
     */
    @Override
    public final IdStore<Integer> createEmptyStore() {
        return new IdStore<Integer>();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * jrsmith.util.AbstractIntegerIdStoreTest#createTestListStore(java.lang
     * .Integer[])
     */
    @Override
    public final IdStore<Integer> createTestListStore(final Integer[] array) {
        return new IdStore<Integer>(Arrays.asList(array));
    }

    /**
     * Test method for {@link jrsmith.util.IdStore#IdStore(java.util.List)}.
     * Ensure duplicate elements are allowed.
     */
    @Test
    public final void testIdStoreListOfTDuplicate() {
        // Ensure duplicate elements cause no problems
        IdStore<Integer> duplicateStore = createTestListStore(DUPLICATE_ARRAY);
        Assert.assertTrue(matches(duplicateStore, DUPLICATE_ARRAY));
    }

    /**
     * Test method for {@link jrsmith.util.IdStore#store(java.lang.Object)}.
     * Ensure duplicate elements are allowed.
     */
    @Test
    public final void testStoreDuplicate() {
        IdStore<Integer> store = createEmptyStore();
        // Test storing a duplicate
        int index = store.store(0);
        Assert.assertEquals(store.store(0), index + 1);
    }
}
