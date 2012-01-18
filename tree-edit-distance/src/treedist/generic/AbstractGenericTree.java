/**
 * 
 */
package treedist.generic;

import treedist.Tree;

/**
 * Provides a partial implementation of the GenericTree interface. Classes that
 * inherit from AbstractGenericTree only need to implement and underlying tree
 * of integers and a mapping between these integers and values. They implement
 * these through the methods: getTree, getFoundNode, and getId. All other
 * methods in the GenericTree interface are implemented from these.
 * 
 * @author Joshua Smith
 * 
 * @param <T>
 *            The type of the values in the GenericTree.
 * 
 */
public abstract class AbstractGenericTree<T> implements GenericTree<T> {

    /**
     * Get the value in the node at nodeId in the tree. Behavior need not be
     * defined if nodeId is not the ID of a node in the tree.
     * 
     * @param nodeId
     *            the ID of the node to find.
     * @return the value at the node with ID nodeId.
     */
    protected abstract T getFoundNode(int nodeId);

    /**
     * Get the underlying tree implementation of this abstract tree. The
     * integers in this tree will be used as the nodeId's for the abstract
     * generic tree.
     * 
     * @return the underlying tree.
     */
    protected abstract Tree getTree();

    /*
     * (non-Javadoc)
     * 
     * @see treedist.generic.GenericTree#getId(T)
     */
    @Override
    public abstract int getId(T value);

    /*
     * (non-Javadoc)
     * 
     * @see treedist.generic.GenericTree#getNode(int)
     */
    @Override
    public final T getNode(final int nodeId) {
        if (nodeId == Tree.NOT_FOUND) {
            return null;
        } else {
            return getFoundNode(nodeId);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see treedist.Tree#getRoot()
     */
    @Override
    public final int getRoot() {
        return getTree().getRoot();
    }

    /*
     * (non-Javadoc)
     * 
     * @see treedist.Tree#getFirstChild(int)
     */
    @Override
    public final int getFirstChild(final int nodeId) {
        return getTree().getFirstChild(nodeId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see treedist.Tree#getNextSibling(int)
     */
    @Override
    public final int getNextSibling(final int nodeId) {
        return getTree().getNextSibling(nodeId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see treedist.Tree#getParent(int)
     */
    @Override
    public final int getParent(final int nodeId) {
        return getTree().getParent(nodeId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see treedist.Tree#size()
     */
    @Override
    public final int size() {
        return getTree().size();
    }

    /*
     * (non-Javadoc)
     * 
     * @see treedist.generic.GenericTree#getFirstChild(java.lang.Object)
     */
    @Override
    public final int getFirstChild(final T node) {
        return getFirstChild(getId(node));
    }

    /*
     * (non-Javadoc)
     * 
     * @see treedist.generic.GenericTree#getNextSibling(java.lang.Object)
     */
    @Override
    public final int getNextSibling(final T node) {
        return getNextSibling(getId(node));
    }

    /*
     * (non-Javadoc)
     * 
     * @see treedist.generic.GenericTree#getParent(java.lang.Object)
     */
    @Override
    public final int getParent(final T node) {
        return getParent(getId(node));
    }

    /*
     * (non-Javadoc)
     * 
     * @see treedist.generic.GenericTree#getRootNode()
     */
    @Override
    public final T getRootNode() {
        return getNode(getRoot());
    }

    /*
     * (non-Javadoc)
     * 
     * @see treedist.generic.GenericTree#getFirstChildNode(int)
     */
    @Override
    public final T getFirstChildNode(final int nodeId) {
        return getNode(getFirstChild(nodeId));
    }

    /*
     * (non-Javadoc)
     * 
     * @see treedist.generic.GenericTree#getFirstChildNode(java.lang.Object)
     */
    @Override
    public final T getFirstChildNode(final T node) {
        return getNode(getFirstChild(node));
    }

    /*
     * (non-Javadoc)
     * 
     * @see treedist.generic.GenericTree#getNextSiblingNode(int)
     */
    @Override
    public final T getNextSiblingNode(final int nodeId) {
        return getNode(getNextSibling(nodeId));
    }

    /*
     * (non-Javadoc)
     * 
     * @see treedist.generic.GenericTree#getNextSiblingNode(java.lang.Object)
     */
    @Override
    public final T getNextSiblingNode(final T node) {
        return getNode(getNextSibling(node));
    }

    /*
     * (non-Javadoc)
     * 
     * @see treedist.generic.GenericTree#getParentNode(int)
     */
    @Override
    public final T getParentNode(final int nodeId) {
        return getNode(getParent(nodeId));
    }

    /*
     * (non-Javadoc)
     * 
     * @see treedist.generic.GenericTree#getParentNode(java.lang.Object)
     */
    @Override
    public final T getParentNode(final T node) {
        return getNode(getParent(node));
    }

}
