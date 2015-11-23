package Seminar_10_11_2015_;

import java.util.*;

/**
 * @author Sherafgan Kandov
 *         19.11.15
 */
public class Graph<TDataValue, TWeight> {
    private List<Vertex> vertices = new ArrayList<>();
    private List<Edge> edges = new ArrayList<>();


    public void insertVertex(TDataValue value) {
        //Complexity: O(#ofVertices)
        if (vertexExists(value)) { //O(#ofVertices)
            throw new IllegalArgumentException("Vertex with value '" + value + "' already exists in graph!");
        } else {
            //Complexity: O(1)
            Vertex vertexToAdd = new Vertex(value);
            vertices.add(vertexToAdd);
        }
    }

    public void insertEdge(TDataValue origin, TDataValue destination, TWeight weight) {
        if (!vertexExists(origin)) {
            throw new NullPointerException("'There is no 'origin' vertex!");
        } else if (!vertexExists(destination)) {
            throw new NullPointerException("'There is no 'destination' vertex!");
        } else {
            //Complexity: O(1)
            Edge edgeToAdd = new Edge(origin, destination, weight);
            edges.add(edgeToAdd);
        }
    }

    private boolean vertexExists(TDataValue vertexValue) {
        //Complexity: O(#ofVertices)
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).getValue().equals(vertexValue)) {
                return true;
            }
        }
        return false;
    }

    public void removeVertex(TDataValue value) {
        //Complexity: O(#ofVertices * #ofEdges)
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).getValue().equals(value)) {
                deleteIncidentEdges(i); //O(#ofEdges)
                vertices.remove(i);
            }
        }
    }

    private void deleteIncidentEdges(int indexOfVertexInTheList) {
        //Complexity: O(#ofEdges)
        int i = 0;
        boolean p = true;
        while (i < edges.size()) {
            if (edges.size() > 0
                    && edges.get(i).getOrigin().equals(vertices.get(indexOfVertexInTheList).getValue())) {
                edges.remove(i);
                p = false;
            }
            if (edges.size() > 0
                    && edges.get(i).getDestination().equals(vertices.get(indexOfVertexInTheList).getValue())) {
                edges.remove(i);
                p = false;
            }
            if (p) {
                i++;
            } else {
                p = true;
            }
        }
    }

    public void removeEdge(TDataValue origin, TDataValue destination, TWeight weight) {
        //Complexity: O(#ofEdges)
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).getWeight().equals(weight)
                    && edges.get(i).getOrigin().equals(origin)
                    && edges.get(i).getDestination().equals(destination)) {
                edges.remove(i);
            }
        }
    }

    public void removeAllEdgesWithWeight(TWeight weight) {
        //Complexity: O(#ofEdges)
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).getWeight().equals(weight)) {
                edges.remove(i);
            }
        }
    }

    public List<TWeight> incidentEdges(TDataValue vertexValue) {
        //Complexity: O(#ofEdges)
        List<TWeight> incidentEdges = new ArrayList<>();
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).getOrigin().equals(vertexValue)
                    || edges.get(i).getDestination().equals(vertexValue)) {
                incidentEdges.add(edges.get(i).getWeight());
            }
        }
        if (incidentEdges.size() == 0) {
            return null;
        } else {
            return incidentEdges;
        }
    }

    public List<TDataValue> endVertices(TWeight weight) {
        //Complexity: O(#ofEdges)
        List<TDataValue> endVertices = new ArrayList<>();
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).getWeight().equals(weight)) {
                endVertices.add(edges.get(i).getOrigin());
                endVertices.add(edges.get(i).getDestination());
                return endVertices;
            }
        }
        return null;
    }

    public TDataValue opposite(TDataValue vertexValue, TWeight weight) {
        //Complexity: O(#ofEdges)
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).getOrigin().equals(vertexValue) && edges.get(i).getWeight().equals(weight)) {
                return edges.get(i).getDestination();
            } else if (edges.get(i).getDestination().equals(vertexValue) && edges.get(i).getWeight().equals(weight)) {
                return edges.get(i).getOrigin();
            }
        }
        return null;
    }

    public boolean areAdjacent(TDataValue firstVertexValue, TDataValue secondVertexValue) {
        //Complexity: O(#ofEdges)
        for (int i = 0; i < edges.size(); i++) {
            if ((edges.get(i).getOrigin().equals(firstVertexValue)
                    && edges.get(i).getDestination().equals(secondVertexValue))
                    || (edges.get(i).getDestination().equals(firstVertexValue)
                    && edges.get(i).getOrigin().equals(secondVertexValue))) {
                return true;
            }
        }
        return false;
    }

    public int degree(TDataValue vertexValue) {
        //Complexity: O(#ofEdges)
        //implementation works only for ACYCLIC GRAPH
        int degree = 0;
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).getOrigin().equals(vertexValue)
                    || edges.get(i).getDestination().equals(vertexValue)) {
                degree++;
            }
        }
        return degree;
    }

    public int amountOfVertices() {
        return vertices.size();
    }

    public int amountOfEdges() {
        return edges.size();
    }

    private class Vertex {
        private TDataValue value;

        public Vertex(TDataValue value) {
            this.value = value;
        }

        public TDataValue getValue() {
            return value;
        }

        public void setValue(TDataValue value) {
            this.value = value;
        }
    }

    private class Edge {
        private TDataValue origin;
        private TDataValue destination;
        private TWeight weight;

        public Edge(TDataValue origin, TDataValue destination, TWeight weight) {
            this.origin = origin;
            this.destination = destination;
            this.weight = weight;
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
    }
}
