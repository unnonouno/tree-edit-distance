package jrsmith.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Collection that automatically assigns integer IDs to the items it contains.
 * The first item stored in a IdStore is assigned the ID 0, and each ensuing
 * items are stored sequentially. Items can be retrieved by their IDs.
 * 
 * @author Joshua Smith
 * 
 * @param <T>
 *            The type of the items stored in the IdStore.
 */
public class IdStore<T> {

    /**
     * The items in the IdStore. IDs of the items correspond to their indices.
     */
    private List<T> items;

    /**
     * Creates an IdStore with no elements.
     */
    public IdStore() {
        this.items = new ArrayList<T>();
    }

    /**
     * Creates an IdStore containing all elements in items.
     * 
     * @param newItems
     *            the elements stored in the initial IdStore.
     */
    public IdStore(final List<T> newItems) {
        this.items = new ArrayList<T>(newItems);
    }

    /**
     * Stores item in the IdStore, returning id assigned to that item. The item
     * is assigned the lowest available ID greater than zero.
     * 
     * @param item
     *            the element to store.
     * @return the id assigned to item.
     */
    public final int store(final T item) {
        int id = items.size();
        storing(item, id);
        items.add(item);
        return id;
    }

    /**
     * Entry point will be called before item is added to the IdStore with ID
     * id.
     * 
     * @param id
     *            the id item will be assigned.
     * @param item
     *            the item that will be added.
     */
    protected void storing(final T item, final int id) {
    }

    /**
     * Returns the item in the IdStore that was assigned id. An
     * IndexOutOfBoundsException is thrown if id < 0 or id >= size().
     * 
     * @param id
     *            The id assigned to the item being retrieved.
     * @return the item which was assigned id.
     */
    public final T getItem(final int id) {
        if (id < 0 || id >= items.size()) {
            throw new IndexOutOfBoundsException(
                    "IdStore does not contain an item at id " + id);
        }
        return items.get(id);
    }

    /**
     * Returns the number of items in the IdStore.
     * 
     * @return the number of items in the IdStore.
     */
    public final int size() {
        return items.size();
    }

    /**
     * Returns all items in the IdStore in an unmodifiable list.
     * 
     * @return all items in the IdStore in an unmodifiable list.
     */
    public final List<T> items() {
        return Collections.unmodifiableList(items);
    }
}
