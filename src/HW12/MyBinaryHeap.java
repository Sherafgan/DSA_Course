package HW12;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @author Sherafgan Kandov
 *         17.11.15
 */
public class MyBinaryHeap<K extends Comparable, V> implements Map {
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
