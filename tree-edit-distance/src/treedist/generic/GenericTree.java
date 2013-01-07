package treedist.generic;

import treedist.Tree;

/**
 * A GenericTree mirrors a tree, but allows for generic values at each node
 * rather than simply integers. Generic trees can be interacted with using the
 * values contained in the nodes of the tree, the integer IDs for these nodes,
 * or a combination of both.
 * 
 * To avoid ambiguity, a GenericTree restricts its values to be unique. In order
 * to store duplicate values in a GenericTree, values must be wrapped inside
 * another class that ensures uniqueness.
 * 
 * @author Joshua Smith
 * 
 * @param <T>
 *            the type of the values at each node in the tree.
 */
public interface GenericTree<T> extends Tree {

    /**
     * Returns the value stored at nodeId in the GenericTree.
     * 
     * @param nodeId
     *            the ID of the node to find.
     * @return the value at nodeId in the GenericTree.
     */
    T getNode(int nodeId);

    /**
     * Returns the ID of the node containing value in the tree. If value is not
     * contained in the tree, Tree.NOT_FOUND is returned.
     * 
     * @param value
     *            the value in the node to find.
     * @return the ID of the node containing value, or Tree.NOT_FOUND if value
     *         is not in the tree.
     */
    int getId(T value);

    /**
     * Returns the ID of the first child of the node containing value in the
     * tree. Behavior is undefined if value is not in the tree.
     * 
     * @param value
     *            the value in the node to find the first child of.
     * @return the ID of the first child of the node containing value.
     */
    int getFirstChild(T value);

    /**
     * Returns the ID of the next sibling of the node containing value in the
     * tree. Behavior is undefined if value is not in the tree.
     * 
     * @param value
     *            the value in the node to find the next sibling of.
     * @return the ID of the next sibling of the node containing value.
     */
    int getNextSibling(T value);

    /**
     * Returns the ID of the parent of the node containing value in the tree.
     * Behavior is undefined if value is not in the tree.
     * 
     * @param value
     *            the value in the node to find the parent of.
     * @return the ID of the parent of the node containing value.
     */
    int getParent(T value);

    /**
     * Returns the value in the node at the root of the tree.
     * 
     * @return the value in the node at the root of the tree.
     */
    T getRootNode();

    /**
     * Returns the value of the first child of the node identified by nodeId in
     * the tree. Behavior is undefined if nodeId does not equal the ID of a node
     * in the tree.
     * 
     * @param nodeId
     *            the id of the node to find the first child of.
     * @return the value contained in the first child of the node with ID
     *         nodeId.
     */
    T getFirstChildNode(int nodeId);

    /**
     * Returns the value of the first child of the node containing value in the
     * tree. Behavior is undefined if value is not in the tree.
     * 
     * @param value
     *            the value in the node to find the first child of.
     * @return the value of the first child of the node containing value.
     */
    T getFirstChildNode(T value);

    /**
     * Returns the value of the next sibling of the node identified by nodeId in
     * the tree. Behavior is undefined if nodeId does not equal the ID of a node
     * in the tree.
     * 
     * @param nodeId
     *            the id of the node to find the next sibling of.
     * @return the value contained in the next sibling of the node with ID
     *         nodeId.
     */
    T getNextSiblingNode(int nodeId);

    /**
     * Returns the value of the next sibling of the node containing value in the
     * tree. Behavior is undefined if value is not in the tree.
     * 
     * @param value
     *            the value in the node to find the next sibling of.
     * @return the value of the next sibling of the node containing value.
     */
    T getNextSiblingNode(T value);

    /**
     * Returns the value of the parent of the node identified by nodeId in the
     * tree. Behavior is undefined if nodeId does not equal the ID of a node in
     * the tree.
     * 
     * @param nodeId
     *            the id of the node to find the parent of.
     * @return the value contained in the parent of the node with ID nodeId.
     */
    T getParentNode(int nodeId);

    /**
     * Returns the value of the parent of the node containing value in the tree.
     * Behavior is undefined if value is not in the tree.
     * 
     * @param value
     *            the value in the node to find the parent of.
     * @return the value of the parent of the node containing value.
     */
    T getParentNode(T value);

}
