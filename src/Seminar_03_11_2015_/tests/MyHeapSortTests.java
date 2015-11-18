package Seminar_03_11_2015_.tests;

import Seminar_03_11_2015_.MyHeapSort;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author Sherafgan Kandov
 *         18.11.15
 */
public class MyHeapSortTests extends TestCase {
    @Test
    public void test() {
        MyHeapSort myHeapSort = new MyHeapSort();

        Comparable[] array = new Integer[]{123, 4, 5, 4, 42, 1, 3, 23, 12, 241, -1};
        array = myHeapSort.heapsort(array);

        assertTrue(array.length == 11);
        assertTrue((Integer) array[10] == 241);
        assertTrue((Integer) array[0] == -1);
        assertTrue((Integer) array[4] == 4);
        assertTrue((Integer) array[5] == 5);
        assertTrue((Integer) array[6] == 12);
    }
}
