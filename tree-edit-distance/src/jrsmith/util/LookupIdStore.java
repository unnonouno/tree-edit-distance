package jrsmith.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Collection that automatically assigns integer IDs to the items it contains.
 * The first item stored in a LookupIdStore is assigned the ID 0, and each
 * ensuing items are stored sequentially. Items can be retrieved by their IDs.
 * 
 * In addition, IDs can be looked up by the items they are assigned to. This
 * capability adds the restriction that equal items cannot be stored in a
 * LookupIdStore.
 * 
 * @author Joshua Smith
 * 
 * @param <T>
 *            The type of the items stored in the LookupIdStore.
 */
public class LookupIdStore<T> extends IdStore<T> {

    /**
     * Lookup from items in IdStore to their IDs.
     */
    private Map<T, Integer> ids;

    /**
     * Creates a LookupIdStore with no elements.
     */
    public LookupIdStore() {
        this(new ArrayList<T>(0));
    }

    /**
     * Creates a LookupIdStore containing all elements in items. An
     * IllegalArgumentException is thrown if items contains any duplicate
     * elements
     * 
     * @param items
     *            the elements stored in the initial IdStore.
     */
    public LookupIdStore(final List<T> items) {
        super(items);
        this.ids = new HashMap<T, Integer>();
        int index = 0;
        for (T item : items) {
            Integer oldValue = ids.put(item, index);
            if (oldValue != null) {
                throw new IllegalArgumentException(
                        "LookupIdStore can not store duplicate items.");
            }
            index++;
        }
    }

    /**
     * Adds the (item, id) pair to the IDs lookup map. An
     * IllegalArgumentException is thrown if the LookupIdStore already contains
     * an element equal to item.
     * 
     * @param id
     *            the id item will be assigned.
     * @param item
     *            the item that will be added.
     */
    @Override
    protected final void storing(final T item, final int id) {
        if (ids.containsKey(item)) {
            throw new IllegalArgumentException(
                    "LookupIdStore can not store duplicate items.");
        }
        ids.put(item, id);
    }

    /**
     * Retrieves the ID assigned to item. If item was never added to the
     * LookupIdStore, a NoSuchElementException is thrown.
     * 
     * @param item
     *            the item whose ID is being retrieved.
     * @return the ID assigned to item.
     */
    public final int getId(final T item) {
        if (!contains(item)) {
            throw new NoSuchElementException(
                    "LookupIdStore does not contain item");
        }
        return ids.get(item);
    }

    /**
     * Returns true if item is in the LookupIdStore, false otherwise.
     * 
     * @param item
     *            the item to check the existence of.
     * @return true if item is in the LookupIdStore, false otherwise.
     */
    public final boolean contains(final T item) {
        return ids.containsKey(item);
    }

}
