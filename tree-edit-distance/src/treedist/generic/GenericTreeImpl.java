/**
 * 
 */
package treedist.generic;

import java.util.List;
import java.util.NoSuchElementException;

import jrsmith.util.LookupIdStore;
import treedist.Tree;
import treedist.TreeImpl;

/**
 * Simple implementation of the GenericTree interface using a TreeImpl for the
 * underlying tree. This tree is immutable once it has been created.
 * 
 * @author Joshua Smith
 * 
 * @param <T>
 *            The type of the values in the GenericTree.
 */
public class GenericTreeImpl<T> extends AbstractGenericTree<T> {

    /**
     * A map between values and IDs.
     */
    private final LookupIdStore<T> ids;

    /**
     * The underlying tree of IDs.
     */
    private final TreeImpl tree;

    /**
     * Creates a tree containing the values in nodes. Parents contains the
     * values for the parents of the values in nodes, such that
     * getParentNode(nodes[i]) == parents[i].
     * 
     * All values in parents must equal a value in nodes, with the exception of
     * one which must be null. The node with the index of the null value in
     * parents is the root of the tree.
     * 
     * @param nodes
     *            the values in the tree.
     * @param parents
     *            the values of the parent of each node in nodes as specified
     *            above.
     */
    public GenericTreeImpl(final List<T> nodes, final List<T> parents) {
        if (nodes.size() != parents.size()) {
            throw new IllegalArgumentException(
                    "Parents list must be the same size os Nodes list.");
        }
        this.ids = new LookupIdStore<T>(nodes);

        int[] parentArray = makeIdArray(parents, ids);
        this.tree = new TreeImpl(parentArray);
    }

    /**
     * Creates an array of the IDs in values, given a LookupIdStore that maps
     * values to IDs. The resulting array is ordered such that each ID is at the
     * same index as its corresponding value in values. All values not contained
     * in ids, are given the ID: Tree.NOT_FOUND.
     * 
     * @param values
     *            a list of values
     * @param ids
     *            a map from values to IDs.
     * @return an array of the IDs of each value in values.
     * 
     * @param <T>
     *            the type of the values to map to IDs.
     */
    private static <T> int[] makeIdArray(final List<T> values,
            final LookupIdStore<T> ids) {
        int[] idArray = new int[values.size()];

        int length = values.size();
        for (int index = 0; index < length; index++) {
            T value = values.get(index);
            if (value == null) {
                idArray[index] = Tree.NOT_FOUND;
            } else {
                idArray[index] = ids.getId(value);
            }
        }

        return idArray;
    }

    /*
     * (non-Javadoc)
     * 
     * @see treedist.generic.AbstractGenericTree#getId(java.lang.Object)
     */
    @Override
    public final int getId(final T node) {
        try {
            return ids.getId(node);
        } catch (NoSuchElementException e) {
            return Tree.NOT_FOUND;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see treedist.generic.AbstractGenericTree#getFoundNode(int)
     */
    @Override
    protected final T getFoundNode(final int nodeId) {
        return ids.getItem(nodeId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see treedist.generic.AbstractGenericTree#getTree()
     */
    @Override
    protected final Tree getTree() {
        return tree;
    }
}
