package HW12;

/**
 * @author Sherafgan Kandov
 *         17.11.15
 */
public class MyPriorityQueue<V> {
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
