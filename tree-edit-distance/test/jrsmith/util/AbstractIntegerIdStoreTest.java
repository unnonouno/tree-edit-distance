package jrsmith.util;

import java.util.Arrays;

import org.junit.Test;

/**
 * Tests behavior that should be common to all classes that extend IdStore.
 * 
 * @author Joshua Smith
 * 
 */
public abstract class AbstractIntegerIdStoreTest {

    /**
     * An array of test integers to be stored.
     */
    protected static final Integer[] TEST_ARRAY = { 0, 1, 2, 3, 4, 5 };
    /**
     * And array of test integers to be stored containing duplicates.
     */
    protected static final Integer[] DUPLICATE_ARRAY = { 0, 1, 2, 0, 5 };

    /**
     * Create an IdStore with no elements.
     * 
     * @return the empty IdStore.
     */
    public abstract IdStore<Integer> createEmptyStore();

    /**
     * Create an IdStore containing the elements in array in the order they
     * appear in array.
     * 
     * @param array
     *            the elements to be added to the IdStore.
     * @return the IdStore.
     */
    public abstract IdStore<Integer> createTestListStore(final Integer[] array);

    /**
     * Create an IdStore with no elements via the createTestListStore
     * implementation.
     * 
     * @return the IdStore.
     */
    public final IdStore<Integer> createEmptyListStore() {
        return createTestListStore(new Integer[0]);
    }

    /**
     * Create an IdStore containing the elements in array in the order they
     * appear via the createEmptyStore implementation and the IdStore's store
     * method.
     * 
     * @param array
     *            the elements to be added to the IdStore.
     * @return the IdStore.
     */
    public final IdStore<Integer> createTestStore(final Integer[] array) {
        IdStore<Integer> store = createEmptyStore();
        for (Integer t : array) {
            store.store(t);
        }
        return store;
    }

    /**
     * Tests that store and elements contain the exact same values, with the Id
     * of each value in store matching the index of that value in elements.
     * 
     * @param store
     *            the IdStore containing values.
     * @param elements
     *            the values to compare the IdStore with.
     * @return true if store and elements contain the same values in the same
     *         order.
     * 
     * @param <T>
     *            the type of values
     */
    protected static final <T> boolean matches(final IdStore<T> store,
            final T[] elements) {
        if (store.size() != elements.length) {
            return false;
        }
        for (int i = 0; i < store.size(); i++) {
            if (!store.getItem(i).equals(elements[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Test method for {@link jrsmith.util.IdStore#IdStore()}.
     */
    @Test
    public final void testIdStore() {
        createEmptyStore();
    }

    /**
     * Test method for {@link jrsmith.util.IdStore#IdStore(java.util.List)}.
     */
    @Test
    public final void testIdStoreListOfT() {
        // Test edge case of the empty list.
        IdStore<Integer> emptyStore = createEmptyListStore();
        assert (emptyStore.size() == 0);

        // Ensure all items are added
        IdStore<Integer> store = createTestListStore(TEST_ARRAY);
        assert (matches(store, TEST_ARRAY));
    }

    /**
     * Test method for {@link jrsmith.util.IdStore#store(java.lang.Object)}.
     */
    @Test
    public final void testStore() {
        // Test adding to empty store.
        IdStore<Integer> emptyStore = createEmptyStore();
        for (int i = 0; i < TEST_ARRAY.length; i++) {
            assert (emptyStore.store(i) == i);
        }

        // Test adding to a store created from a list
        IdStore<Integer> store = createTestListStore(TEST_ARRAY);
        // Test ids are sequential.
        assert (store.store(-1) == TEST_ARRAY.length);
    }

    /**
     * Test method for {@link jrsmith.util.IdStore#getItem(int)}.
     */
    @Test
    public final void testGetItem() {
        // Test get items
        IdStore<Integer> store = createTestStore(TEST_ARRAY);
        assert (matches(store, TEST_ARRAY));

        // Test get items from list store
        IdStore<Integer> listStore = createTestListStore(TEST_ARRAY);
        assert (matches(listStore, TEST_ARRAY));

        // Test get an added item
        IdStore<Integer> emptyStore = createEmptyStore();
        emptyStore.store(1);
        assert (emptyStore.getItem(0) == 1);
    }

    /**
     * Test method for {@link jrsmith.util.IdStore#getItem(int)}.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public final void testGetItemFromEmpty() {
        createEmptyStore().getItem(0);
    }

    /**
     * Test method for {@link jrsmith.util.IdStore#getItem(int)}.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public final void testGetItemTooHigh() {
        createTestStore(TEST_ARRAY).getItem(TEST_ARRAY.length);
    }

    /**
     * Test method for {@link jrsmith.util.IdStore#getItem(int)}.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public final void testGetItemTooLow() {
        createTestStore(TEST_ARRAY).getItem(-1);
    }

    /**
     * Test method for {@link jrsmith.util.IdStore#size()}.
     */
    @Test
    public final void testSize() {
        assert (createEmptyStore().size() == 0);
        assert (createTestStore(TEST_ARRAY).size() == TEST_ARRAY.length);
    }

    /**
     * Test method for {@link jrsmith.util.IdStore#items()}.
     */
    @Test
    public final void testItems() {
        IdStore<Integer> store = createTestStore(TEST_ARRAY);
        store.items().equals(Arrays.asList(TEST_ARRAY));
    }

}
