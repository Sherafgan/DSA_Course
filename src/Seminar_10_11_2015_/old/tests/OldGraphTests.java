package Seminar_10_11_2015_.old.tests;

import Seminar_10_11_2015_.old.OldGraph;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author Sherafgan Kandov
 *         24.11.15
 */
public class OldGraphTests extends TestCase {
    @Test
    public void test() {
        //All the tests in this method are done to the following oldGraph
        //          v
        //         / \
        //        /   \
        //     a /     \ b
        //      /       \
        //     /    c    \
        //    u-----------w

        OldGraph<String, String> oldGraph = new OldGraph<>();

        oldGraph.insertVertex("u");
        oldGraph.insertVertex("v");
        oldGraph.insertVertex("w");

        assertTrue(oldGraph.amountOfVertices() == 3);
        assertTrue(oldGraph.amountOfEdges() == 0);

        oldGraph.insertEdge("u", "v", "a");
        oldGraph.insertEdge("v", "w", "b");

        assertTrue(oldGraph.amountOfEdges() == 2);

        oldGraph.removeVertex("v");

        assertTrue(oldGraph.amountOfEdges() == 0);
        assertTrue(oldGraph.amountOfVertices() == 2);

        oldGraph.insertVertex("v");
        oldGraph.insertEdge("u", "v", "a");
        oldGraph.insertEdge("v", "w", "b");
        oldGraph.insertEdge("u", "w", "c");

        assertTrue(oldGraph.amountOfVertices() == 3);
        assertTrue(oldGraph.amountOfEdges() == 3);

        assertSame(oldGraph.degree("u"), 2);
        assertSame(oldGraph.degree("v"), 2);

        assertEquals(oldGraph.endVertices("a").get(0), "u");
        assertEquals(oldGraph.endVertices("a").get(1), "v");

        assertEquals(oldGraph.opposite("u", "a"), "v");

        assertEquals(oldGraph.incidentEdges("v").get(0), "a");
        assertEquals(oldGraph.incidentEdges("v").get(1), "b");

        oldGraph.removeAllEdgesWithWeight("a");

        assertTrue(oldGraph.amountOfEdges() == 2);
        assertFalse(oldGraph.areAdjacent("u", "v"));
        assertTrue(oldGraph.areAdjacent("u", "w"));

        assertSame(oldGraph.degree("u"), 1);
        assertSame(oldGraph.degree("v"), 1);

        assertEquals(oldGraph.endVertices("b").get(0), "v");
        assertEquals(oldGraph.endVertices("b").get(1), "w");

        assertEquals(oldGraph.opposite("v", "b"), "w");
        assertEquals(oldGraph.opposite("w", "b"), "v");

        assertEquals(oldGraph.incidentEdges("v").get(0), "b");
    }

    @Test
    public void test2() {
        //Tests for another more complex undirected weighted oldGraph
        //Link to image of oldGraph: http://i.stack.imgur.com/kJF5K.png

        OldGraph<String, Integer> oldGraph = new OldGraph<>();

        oldGraph.insertVertex("A");
        oldGraph.insertVertex("B");
        oldGraph.insertVertex("C");
        oldGraph.insertVertex("D");
        oldGraph.insertVertex("E");
        oldGraph.insertVertex("F");
        oldGraph.insertVertex("G");
        oldGraph.insertVertex("H");
        oldGraph.insertVertex("I");

        assertSame(oldGraph.amountOfVertices(), 9);

        oldGraph.insertEdge("A", "B", 22);
        oldGraph.insertEdge("A", "C", 9);
        oldGraph.insertEdge("A", "D", 12);

        oldGraph.insertEdge("B", "C", 35);
        oldGraph.insertEdge("B", "F", 36);
        oldGraph.insertEdge("B", "H", 34);

        oldGraph.insertEdge("C", "D", 4);
        oldGraph.insertEdge("C", "E", 65);
        oldGraph.insertEdge("C", "F", 42);

        oldGraph.insertEdge("D", "E", 33);
        oldGraph.insertEdge("D", "I", 30);

        oldGraph.insertEdge("E", "F", 18);
        oldGraph.insertEdge("E", "G", 23);

        oldGraph.insertEdge("F", "G", 39);
        oldGraph.insertEdge("F", "H", 24);

        oldGraph.insertEdge("G", "H", 25);
        oldGraph.insertEdge("G", "I", 21);

        oldGraph.insertEdge("H", "I", 19);

        assertSame(oldGraph.amountOfEdges(), 18);

        oldGraph.removeEdge("A", "B", 22);
        assertSame(oldGraph.amountOfEdges(), 17);

        assertTrue(oldGraph.opposite("B", 34).equals("H"));

//        assertSame(Integer.parseInt(oldGraph.incidentEdges("E").get(0)), 65);
//        assertSame(Integer.parseInt(oldGraph.incidentEdges("E").get(1)), 33);
//        assertSame(Integer.parseInt(oldGraph.incidentEdges("E").get(2)), 18);
//        assertSame(Integer.parseInt(oldGraph.incidentEdges("E").get(3)), 23);

        assertTrue(oldGraph.endVertices(25).get(0).equals("G"));
        assertTrue(oldGraph.endVertices(25).get(1).equals("H"));

        assertTrue(oldGraph.endVertices(65).get(0).equals("C"));
        assertTrue(oldGraph.endVertices(65).get(1).equals("E"));

        assertTrue(oldGraph.areAdjacent("D", "I"));
        assertTrue(oldGraph.areAdjacent("E", "F"));
        assertTrue(oldGraph.areAdjacent("C", "D"));
        assertTrue(oldGraph.areAdjacent("I", "H"));

        assertFalse(oldGraph.areAdjacent("A", "F"));
        assertFalse(oldGraph.areAdjacent("C", "I"));

        assertSame(oldGraph.degree("E"), 4);
        assertSame(oldGraph.degree("A"), 2); // A-B was removed
        assertSame(oldGraph.degree("I"), 3);
        assertSame(oldGraph.degree("F"), 5);

        assertTrue(oldGraph.opposite("C", 9).equals("A"));
        assertTrue(oldGraph.opposite("E", 18).equals("F"));
        assertTrue(oldGraph.opposite("G", 39).equals("F"));
        assertTrue(oldGraph.opposite("E", 18).equals("F"));
        assertTrue(oldGraph.opposite("I", 19).equals("H"));
        assertTrue(oldGraph.opposite("F", 36).equals("B"));

        oldGraph.removeVertex("F");
        assertSame(oldGraph.amountOfEdges(), 12);

        assertTrue(oldGraph.opposite("F", 36) == null);
        assertTrue(oldGraph.opposite("F", 42) == null);
        assertTrue(oldGraph.opposite("F", 18) == null);
        assertTrue(oldGraph.opposite("F", 39) == null);
        assertTrue(oldGraph.opposite("F", 24) == null);

        assertFalse(oldGraph.areAdjacent("B", "F"));
    }
}