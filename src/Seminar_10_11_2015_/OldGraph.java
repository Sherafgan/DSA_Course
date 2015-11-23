package Seminar_10_11_2015_;

import java.util.*;

/**
 * @author Sherafgan Kandov
 *         19.11.15
 */
public class OldGraph<TDataValue, TWeight> {
    private List<Vertex> vertices = new ArrayList<>();
    private List<Edge> edges = new ArrayList<>();
    private List<Integer> adjacencyList = new ArrayList<>();


    public void insertVertex(TDataValue value) {
        Vertex vertexToAdd = new Vertex(value, vertices.size());
        vertices.add(vertexToAdd);
    }

    public void insertEdge(TDataValue origin, TDataValue destination, TWeight weight) {
        Edge edgeToAdd = new Edge(origin, destination, weight, edges.size());
        edges.add(edgeToAdd);
    }

    public void removeVertex(TDataValue value) {
        //Complexity: O(#ofEdges * #ofVertices)
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).getValue().equals(value)) {
                vertices.remove(i);
                deleteIncidentEdges(i);
            }
        }
    }

    public void removeVertex(TDataValue value, int listPosition) {
        //Complexity: O(1)
        if (vertices.get(listPosition).getValue().equals(value)) {
            vertices.remove(listPosition);
            deleteIncidentEdges(listPosition);
        }
    }

    private void deleteIncidentEdges(int i) {
        for (int j = 0; j < edges.size(); j++) {
            if (edges.get(j).getDestination() == i) {
                edges.get(j).setDestination(-1);
            }
            if (edges.get(j).getOrigin() == i) {
                edges.get(j).setOrigin(-1);
            }
        }
    }

    public void removeEdge(TWeight weight) {
        //Complexity: O(# of edges)
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).getWeight().equals(weight)) {
                edges.remove(i);
                removeReferences(i);
            }
        }
    }

    private void removeReferences(int index) {
        //Complexity: O(#ofVertices * adjacencyLengthOfEachVertex)
        adjacencyList.set(edges.get(index).getAdjacencyListPosition1(), null);
        adjacencyList.set(edges.get(index).getAdjacencyListPosition2(), null);
    }

    private void removeEdge(TWeight weight, int listPosition) {
        //Complexity: O(1)
        if (edges.get(listPosition).getWeight().equals(weight)) {
            edges.remove(listPosition);
            removeReferences(listPosition);
        }
    }

    public TWeight[] incidentEdges(TDataValue value) {
        return null;
    }

    public TDataValue[] endVertices(TWeight weight) {
        //TODO implement
        //return ending vertices of edge with 'value'
        return null;
    }

    public TDataValue opposite(TDataValue value, TWeight weight) {
        //TODO implement
        //return opposite vertex to vertex with 'value' and 'weight' of incident edge
        return null;
    }

    public boolean areAdjacent(TDataValue value1, TDataValue value2) {
        //Complexity: O(# of edges)
        for (int i = 0; i < edges.size(); i++) {
            if (vertices.get(edges.get(i).getOrigin()).equals(value1) &&
                    vertices.get(edges.get(i).getDestination()).equals(value2)) {
                return true;
            }
        }
        return false;
    }

    public int degree(TDataValue value) {
        //Complexity: O(1 + #ofVertices)
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).getValue().equals(value)) {
//                return vertices.get(i).getAjacencyList().size();
            }
        }
        return -1;
    }

    public int amountOfVertices() {
        return vertices.size();
    }

    public int amountOfEdges() {
        return edges.size();
    }

    private class Vertex {
        private TDataValue value;
        private int listPosition;
        private int adjacencyListPosition;

        public Vertex(TDataValue value, int listPosition) {
            this.value = value;
        }

        public TDataValue getValue() {
            return value;
        }

        public void setValue(TDataValue value) {
            this.value = value;
        }

        public int getListPosition() {
            return listPosition;
        }

        public void setListPosition(int listPosition) {
            this.listPosition = listPosition;
        }

        public int getAdjacencyListPosition() {
            return adjacencyListPosition;
        }

        public void setAdjacencyListPosition(int adjacencyListPosition) {
            this.adjacencyListPosition = adjacencyListPosition;
        }
    }

    private class Edge {
        private TDataValue origin;
        private TDataValue destination;
        private TWeight weight;
        private int listPosition;

        private int adjacencyListPosition1;
        private int getAdjacencyListPosition2;

        public Edge(TDataValue origin, TDataValue destination, TWeight weight, int listPosition) {
            this.origin = origin;
            this.destination = destination;
            this.weight = weight;
            this.listPosition = listPosition;
        }

        public TDataValue getOrigin() {
            return origin;
        }

        public void setOrigin(TDataValue origin) {
            this.origin = origin;
        }

        public TDataValue getDestination() {
            return destination;
        }

        public void setDestination(TDataValue destination) {
            this.destination = destination;
        }

        public TWeight getWeight() {
            return weight;
        }

        public void setWeight(TWeight weight) {
            this.weight = weight;
        }

        public int getListPosition() {
            return listPosition;
        }

        public void setListPosition(int listPosition) {
            this.listPosition = listPosition;
        }

        public int getAdjacencyListPosition1() {
            return adjacencyListPosition1;
        }

        public void setAdjacencyListPosition1(int adjacencyListPosition1) {
            this.adjacencyListPosition1 = adjacencyListPosition1;
        }

        public int getAdjacencyListPosition2() {
            return getAdjacencyListPosition2;
        }

        public void setGetAdjacencyListPosition2(int getAdjacencyListPosition2) {
            this.getAdjacencyListPosition2 = getAdjacencyListPosition2;
        }
    }
}
