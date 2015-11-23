package Seminar_10_11_2015_.tests;

import Seminar_10_11_2015_.OldGraph;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author Sherafgan Kandov
 *         23.11.15
 */
public class GraphTests extends TestCase {
    @Test
    public void test() {
        OldGraph<String, String> oldGraph = new OldGraph<>();

        oldGraph.insertVertex("u");
        oldGraph.insertVertex("v");
        oldGraph.insertVertex("w");

        assertTrue(oldGraph.amountOfVertices() == 3);
        assertTrue(oldGraph.amountOfEdges() == 0);

        oldGraph.insertEdge("u", "v", "a");
    }
}
