package HW13.tests;

import HW13.Graph;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.*;

/**
 * @author Sherafgan Kandov
 *         23.11.15
 */
public class GraphTests extends TestCase {
    @Test
    public void test() throws IOException, ClassNotFoundException {
        String line1 = "A B C D E F G H Kolya Vasya";
        String line2 = "A B 5 D Kolya 1 G Kolya 5 Kolya Vasya 12 F B 7";
        String[] parsedLine1 = line1.split("\\s");
        String[] parsedLine2 = line2.split("\\s");

        Graph<String, Integer> graph = new Graph<>();

        for (String s : parsedLine1) {
            graph.insertVertex(s);
        }

        for (int i = 0; i < parsedLine2.length; i += 3) {
            graph.insertEdge(parsedLine2[i], parsedLine2[i + 1], Integer.parseInt(parsedLine2[i + 2]));
        }

        //checking
        assertTrue(graph.amountOfVertices() == 10);
        assertTrue(graph.amountOfEdges() == 5);

        assertTrue(graph.areAdjacent("A", "B"));
        assertFalse(graph.areAdjacent("A", "C"));
        assertTrue(graph.opposite("Kolya", 12).equals("Vasya"));

        graph.removeVertex("Kolya");

        assertTrue(graph.amountOfEdges() == 2);
        assertTrue(graph.opposite("Kolya", 12) == null);

        //serialization
        FileOutputStream fos = new FileOutputStream("graphSerialized.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(graph);
        oos.flush();
        oos.close();

        //deserialization
        FileInputStream fis = new FileInputStream("graphSerialized.txt");
        ObjectInputStream oin = new ObjectInputStream(fis);
        Graph graphDeserialized = (Graph) oin.readObject();

        assertTrue(graphDeserialized.amountOfEdges() == 2);
        assertTrue(graphDeserialized.opposite("Kolya", 12) == null);

        assertTrue(graphDeserialized.areAdjacent("A", "B"));
        assertTrue(graphDeserialized.endVertices(7).get(0).equals("F"));
        assertTrue(graphDeserialized.endVertices(7).get(1).equals("B"));
    }

    @Test
    public void test2() {
        //Tests for 'complex' undirected weighted graph
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

        assertTrue(graph.opposite("B", 34).equals("H"));

        assertSame(Integer.parseInt(graph.incidentEdges("E").get(0)), 65);
        assertSame(Integer.parseInt(graph.incidentEdges("E").get(1)), 33);
        assertSame(Integer.parseInt(graph.incidentEdges("E").get(2)), 18);
        assertSame(Integer.parseInt(graph.incidentEdges("E").get(3)), 23);

        assertTrue(graph.endVertices(25).get(0).equals("G"));
        assertTrue(graph.endVertices(25).get(1).equals("H"));

        assertTrue(graph.endVertices(65).get(0).equals("C"));
        assertTrue(graph.endVertices(65).get(1).equals("E"));

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

        assertTrue(graph.opposite("C", 9).equals("A"));
        assertTrue(graph.opposite("E", 18).equals("F"));
        assertTrue(graph.opposite("G", 39).equals("F"));
        assertTrue(graph.opposite("E", 18).equals("F"));
        assertTrue(graph.opposite("I", 19).equals("H"));
        assertTrue(graph.opposite("F", 36).equals("B"));

        graph.removeVertex("F");
        assertSame(graph.amountOfEdges(), 12);

        assertTrue(graph.opposite("F", 36) == null);
        assertTrue(graph.opposite("F", 42) == null);
        assertTrue(graph.opposite("F", 18) == null);
        assertTrue(graph.opposite("F", 39) == null);
        assertTrue(graph.opposite("F", 24) == null);

        assertFalse(graph.areAdjacent("B", "F"));
    }
}