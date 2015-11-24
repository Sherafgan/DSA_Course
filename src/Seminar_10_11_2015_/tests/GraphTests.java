package Seminar_10_11_2015_.tests;

import Seminar_10_11_2015_.old.OldGraph;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author Sherafgan Kandov
 *         23.11.15
 */
public class GraphTests extends TestCase {
    @Test
    public void test() {
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

        assertSame(oldGraph.opposite("B", 34), "H");

        assertSame(oldGraph.incidentEdges("E").get(0), 65);
        assertSame(oldGraph.incidentEdges("E").get(1), 33);
        assertSame(oldGraph.incidentEdges("E").get(2), 18);
        assertSame(oldGraph.incidentEdges("E").get(3), 23);

        assertSame(oldGraph.endVertices(25).get(0), "G");
        assertSame(oldGraph.endVertices(25).get(1), "H");

        assertSame(oldGraph.endVertices(65).get(0), "C");
        assertSame(oldGraph.endVertices(65).get(1), "E");

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

        assertSame(oldGraph.opposite("C", 9), "A");
        assertSame(oldGraph.opposite("E", 18), "F");
        assertSame(oldGraph.opposite("G", 39), "F");
        assertSame(oldGraph.opposite("E", 18), "F");
        assertSame(oldGraph.opposite("I", 19), "H");

        oldGraph.removeVertex("F");
        assertSame(oldGraph.amountOfEdges(), 12);

        assertSame(oldGraph.opposite("F", 36), null);
        assertSame(oldGraph.opposite("F", 42), null);
        assertSame(oldGraph.opposite("F", 18), null);
        assertSame(oldGraph.opposite("F", 39), null);
        assertSame(oldGraph.opposite("F", 24), null);

        assertFalse(oldGraph.areAdjacent("B", "F"));
    }
}
