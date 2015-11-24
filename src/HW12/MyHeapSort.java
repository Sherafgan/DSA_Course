package HW12;

/**
 * @author Sherafgan Kandov
 *         18.11.15
 */
public class MyHeapSort {
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
}
