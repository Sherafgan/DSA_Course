package Assignment2.ProblemB; //TODO: delete this line when submitting

import database.MyFrameworkTests;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * TODO: This code should be implemented by a student
 */
public class MyFramework {

    /**
     * TODO implement your own HashMap class!
     *
     * @param <K> key type
     * @param <V> value type
     */
    public static class MyMap<K, V> implements Map<K, V> {

        private Entry<K, V>[] table;   //Array of Entry.
        private int capacity = 1000;  //Initial capacity of HashMap

        static class Entry<K, V> {
            K key;
            V value;
            private Entry<K, V> next;

            public Entry(K key, V value, Entry<K, V> next) {
                this.key = key;
                this.value = value;
                this.next = next;
            }

            public Entry<K, V> getNext() {
                return next;
            }

            public void setNext(Entry<K, V> next) {
                this.next = next;
            }
        }

        @SuppressWarnings("unchecked")
        public MyMap() {
            table = new Entry[capacity];
        }

        @Override
        public void clear() {
            for (int i = 0; i < table.length; i++) {
                table[i] = null;
            }
        }

        @Override
        public boolean containsKey(Object arg0) {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public boolean containsValue(Object arg0) {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public Set<Map.Entry<K, V>> entrySet() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public V get(Object arg0) {
            int hash = hashObject(arg0);
            if (table[hash] == null) {
                return null;
            } else {
                Entry<K, V> temp = table[hash];
                while (temp != null) {
                    if (temp.key.equals(arg0))
                        return temp.value;
                    temp = temp.next; //return value corresponding to arg0.
                }
                return null;   //returns null if arg0 is not found.
            }
        }

        @Override
        public boolean isEmpty() {
            return size() == 0;
        }

        @Override
        public Set<K> keySet() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public V put(K arg0, V arg1) {
            if (arg0 == null) {
                return null;    //does not allow to store null.
            }
            //calculate hash of key.
            int hash = hash(arg0);
            //create new entry.
            Entry<K, V> newEntry = new Entry<K, V>(arg0, arg1, null);

            //if table location does not contain any entry, store entry there.
            if (table[hash] == null) {
                table[hash] = newEntry;
            } else {
                Entry<K, V> previous = null;
                Entry<K, V> current = table[hash];

                while (current != null) { //we have reached last entry of bucket.
                    if (current.key.equals(arg0)) {
                        if (previous == null) {  //node has to be insert on first of bucket.
                            newEntry.next = current.next;
                            table[hash] = newEntry;
                            return null;
                        } else {
                            newEntry.next = current.next;
                            previous.next = newEntry;
                            return previous.value;
                        }
                    }
                    previous = current;
                    current = current.next;
                }
                previous.next = newEntry;
            }
            return null;
        }

        @Override
        public void putAll(Map<? extends K, ? extends V> arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public V remove(Object arg0) {
            int hash = hashObject(arg0);

            if (table[hash] == null) {
                return null;
            } else {
                Entry<K, V> previous = null;
                Entry<K, V> current = table[hash];

                while (current != null) { //we have reached last entry node of bucket.
                    if (current.key.equals(arg0)) {
                        if (previous == null) {  //delete first entry node.
                            table[hash] = table[hash].next;
                            return null;
                        } else {
                            previous.next = current.next;
                            return previous.value;
                        }
                    }
                    previous = current;
                    current = current.next;
                }
                return null;
            }
        }

        @Override
        public int size() {
            int counter = 0;
            for (int i = 0; i < table.length; i++) {
                Entry<K, V> newEntry = table[i];
                while (newEntry != null) {
                    counter++;
                    newEntry = newEntry.getNext();
                }
            }
            return counter;
        }

        @Override
        public Collection<V> values() {
            // TODO Auto-generated method stub
            return null;
        }

        private int hash(K key) {
            return Math.abs(key.hashCode()) % capacity;
        }

        private int hashObject(Object key) {
            if (key != null) {
                K castedKey = (K) key;
                return hash(castedKey);

            } else {
                throw new NullPointerException("Null KEY!!!");
            }
        }
    }

    /**
     * TODO Implement your own binary search algorithms
     *
     * @param data  - list of data to search. Assume data is sorted
     * @param map   - method that converts object from the list to Comparable
     *              object. Most likely this is a method that takes value from the
     *              row. use map.apply(value) to start
     * @param value - value we are searching for
     * @return index of the element in array if present, else -1
     */
    @SuppressWarnings({"rawtypes"})
    public static <T> int binarySearch(List<T> data, Function<Object, Comparable> map, Comparable value) {
        Comparable[] a = convertDataToComparable(data, map);

        return binarySearchR(a, value, 0, data.size() - 1);
    }

    private static <T> Comparable[] convertDataToComparable(List<T> data, Function<Object, Comparable> map) {
        Comparable[] a = new Comparable[data.size()];

        for (int i = 0; i < data.size(); i++) {
            a[i] = map.apply(data.get(i));
        }
        return a;
    }

    /**
     * Is necessary for 'nextGreaterElementIndex' method
     */
    private static int lastLowIndex;

    /**
     * Recursive method.
     * Calculates middle index of array. Returns 'midIndex' if the middle number in
     * array is equal to target value, otherwise checks if it is greater or less than
     * target value and calls itself changing the 'highIndex' to 'midIndex - 1' or
     * 'lowIndex' to 'midIndex + 1' respectively.
     */
    private static int binarySearchR(Comparable[] a, Comparable value, int lowIndex, int highIndex) {
        if (lowIndex > highIndex) {
            return -1;
        }

        int midIndex = (lowIndex + highIndex) / 2;
        if (a[midIndex].compareTo(value) == 0) {
            return midIndex;
        } else if (a[midIndex].compareTo(value) == 1) {
            lastLowIndex = lowIndex;
            return binarySearchR(a, value, lowIndex, midIndex - 1);
        } else if (value.compareTo(a[midIndex]) == 1) {
            lastLowIndex = midIndex + 1;
            return binarySearchR(a, value, midIndex + 1, highIndex);
        } else {
            return -1;
        }
    }

    /**
     * TODO implement your own binary search algorithms that returns either
     * element index OR index of the next greater element
     *
     * @param data  - list of data to search. Assume data is sorted
     * @param map   - method that converts object from the list to Comparable
     *              object. Most likely this is a method that takes value from the
     *              row. use map.apply(value) to start
     * @param value - value we are searching for
     * @return index of the element in array if present, or index of next
     * greater element
     */
    @SuppressWarnings({"rawtypes"})
    public static <T> int binarySearchOrNext(List<T> data, Function<Object, Comparable> map, Comparable value) {
        Comparable[] a = convertDataToComparable(data, map);

        int returnedIndex = binarySearchR(a, value, 0, data.size() - 1);

        if (returnedIndex == -1) {
            return nextGreaterElementIndex(a, lastLowIndex, value);
        } else {
            return returnedIndex;
        }
    }

    /**
     * Is necessary for 'binarySearchOrNext' method
     * Returns next greater element's index
     */
    private static int nextGreaterElementIndex(Comparable[] a, int lowIndex, Comparable value) {
        while (value.compareTo(a[lowIndex]) == 1) {
            lowIndex++;
        }
        return lowIndex;
    }

    /**
     * TODO Implement your own sort that is VERY fast
     *
     * @param data data to sort
     * @param map  - method that converts object from the list to Comparable
     *             object. Most likely this is a method that takes value from the
     *             row. use map.apply(value) to start
     */
    @SuppressWarnings("rawtypes")
    public static <T> void sort(List<T> data, Function<Object, Comparable> map) {
        mergeSort((List<Comparable>) data, 0, data.size() - 1);
    }

    public static void mergeSort(List<Comparable> data, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2; //Same as (l+r)/2, but avoids overflow for large l and h
            mergeSort(data, l, m);
            mergeSort(data, m + 1, r);
            merge(data, l, m, r);
        }
    }

    private static void merge(List<Comparable> data, int l, int m, int r) {
        int i, j, k;
        int n1 = m - l + 1;
        int n2 = r - m;

    /* create temp arrays */
        Comparable[] L = new Comparable[n1];
        Comparable[] R = new Comparable[n2];

    /* Copy data to temp arrays L[] and R[] */
        for (i = 0; i < n1; i++)
            L[i] = data.get(l + i);
        for (j = 0; j < n2; j++)
            R[j] = data.get(m + 1 + j);

    /* Merge the temp arrays back into arr[l..r]*/
        i = 0;
        j = 0;
        k = l;
        while (i < n1 && j < n2) {
            if (R[j].compareTo(L[i]) >= 0) {
                data.set(k, L[i]);
                i++;
            } else {
                data.set(k, R[j]);
                j++;
            }
            k++;
        }

    /* Copy the remaining elements of L[], if there are any */
        while (i < n1) {
            data.set(k, L[i]);
            i++;
            k++;
        }

    /* Copy the remaining elements of R[], if there are any */
        while (j < n2) {
            data.set(k, R[j]);
            j++;
            k++;
        }
    }

    private static int stringHashCode = 0;

    /**
     * TODO Your own hash function with uniform distribution for input strings
     *
     * @param string any string
     * @return hash for the string
     */
//    public static int hash(String string) {
//        stringHashCode++;
//        return stringHashCode;
//    }

    public static int hash(String string) {
        int hash = 0;
        for (int i = 0; i < string.length(); i++) {
            //hash = (hash � 5) - hash + string.charAt(i);
            hash = (hash << 5) | (hash >>> 27);
            hash += (int) string.charAt(i);
        }
        //return Math.abs(hash);
        return Math.abs(hash);
    }

    private static int floatHashCode = 0;

    /**
     * TODO Your own hash function with uniform distribution for floats
     *
     * @param flt floating point number
     * @return hash code
     */
    public static int hash(Float flt) {
        floatHashCode++;
        return floatHashCode;
    }

    private static int integerHashCode = 0;

    /**
     * TODO Your own hash function with uniform distribution for floats
     *
     * @param i floating point number
     * @return hash code
     */
    public static int hash(Integer i) {
        integerHashCode++;
        return integerHashCode;
    }

    /**
     * TODO entry point for assignment #2 task
     *
     * @param args
     */
    public static void main(String[] args) {
        // TODO: here you will implement code at assignment #2
        // you should submit this file excluding package declaration

        //TODO: delete code below at assignment submission
        MyFrameworkTests.testYourBinarySearch();
        MyFrameworkTests.testYourSorting();
        MyFrameworkTests.testYourHashmap();
        MyFrameworkTests.testYourIntHash();
        MyFrameworkTests.testYourFloatHash();
        MyFrameworkTests.testYourStringHash();
    }
}
