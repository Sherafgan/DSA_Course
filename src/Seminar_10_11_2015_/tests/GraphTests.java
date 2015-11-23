package Seminar_10_11_2015_.tests;

import Seminar_10_11_2015_.Graph;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author Sherafgan Kandov
 *         23.11.15
 */
public class GraphTests extends TestCase {
    @Test
    public void test() {
        //All the tests in this method are done to the following graph
        //          v
        //         / \
        //        /   \
        //     a /     \ b
        //      /       \
        //     /    c    \
        //    u-----------w

        Graph<String, String> graph = new Graph<>();

        graph.insertVertex("u");
        graph.insertVertex("v");
        graph.insertVertex("w");

        assertTrue(graph.amountOfVertices() == 3);
        assertTrue(graph.amountOfEdges() == 0);

        graph.insertEdge("u", "v", "a");
        graph.insertEdge("v", "w", "b");

        assertTrue(graph.amountOfEdges() == 2);

        graph.removeVertex("v");

        assertTrue(graph.amountOfEdges() == 0);
        assertTrue(graph.amountOfVertices() == 2);

        graph.insertVertex("v");
        graph.insertEdge("u", "v", "a");
        graph.insertEdge("v", "w", "b");
        graph.insertEdge("u", "w", "c");

        assertTrue(graph.amountOfVertices() == 3);
        assertTrue(graph.amountOfEdges() == 3);

        assertSame(graph.degree("u"), 2);
        assertSame(graph.degree("v"), 2);

        assertEquals(graph.endVertices("a").get(0), "u");
        assertEquals(graph.endVertices("a").get(1), "v");

        assertEquals(graph.opposite("u", "a"), "v");

        assertEquals(graph.incidentEdges("v").get(0), "a");
        assertEquals(graph.incidentEdges("v").get(1), "b");

        graph.removeAllEdgesWithWeight("a");

        assertTrue(graph.amountOfEdges() == 2);
        assertFalse(graph.areAdjacent("u", "v"));
        assertTrue(graph.areAdjacent("u", "w"));

        assertSame(graph.degree("u"), 1);
        assertSame(graph.degree("v"), 1);

        assertEquals(graph.endVertices("b").get(0), "v");
        assertEquals(graph.endVertices("b").get(1), "w");

        assertEquals(graph.opposite("v", "b"), "w");
        assertEquals(graph.opposite("w", "b"), "v");

        assertEquals(graph.incidentEdges("v").get(0), "b");
    }

    @Test
    public void test2() {
        //Tests for another more complex undirected weighted graph
        //Link to image of graph: http://i.stack.imgur.com/kJF5K.png

        Graph<String, Integer> graph = new Graph<>();

        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertVertex("E");
        graph.insertVertex("F");
        graph.insertVertex("G");
        graph.insertVertex("H");
        graph.insertVertex("I");

        assertSame(graph.amountOfVertices(), 9);

        graph.insertEdge("A", "B", 22);
        graph.insertEdge("A", "C", 9);
        graph.insertEdge("A", "D", 12);

        graph.insertEdge("B", "C", 35);
        graph.insertEdge("B", "F", 36);
        graph.insertEdge("B", "H", 34);

        graph.insertEdge("C", "D", 4);
        graph.insertEdge("C", "E", 65);
        graph.insertEdge("C", "F", 42);

        graph.insertEdge("D", "E", 33);
        graph.insertEdge("D", "I", 30);

        graph.insertEdge("E", "F", 18);
        graph.insertEdge("E", "G", 23);

        graph.insertEdge("F", "G", 39);
        graph.insertEdge("F", "H", 24);

        graph.insertEdge("G", "H", 25);
        graph.insertEdge("G", "I", 21);

        graph.insertEdge("H", "I", 19);

        assertSame(graph.amountOfEdges(), 18);

        graph.removeEdge("A", "B", 22);
        assertSame(graph.amountOfEdges(), 17);

        assertSame(graph.opposite("B", 34), "H");

        assertSame(graph.incidentEdges("E").get(0), 65);
        assertSame(graph.incidentEdges("E").get(1), 33);
        assertSame(graph.incidentEdges("E").get(2), 18);
        assertSame(graph.incidentEdges("E").get(3), 23);

        assertSame(graph.endVertices(25).get(0), "G");
        assertSame(graph.endVertices(25).get(1), "H");

        assertSame(graph.endVertices(65).get(0), "C");
        assertSame(graph.endVertices(65).get(1), "E");

        assertTrue(graph.areAdjacent("D", "I"));
        assertTrue(graph.areAdjacent("E", "F"));
        assertTrue(graph.areAdjacent("C", "D"));
        assertTrue(graph.areAdjacent("I", "H"));

        assertFalse(graph.areAdjacent("A", "F"));
        assertFalse(graph.areAdjacent("C", "I"));

        assertSame(graph.degree("E"), 4);
        assertSame(graph.degree("A"), 2); // A-B was removed
        assertSame(graph.degree("I"), 3);
        assertSame(graph.degree("F"), 5);

        assertSame(graph.opposite("C", 9), "A");
        assertSame(graph.opposite("E", 18), "F");
        assertSame(graph.opposite("G", 39), "F");
        assertSame(graph.opposite("E", 18), "F");
        assertSame(graph.opposite("I", 19), "H");

        graph.removeVertex("F");
        assertSame(graph.amountOfEdges(), 12);

        assertSame(graph.opposite("F", 36), null);
        assertSame(graph.opposite("F", 42), null);
        assertSame(graph.opposite("F", 18), null);
        assertSame(graph.opposite("F", 39), null);
        assertSame(graph.opposite("F", 24), null);

        assertFalse(graph.areAdjacent("B", "F"));
    }
}
