package Seminar_03_11_2015_;

import org.omg.CORBA.StringHolder;

import java.util.*;

/**
 * @author Sherafgan Kandov
 *         17.11.15
 */
public class MyPriorityQueue<V> implements Queue<V> {
    private MyBinaryHeap<? extends Comparable<V>, V> myBinaryHeap;

    public MyPriorityQueue() {
        myBinaryHeap = new MyBinaryHeap();
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public int size() {
        return myBinaryHeap.size();
    }

    @Override
    public boolean isEmpty() {
        return myBinaryHeap.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public boolean offer(Object o) {
        myBinaryHeap.put(o, o);
        return true;
    }

    @Override
    public boolean add(V v) {
        return offer(v);
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends V> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        myBinaryHeap.clear();
    }

    @Override
    public V remove() {
        return myBinaryHeap.removeMin();
    }

    @Override
    public V poll() {
        V valueToReturn = myBinaryHeap.getMin();
        myBinaryHeap.removeMin();
        return valueToReturn;
    }


    @Override
    public V element() {
        return null;
    }

    @Override
    public V peek() {
        return myBinaryHeap.removeMin();
    }
}
