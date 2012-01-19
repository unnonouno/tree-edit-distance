package treedist.generic;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import treedist.Tree;

/**
 * Test GenericTreeImpl implementation.
 * 
 * @author Joshua Smith
 * 
 */
public class GenericTreeImplTest {

    /** Value at root node in test trees. */
    private static final String ROOT = "root";
    /** Prefix to the value of all children of the root node in test trees. */
    private static final String CHILD_PREFIX = "child";

    /** Array of nodes for a test tree. */
    private static final String[] NODES = { ROOT, CHILD_PREFIX + "a",
            CHILD_PREFIX + "b", CHILD_PREFIX + "c", "a1", "a2", "c1" };
    /** Array specifying the parents for nodes. */
    private static final String[] PARENTS = { null, ROOT, ROOT, ROOT,
            CHILD_PREFIX + "a", CHILD_PREFIX + "a", CHILD_PREFIX + "c" };

    /** A second test array with less conventional ordering of nodes. */
    private static final String[] NODES2 = { CHILD_PREFIX + "a",
            CHILD_PREFIX + "b", CHILD_PREFIX + "c", ROOT, "a1", "a2", "c1" };
    /** Array specifying the parents for nodes2. */
    private static final String[] PARENTS2 = { ROOT, ROOT, ROOT, null,
            CHILD_PREFIX + "a", CHILD_PREFIX + "a", CHILD_PREFIX + "c" };

    /**
     * Return an empty GenericTreeImpl of type T.
     * 
     * @return the tree.
     * @param <T>
     *            type of values in tree.
     */
    private static <T> GenericTreeImpl<T> createEmptyTree() {
        return new GenericTreeImpl<T>(new LinkedList<T>(), new LinkedList<T>());
    }

    /**
     * Return a GenericTreeImpl using the nodes-parents list constructor.
     * 
     * @param nodes
     *            the values to be put into the tree.
     * @param parents
     *            each value in parents is the parent of the value in nodes at
     *            the same index.
     * @return the tree.
     * @param <T>
     *            type of values in tree.
     */
    private static <T> GenericTreeImpl<T> createTree(final List<T> nodes,
            final List<T> parents) {
        return new GenericTreeImpl<T>(nodes, parents);
    }

    /**
     * Test constructor. Also test the get root methods.
     */
    @Test
    public final void testGenericTreeImpl() {
        // Test empty constructor
        GenericTree<String> tree = createEmptyTree();
        Assert.assertEquals(tree.size(), 0);
        Assert.assertEquals(tree.getRoot(), Tree.NOT_FOUND);
        Assert.assertNull(tree.getRootNode());

        // Test normal constructor
        tree = createTree(Arrays.asList(NODES), Arrays.asList(PARENTS));
        Assert.assertEquals(tree.size(), NODES.length);
        Assert.assertEquals(tree.getRootNode(), ROOT);
        Assert.assertEquals(tree.getParentNode(CHILD_PREFIX + "a"), ROOT);
        Assert.assertEquals(tree.getParentNode(tree.getParentNode("a1")), ROOT);

        // Test root can be anywhere
        tree = createTree(Arrays.asList(NODES2), Arrays.asList(PARENTS2));
        Assert.assertEquals(tree.size(), NODES2.length);
        Assert.assertEquals(tree.getRootNode(), ROOT);
        Assert.assertEquals(tree.getParentNode(CHILD_PREFIX + "a"), ROOT);
        Assert.assertEquals(tree.getParentNode(tree.getParentNode("a1")), ROOT);
    }

    /**
     * Test that getId and getNode are inverses. Must be able to turn a Node
     * into and Id and back.
     */
    @Test
    public final void testGetNodeAndGetId() {
        GenericTree<String> tree =
                createTree(Arrays.asList(NODES), Arrays.asList(PARENTS));
        for (String val : NODES) {
            Assert.assertEquals(tree.getNode(tree.getId(val)), val);
        }
    }

    /**
     * Tests that the root node has no parents for all possible getRoot methods.
     */
    @Test
    public final void testGetRootHasNoParent() {
        GenericTree<String> tree =
                createTree(Arrays.asList(NODES), Arrays.asList(PARENTS));
        Assert.assertEquals(tree.getParent(tree.getRoot()), Tree.NOT_FOUND);
        Assert.assertEquals(tree.getParentNode(tree.getRoot()), null);
        Assert.assertEquals(tree.getParent(tree.getRootNode()), Tree.NOT_FOUND);
        Assert.assertEquals(tree.getParentNode(tree.getRootNode()), null);
    }

    /**
     * Tests that getFirstChild, getParent, and getNextSibling work for all
     * nodes in the first generation of the tree.
     */
    @Test
    public final void testChildren() {
        GenericTree<String> tree =
                createTree(Arrays.asList(NODES), Arrays.asList(PARENTS));

        // Counters to move through the tree using each possible method.
        int idId, nodeId;
        String idNode, nodeNode;

        for (
        // Start each counter at first child
        idId = tree.getFirstChild(tree.getRoot()), idNode =
                tree.getFirstChildNode(tree.getRoot()), nodeId =
                tree.getFirstChild(tree.getRootNode()), nodeNode =
                tree.getFirstChildNode(tree.getRootNode());

        // End loop when there is no next child
        idId != Tree.NOT_FOUND;

        // move each to next child
        idId = tree.getNextSibling(idId), idNode =
                tree.getNextSiblingNode(nodeId), nodeId =
                tree.getNextSibling(nodeNode), nodeNode =
                tree.getNextSiblingNode(nodeNode)) {
            // Ensure methods return compatible values.
            Assert.assertEquals(idId, nodeId);
            Assert.assertEquals(idNode, nodeNode);
            Assert.assertEquals(tree.getId(idNode), idId);

            // Ensure node found is actually a child.
            Assert.assertTrue(idNode.startsWith(CHILD_PREFIX));

            // Ensure the child correctly identifies its parent.
            Assert.assertEquals(tree.getParentNode(idId), ROOT);
            Assert.assertEquals(tree.getParentNode(idNode), ROOT);
        }
    }
}
