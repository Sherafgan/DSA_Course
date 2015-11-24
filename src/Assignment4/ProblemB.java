package Assignment4;

import java.util.*;

/**
 * @author Sherafgan Kandov
 *         24.11.15
 */
public class ProblemB {
    private static final String IN_FILE_NAME = "cities.txt";
    private static final String OUT_FILE_NAME = "able.txt";

    static Graph<String, String> graph;

    public static void main(String[] args) {
        
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
            //Complexity: O(#ofIncidentEdges) => O(1)
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

        public List<String> adjacentVertices(TDataValue vertexValue) {
            //Complexity: O(#ofAdjacentVertices) => O(1)
            List<String> adjacentVertices = new ArrayList<>();
            for (int i = 0; i < vertices.get(vertexValue).getAdjacencyList().size(); i++) {
                adjacentVertices.add(vertices.get(vertexValue).getAdjacencyList().get(i).split("\\s")[1]);
            }
            return adjacentVertices;
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

        private class Vertex {
            private TDataValue value;
            private List<String> adjacencyList;

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

    static class MyHeapSort {
        public Comparable[] heapsort(Comparable[] array) {
            Comparable[] sortedArray = new Comparable[array.length];

            MyPriorityQueue<Object> myPriorityQueue = new MyPriorityQueue<>();
            for (int i = 0; i < array.length; i++) {
                myPriorityQueue.add(array[i]);
            }

            for (int i = 0; i < sortedArray.length; i++) {
                sortedArray[i] = (Comparable) myPriorityQueue.poll();
            }

            return sortedArray;
        }

        class MyPriorityQueue<V> {
            private MyBinaryHeap<? extends Comparable<V>, V> myBinaryHeap;

            public MyPriorityQueue() {
                myBinaryHeap = new MyBinaryHeap();
            }

            public int size() {
                return myBinaryHeap.size();
            }

            public boolean isEmpty() {
                return myBinaryHeap.isEmpty();
            }

            public boolean contains(Object o) {
                return myBinaryHeap.containsValue(o);
            }

            public boolean offer(Object o) {
                return myBinaryHeap.put(o, o);
            }

            public boolean add(V v) {
                return offer(v);
            }

            public void clear() {
                myBinaryHeap.clear();
            }

            public V remove() {
                return myBinaryHeap.removeMin();
            }

            public V poll() {
                return myBinaryHeap.removeMin();
            }

            public V peek() {
                return myBinaryHeap.getMin();
            }
        }

        class MyComparator implements Comparator {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof String && o2 instanceof String) {
                    String s1 = (String) o1;
                    String s2 = (String) o2;

                    int n;
                    if (s1.length() > s2.length()) {
                        n = s2.length();
                    } else {
                        n = s1.length();
                    }

                    for (int i = 0; i < n; i++) {
                        int c1;
                        if (s1.charAt(i) > 90) {
                            c1 = s1.charAt(i) - 32;
                        } else {
                            c1 = s1.charAt(i);
                        }

                        int c2;
                        if (s2.charAt(i) > 90) {
                            c2 = s2.charAt(i) - 32;
                        } else {
                            c2 = s2.charAt(i);
                        }

                        if (c1 > c2) {
                            return 1;
                        } else if (c1 < c2) {
                            return -1;
                        }
                    }

                    return 0;
                } else {
                    Comparable comp1 = (Comparable) o1;
                    Comparable comp2 = (Comparable) o2;

                    return comp1.compareTo(comp2);
                }
            }
        }

        class MyBinaryHeap<K extends Comparable, V> implements Map {
            private static final int DEFAULT_SIZE_OF_ARRAY = 16;
            private static final int ROOT_INDEX = 0;

            private Node<K, V>[] heap;
            private int size;

            MyComparator myComparator;

            public MyBinaryHeap() {
                heap = new Node[DEFAULT_SIZE_OF_ARRAY];
                size = 0;

                myComparator = new MyComparator();
            }

            public MyBinaryHeap(K[] keys, V[] values) {
                this();
                for (int i = 0; i < keys.length; i++) {
                    this.put(keys[i], values[i]);
                }
            }

            @Override
            public int size() {
                return size;
            }

            @Override
            public boolean isEmpty() {
                return size == 0;
            }

            @Override
            public boolean containsKey(Object key) {
                for (int i = 0; i < size; i++) {
                    if (myComparator.compare(heap[i].getKey(), key) == 0) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public boolean containsValue(Object value) {
                for (int i = 0; i < size; i++) {
                    if (heap[i].getValue().equals(value)) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public V get(Object key) {
                return null;
            }

            @Override
            public Boolean put(Object key, Object value) {
                if (key != null && value != null) {
                    if (heap[ROOT_INDEX] == null) {
                        heap[ROOT_INDEX] = new Node((Comparable) key, value);
                    } else {
                        heap[size] = new Node((Comparable) key, value);
                        upHeap(size);
                    }
                    size++;

                    if (size == heap.length) {
                        doubleHeapSize();
                    }

                    return true;
                } else {
                    throw new NullPointerException("Input has 'null' key or value!");
                }
            }

            private void doubleHeapSize() {
                Node<K, V>[] newHeap = new Node[heap.length * 2];
                for (int i = 0; i < heap.length; i++) {
                    newHeap[i] = heap[i];
                }
                heap = newHeap;
            }

            private void upHeap(int indexOfNode) {
                Node node = heap[indexOfNode];
                int i = indexOfNode;
//        while (i > 0 && heap[(i - 1) / 2].getKey().compareTo(node.getKey()) == 1) { //was
                while (i > 0 && (myComparator.compare(heap[(i - 1) / 2].getKey(), node.getKey()) == 1)) {
                    Node tmp = heap[i];
//            if (i == size) {
                    heap[i] = heap[(i - 1) / 2];
                    heap[(i - 1) / 2] = tmp;
//            } else {
//                heap[i] = heap[i / 2];
//                heap[i / 2] = tmp;
//            }

                    i = (i - 1) / 2;
                }
            }

            @Override
            public Object remove(Object key) {
                return null;
            }

            private void downHeap(int indexOfNode) {
                int i = indexOfNode;
                int childIndex = findSmallerChild(i);

//        while (childIndex != 0 && heap[childIndex].getKey().compareTo(heap[i].key) == 1) { //was
                while (childIndex != 0 && (myComparator.compare(heap[childIndex].getKey(), heap[i].getKey()) == -1)) {
                    Node tmp = heap[childIndex];
                    heap[childIndex] = heap[i];
                    heap[i] = tmp;
                    i = childIndex;
                    childIndex = findSmallerChild(i);
                }
            }

            private int findSmallerChild(int indexOfParent) {
                if ((2 * indexOfParent + 2) < size) {
                    //two children
//            if (heap[2 * indexOfParent + 1].getKey().compareTo(heap[2 * indexOfParent + 2].getKey()) == 1) {
                    if (myComparator.compare(heap[2 * indexOfParent + 1].getKey(), heap[2 * indexOfParent + 2].getKey()) == -1) {
//                left child is smaller
                        return 2 * indexOfParent + 1;
                    } else {
                        return 2 * indexOfParent + 2;
                    }
                } else if ((2 * indexOfParent + 2) == size) {
                    //node has one child
                    return 2 * indexOfParent + 1;
                } else {
                    return 0; // node is a leaf
                }
            }

            @Override
            public void putAll(Map m) {

            }

            @Override
            public void clear() {
                heap = new Node[DEFAULT_SIZE_OF_ARRAY];
                size = 0;
            }

            @Override
            public Set keySet() {
                return null;
            }

            @Override
            public Collection values() {
                return null;
            }

            @Override
            public Set<Entry> entrySet() {
                return null;
            }

            public V getMin() {
                if (size == 0) {
                    throw new NullPointerException("EMPTY HEAP!");
                } else {
                    //returns root (i.e. minimum)
                    return heap[ROOT_INDEX].getValue();
                }
            }

            public V getLast() {
                return heap[size - 1].getValue();
            }

            public V removeMin() {
                //remove min
                if (size == 0) {
                    throw new NullPointerException("EMPTY HEAP");
                } else {
                    V valueToReturn = heap[ROOT_INDEX].getValue();
                    heap[ROOT_INDEX] = heap[size - 1];
                    heap[size - 1] = null;
                    size--;
                    downHeap(ROOT_INDEX);

                    return valueToReturn;
                }
            }


            private class Node<K extends Comparable, V> {
                private K key;
                private V value;

                public Node(K key, V value) {
                    this.key = key;
                    this.value = value;
                }

                public K getKey() {
                    return key;
                }

                public void setKey(K key) {
                    this.key = key;
                }

                public V getValue() {
                    return value;
                }

                public void setValue(V value) {
                    this.value = value;
                }
            }
        }

    }
}
