package jrsmith.util;

import java.util.Arrays;
import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * Tests that the LookupIdStore, fulfills all requirements of an IdStore, and
 * also behaves correctly on its added methods. Also tests that the
 * LookupIdStore rejects duplicate elements.
 * 
 * @author Joshua Smith
 * 
 */
public class LookupIdStoreTest extends AbstractIntegerIdStoreTest {

    /*
     * (non-Javadoc)
     * 
     * @see jrsmith.util.AbstractIntegerIdStoreTest#createEmptyStore()
     */
    @Override
    public final LookupIdStore<Integer> createEmptyStore() {
        return new LookupIdStore<Integer>();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * jrsmith.util.AbstractIntegerIdStoreTest#createTestListStore(java.lang
     * .Integer[])
     */
    @Override
    public final LookupIdStore<Integer> createTestListStore(
            final Integer[] array) {
        return new LookupIdStore<Integer>(Arrays.asList(array));
    }

    /**
     * Test method for {@link jrsmith.util.IdStore#IdStore(java.util.List)}.
     * Ensure duplicate elements are prohibited.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testIdStoreListOfTDuplicate() {
        IdStore<Integer> store = createTestListStore(DUPLICATE_ARRAY);
        assert (store == null);
    }

    /**
     * Test method for {@link jrsmith.util.IdStore#store(java.lang.Object)}.
     * Ensure duplicate elements are prohibited.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testStoreDuplicate() {
        IdStore<Integer> store = createEmptyStore();
        // Test storing a duplicate
        store.store(0);
        store.store(0);
    }

    /**
     * Test method for {@link jrsmith.util.IdStore#getId(T)}.
     */
    @Test
    public final void testGetId() {
        LookupIdStore<Integer> store = createEmptyStore();
        for (int i : TEST_ARRAY) {
            int id = store.store(i);
            assert (store.getId(i) == id);
        }
        for (int i = 0; i < TEST_ARRAY.length; i++) {
            assert (store.getId(TEST_ARRAY[i]) == i);
        }
    }

    /**
     * Test method for {@link jrsmith.util.IdStore#getId(T)}. Ensure
     * NoSuchElementException is thrown.
     */
    @Test(expected = NoSuchElementException.class)
    public final void testGetIdEmpty() {
        createEmptyStore().getId(0);
    }

    /**
     * Test method for {@link jrsmith.util.IdStore#getId(T)}. Ensure
     * NoSuchElementException is thrown.
     */
    @Test(expected = NoSuchElementException.class)
    public final void testGetFail() {
        LookupIdStore<Integer> store = createEmptyStore();
        for (int i = 0; i < TEST_ARRAY.length; i++) {
            store.store(i);
        }
        store.getId(TEST_ARRAY.length);
    }

    /**
     * Test method for {@link jrsmith.util.IdStore#contains(T)}.
     */
    @Test
    public final void testContains() {
        LookupIdStore<Integer> store = createEmptyStore();
        assert (!store.contains(-1));
        assert (!store.contains(0));
        assert (!store.contains(1));

        for (int i = 0; i < TEST_ARRAY.length; i++) {
            store.store(i);
        }
        for (int i = 0; i < TEST_ARRAY.length; i++) {
            assert (store.contains(i));
        }

        assert (!store.contains(-1));
        assert (!store.contains(TEST_ARRAY.length));
    }
}
