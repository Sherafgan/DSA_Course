package Assignment4;

import java.io.*;
import java.util.*;

/**
 * @author Sherafgan Kandov
 *         26.11.15
 */
public class ProblemC {
    private static final String IN_FILE_NAME = "cities.txt";
    private static final String OUT_FILE_NAME = "travel.txt";

    private static final String HOMETOWN = "Rostov-R";
    private static final String CURRENT_TOWN = "Melitopol-U";

    private static final String EMPTY = "#";
    private static final String QUESTION = "?";
    private static final String EXCLAMATION = "!";

    static Graph<String, String> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader inFile = new BufferedReader(new FileReader(IN_FILE_NAME));
        String firstLine = inFile.readLine();
        String secondLine = inFile.readLine();
        inFile.close();

        String lineToPrint = "";

        if (firstLine != null) {
            String[] firstLineData = firstLine.trim().split("\\s");
            String[] secondLineData = secondLine.trim().split("\\s");

            graph = new Graph<>();

            readDataToGraph(firstLineData, secondLineData);

            //case 1
            List<String> allPaths = allPaths(CURRENT_TOWN, HOMETOWN);

            String[] shortestPath = allPaths.get(0).split("\\s");
            for (String s : allPaths) {
                String[] tmpPath = s.split("\\s");
                if (shortestPath.length > tmpPath.length) {
                    shortestPath = tmpPath;
                }
            }

            lineToPrint = lineToPrint + (shortestPath.length - 1);
            for (String cityName : shortestPath) {
                lineToPrint = lineToPrint + " " + cityName;
            }

            lineToPrint = lineToPrint + "\n";

            //case 2
            allPaths = allPaths("Sukhumi-DG", "Lugansk-DU");

            shortestPath = allPaths.get(0).split("\\s");
            for (String s : allPaths) {
                String[] tmpPath = s.split("\\s");
                if (shortestPath.length > tmpPath.length) {
                    shortestPath = tmpPath;
                }
            }

            lineToPrint = lineToPrint + (shortestPath.length - 1);
            for (String cityName : shortestPath) {
                lineToPrint = lineToPrint + " " + cityName;
            }
        }

        //write data
        PrintWriter outFile = new PrintWriter(new FileWriter(OUT_FILE_NAME));
        outFile.write(lineToPrint);
        outFile.close();
    }

    private static List<String> allPaths(String from, String to) {
        Queue<String> paths = new LinkedList<>();
        paths.add(from);
        List<String> resultingPaths = new LinkedList<>();
        while (!paths.isEmpty()) {
            String path = paths.poll();
            String[] verticesInPath = path.split("\\s");
            String parent = verticesInPath[verticesInPath.length - 1];
            String grandFather = " ";
            if (verticesInPath.length > 1) {
                grandFather = verticesInPath[verticesInPath.length - 2];
            }
            Graph.Vertex vertex = (Graph.Vertex) graph.getVertices().get(parent);
            List adjacencyList = vertex.getAdjacencyList();
            for (int i = 0; i < adjacencyList.size(); i++) {
                String tmp = (String) adjacencyList.get(i);
                String child = tmp.split("\\s")[1];
                if (child.equals(to)) {
                    resultingPaths.add(path + " " + child);
                } else if (!child.equals(to) && !child.equals(grandFather)) {
                    String pathToAdd = path + " " + child;
                    int amountOfVerticesContained = pathToAdd.split("\\s").length;
                    if (amountOfVerticesContained < graph.amountOfVertices()) {
                        paths.add(pathToAdd);
                    }
                }
            }
        }

        return resultingPaths;
    }

    private static void readDataToGraph(String[] firstLineData, String[] secondLineData) {
        for (String s : firstLineData) {
            graph.insertVertex(s);
        }

        for (int i = 0; i < secondLineData.length; i += 2) {
            if (((secondLineData[i].substring(secondLineData[i].length() - 2).equals("-R")
                    && secondLineData[i + 1].substring(secondLineData[i + 1].length() - 3).equals("-DU"))
                    || (secondLineData[i].substring(secondLineData[i].length() - 3).equals("-DU")
                    && secondLineData[i + 1].substring(secondLineData[i + 1].length() - 2).equals("-R")))
                    || ((secondLineData[i].substring(secondLineData[i].length() - 2).equals("-R")
                    && secondLineData[i + 1].substring(secondLineData[i + 1].length() - 3).equals("-DG"))
                    || (secondLineData[i].substring(secondLineData[i].length() - 3).equals("-DG")
                    && secondLineData[i + 1].substring(secondLineData[i + 1].length() - 2).equals("-R")))) {
                graph.insertEdge(secondLineData[i], secondLineData[i + 1], EXCLAMATION);
            } else if ((secondLineData[i].substring(secondLineData[i].length() - 2).equals("-U")
                    || secondLineData[i].substring(secondLineData[i].length() - 2).equals("-G")) ||
                    (secondLineData[i + 1].substring(secondLineData[i + 1].length() - 2).equals("-U")
                            || secondLineData[i + 1].substring(secondLineData[i + 1].length() - 2).equals("-G"))) {
                graph.insertEdge(secondLineData[i], secondLineData[i + 1], QUESTION);
            } else {
                graph.insertEdge(secondLineData[i], secondLineData[i + 1], EMPTY);
            }
        }
    }

    static class Graph<TDataValue, TWeight> {
        private HashMap<TDataValue, Vertex> vertices;
        private HashMap<String, Edge> edges;

        public Graph() {
            vertices = new HashMap<>();
            edges = new HashMap<>();
        }


        public void insertVertex(TDataValue value) {
            //Complexity: O(#ofVertices)
            if (vertexExists(value)) { //O(#ofVertices)
                throw new IllegalArgumentException("Vertex with value '" + value + "' already exists in graph!");
            } else {
                //Complexity: O(1)
                Vertex vertexToAdd = new Vertex(value);
                vertices.put(value, vertexToAdd);
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
                String edgeKey = makeKeyForEdge(origin, destination, weight);
                edges.put(edgeKey, edgeToAdd);
                vertices.get(origin).setEdgeToAdjacencyList(edgeKey);
                edgeKey = makeKeyForEdge(destination, origin, weight);
                vertices.get(destination).setEdgeToAdjacencyList(edgeKey);
            }
        }

        private String makeKeyForEdge(TDataValue origin, TDataValue destination, TWeight weight) {
            return "" + origin + " " + destination + " " + weight;
        }

        private boolean vertexExists(TDataValue vertexValue) {
            //Complexity: O(1)
            if (vertices.get(vertexValue) != null && vertices.get(vertexValue).getValue().equals(vertexValue)) {
                return true;
            } else {
                return false;
            }
        }

        public void removeVertex(TDataValue vertexValue) {
            //Complexity: O(#incidentEdges) => O(1)
            //delete all incident edges
            int tmp = vertices.get(vertexValue).getAdjacencyList().size();
            for (int i = 0; i < tmp; i++) {
                String edgeKey = vertices.get(vertexValue).getAdjacencyList().get(0);
                boolean p1 = true;
                boolean p2 = true;
                if (p1) {
                    for (int j = 0; j < vertices.get(edgeKey.split("\\s")[1]).getAdjacencyList().size() && p1; j++) {
                        String[] otherTableEdgeKey = vertices.get(edgeKey.split("\\s")[1]).getAdjacencyList().get(j).split("\\s");
                        if (otherTableEdgeKey[0].equals(vertexValue) || otherTableEdgeKey[1].equals(vertexValue)) {
                            vertices.get(edgeKey.split("\\s")[1]).getAdjacencyList().remove(j);
                            p1 = false;
                        }
                    }
                }
                if (p2) {
                    for (int j = 0; j < vertices.get(edgeKey.split("\\s")[0]).getAdjacencyList().size() && p2; j++) {
                        String[] otherTableEdgeKey = vertices.get(edgeKey.split("\\s")[0]).getAdjacencyList().get(j).split("\\s");
                        if (otherTableEdgeKey[0].equals(vertexValue) || otherTableEdgeKey[1].equals(vertexValue)) {
                            vertices.get(edgeKey.split("\\s")[0]).getAdjacencyList().remove(j);
                            p2 = false;
                        }
                    }
                }
                if (edges.remove(edgeKey) == null) {
                    String[] splitterEdgeKey = edgeKey.split("\\s");
                    edges.remove("" + splitterEdgeKey[1] + " " + splitterEdgeKey[0] + " " + splitterEdgeKey[2]);
                }
            }
            //delete vertex
            vertices.remove(vertexValue);
        }

        public void removeEdge(TDataValue origin, TDataValue destination, TWeight weight) {
            //Complexity: O(1)
            String keyOfEdgeToRemove = makeKeyForEdge(origin, destination, weight);
            vertices.get(origin).getAdjacencyList().remove(keyOfEdgeToRemove);
            vertices.get(destination).getAdjacencyList().remove(keyOfEdgeToRemove);
            edges.remove(keyOfEdgeToRemove);
        }

        public void removeAllEdgesWithWeight(TWeight weight) {
            //Complexity: O(#ofEdges)
            for (int i = 0; i < edges.size(); i++) {
                if (edges.get(i).getWeight().equals(weight)) {
                    edges.remove(i);
                }
            }
        }

        public List<String> incidentEdges(TDataValue vertexValue) {
            //Complexity: O(#incidentEdges) => O(1)
            List<String> incidentEdges = new ArrayList<>();
            for (int i = 0; i < vertices.get(vertexValue).getAdjacencyList().size(); i++) {
                incidentEdges.add(vertices.get(vertexValue).getAdjacencyList().get(i).split("\\s")[2]);
            }
            if (incidentEdges.size() == 0) {
                return null;
            } else {
                return incidentEdges;
            }
        }

        public List<String> endVertices(TWeight weight) {
            //Complexity: O(#ofEdges)
            List<String> endVertices = new ArrayList<>();
            Collection<Edge> edgesList = edges.values();
            for (Edge edge : edgesList) {
                if (edge.getWeight().equals(weight)) {
                    endVertices.add("" + edge.getOrigin());
                    endVertices.add("" + edge.getDestination());
                    return endVertices;
                }
            }
            return null;
        }

        public String opposite(TDataValue vertexValue, TWeight weight) {
            //Complexity: O(#incidentEdges) => O(1)
            if (vertices.get(vertexValue) != null) {
                for (int i = 0; i < vertices.get(vertexValue).getAdjacencyList().size(); i++) {
                    String[] edgeKeyData = vertices.get(vertexValue).getAdjacencyList().get(i).split("\\s");
                    if (edgeKeyData[2].equals("" + weight)) {
                        return edgeKeyData[1];
                    }
                }
            }
            return null;
        }

        public boolean areAdjacent(TDataValue firstVertexValue, TDataValue secondVertexValue) {
            //Complexity: O(#incidentEdges) => O(1)
            if (vertices.get(firstVertexValue) != null) {
                for (int i = 0; i < vertices.get(firstVertexValue).getAdjacencyList().size(); i++) {
                    if (vertices.get(firstVertexValue).getAdjacencyList().get(i).split("\\s")[1].equals(secondVertexValue)) {
                        return true;
                    }
                }
            }
            return false;
        }

        public int degree(TDataValue vertexValue) {
            //Complexity: O(1)
            return vertices.get(vertexValue).getAdjacencyList().size();
        }

        public int amountOfVertices() {
            return vertices.size();
        }

        public int amountOfEdges() {
            return edges.size();
        }

        public HashMap<TDataValue, Vertex> getVertices() {
            return vertices;
        }

        public HashMap<String, Edge> getEdges() {
            return edges;
        }

        class Vertex {
            private TDataValue value;
            private List<String> adjacencyList;
            private boolean color; //for  DFS or BFS

            public Vertex(TDataValue value) {
                this.value = value;
                adjacencyList = new ArrayList<>();
            }

            public TDataValue getValue() {
                return value;
            }

            public void setValue(TDataValue value) {
                this.value = value;
            }

            public List<String> getAdjacencyList() {
                return adjacencyList;
            }

            public void setEdgeToAdjacencyList(String edgeKey) {
                adjacencyList.add(edgeKey);
            }

            public boolean getColor() {
                return color;
            }

            public void setColor(boolean color) {
                this.color = color;
            }
        }

        class Edge {
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
}
