package Seminar_10_11_2015_;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * @author Sherafgan Kandov
 *         24.11.15
 */
public class Traversals {
    private static final String IN_FILE_NAME = "cities.txt";
    private static final String OUT_FILE_NAME = "able.txt";

    private static final String EMPTY = "#";
    private static final String QUESTION = "?";
    private static final String EXCLAMATION = "!";

    private static final boolean WHITE = true;
    private static final boolean BLACK = false;

    static Graph<String, String> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader inFile = new BufferedReader(new FileReader(IN_FILE_NAME));
        String firstLine = inFile.readLine();
        String secondLine = inFile.readLine();
        inFile.close();

        String[] firstLineData = firstLine.trim().split("\\s");
        String[] secondLineData = secondLine.trim().split("\\s");

        graph = new Graph<>();

        readDataToGraph(firstLineData, secondLineData);

//        breadFirstSearch(graph);

//        depthFirstSearch(graph);

//        String[] ham = findHC(graph);

//        String[] hamC = findHamCir(graph);

//        breadFirstSearch(graph);

//        Queue<String> paths = new LinkedList<>();
//        paths.add("Rostov-R");
//        List<String> resultingPaths = findPath(paths);

        List<String> allPaths = allPaths("Melitopol-U", "Rostov-R");

        System.out.println(allPaths.size());
        String[] shortestPath = allPaths.get(0).split("\\s");
        for (String s : allPaths) {
            String[] tmpPath = s.split("\\s");
            if (shortestPath.length > tmpPath.length) {
                shortestPath = tmpPath;
            }
        }

        System.out.println(shortestPath.length);
        for (String s : shortestPath) {
            System.out.print(s + " ");
        }
    }

    public Map<String, List<String>> findPath(String path) {
        return null;
    }

//    static List<String> paths = new LinkedList<>();

    private static List<String> allPaths(String origin, String destination) {
        Queue<String> paths = new LinkedList<>();
        paths.add(origin);
        List<String> resultingPaths = new LinkedList<>();
        while (!paths.isEmpty()) {
            if (paths.size() > 4) {
                int i = 1;//debugging
            }
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
                if (child.equals(destination)) {
                    resultingPaths.add(path + " " + child);
                } else if (!child.equals(destination) && !child.equals(grandFather)) {
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

    private static List<String> findPath(Queue<String> paths) {
        List<String> resultingPaths = new LinkedList<>();
        while (!paths.isEmpty()) {
            if (paths.size() > 10) {
                int i = 1;//debugging
            }
            String path = paths.poll();
            String[] verticesInPath = path.split("\\s");
            String parent = verticesInPath[verticesInPath.length - 1];
            Graph.Vertex vertex = (Graph.Vertex) graph.getVertices().get(parent);
            List adjacencyList = vertex.getAdjacencyList();
            for (int i = 0; i < adjacencyList.size(); i++) {
                String tmp = (String) adjacencyList.get(i);
                String child = tmp.split("\\s")[1];
                if (child.equals("Rostov-R") && !existInPath(path, child)) {
                    int lengthOfPath = path.split("\\s").length;
                    if (lengthOfPath == graph.amountOfVertices()) {
                        resultingPaths.add(path + " " + child);
                    }
                } else if (!child.equals("Rostov-R") && !existInPath(path, child)) {
                    paths.add(path + " " + child);
                }
            }
        }

        return resultingPaths;
    }

    private static boolean existInPath(String path, String child) {
        String[] pathData = path.split("\\s");
        for (int i = pathData.length - 2; i > 0; i--) {
            if (pathData[i].equals(child)) {
                return true;
            }
        }
        return false;
    }

    private static String[] findHamCir(Graph<String, String> graph) {
        List<String[]> allPaths;
        allPaths = getAllPaths();
        return null;
    }

    private static List<String[]> getAllPaths() {
        Stack<Graph.Vertex> stack = new Stack<>();
        Iterator iterator = graph.getVertices().entrySet().iterator();
        Map.Entry pair = (Map.Entry) iterator.next();
        Graph.Vertex startingVertex = (Graph.Vertex) pair.getValue();
        String[] tmp = new String[graph.getVertices().size()];
        return null;
    }

    private static void BFS2(Graph.Vertex vertex) {
        Queue<Graph.Vertex> queue = new LinkedList<>();
        vertex.setColor(BLACK);
        queue.add(vertex);
        while (!queue.isEmpty()) {
            Graph.Vertex p = queue.poll();
            for (int i = 0; i < p.getAdjacencyList().size(); i++) {
                String edgeKey = (String) p.getAdjacencyList().get(i);
                Graph.Vertex vertex2 = graph.getVertices().get(edgeKey.trim().split("\\s")[1]);
                if (vertex2.getColor()) {
                    vertex2.setColor(BLACK);
                    queue.add(vertex2);
                }
            }
        }
    }

    private static void colorAllVerticesWhite(Graph<String, String> graph) {
        //coloring every vertex white color
        Iterator iterator = graph.getVertices().entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry) iterator.next();
            Graph.Vertex vertex = (Graph.Vertex) pair.getValue();
            vertex.setColor(WHITE);
        }
    }

    static Map<String, List<String>> verticesPaths = new LinkedHashMap<>();

    private static void breadFirstSearch(Graph<String, String> graph) {
        //coloring every vertex white color
//        Iterator iterator = graph.getVertices().entrySet().iterator();
//        while (iterator.hasNext()) {
//            Map.Entry pair = (Map.Entry) iterator.next();
//            Graph.Vertex vertex = (Graph.Vertex) pair.getValue();
//            vertex.setColor(WHITE);
//        }


        Iterator iterator = graph.getVertices().entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry) iterator.next();
            Graph.Vertex vertex = (Graph.Vertex) pair.getValue();
            if (vertex.getColor()) {
                BFS(vertex);

            }
        }
        if (true) {
            boolean check = true;
        }
    }

    private static void BFS(Graph.Vertex vertex) {
        Queue<Graph.Vertex> queue = new LinkedList<>();
        vertex.setColor(BLACK);
        queue.add(vertex);
        while (!queue.isEmpty()) {
            Graph.Vertex p = queue.poll();
            for (int i = 0; i < p.getAdjacencyList().size(); i++) {
                String edgeKey = (String) p.getAdjacencyList().get(i);
                Graph.Vertex vertex2 = graph.getVertices().get(edgeKey.trim().split("\\s")[1]);
                if (vertex2.getColor()) {
                    vertex2.setColor(BLACK);
                    queue.add(vertex2);
                }
            }
        }
    }

    private static boolean isOrigin(Graph.Vertex vertex, Graph.Vertex origin) {
        return vertex.getValue().equals(origin.getValue());
    }

    public static void depthFirstSearch(Graph graph) {
        //coloring every vertex white color
        Iterator iterator = graph.getVertices().entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry) iterator.next();
            Graph.Vertex vertex = (Graph.Vertex) pair.getValue();
            vertex.setColor(WHITE);
        }

        iterator = graph.getVertices().entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry) iterator.next();
            Graph.Vertex vertex = (Graph.Vertex) pair.getValue();
            if (vertex.getColor()) {
                DFS(vertex);
            }
        }
    }

    private static void DFS(Graph.Vertex vertex) {
        vertex.setColor(BLACK);
        for (int i = 0; i < vertex.getAdjacencyList().size(); i++) {
            String edgeKey = (String) vertex.getAdjacencyList().get(i);
            Graph.Vertex vertex2 = graph.getVertices().get(edgeKey.trim().split("\\s")[1]);
            if (vertex2.getColor()) {
                DFS(vertex2);
            }
        }
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

    static class Path {
        private List<Graph.Vertex> listOfVertices;

        public Path() {
            listOfVertices = new ArrayList<>();
        }

        public void addToPath(Graph.Vertex vertex) {
            listOfVertices.add(vertex);
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
                this.color = WHITE;
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
