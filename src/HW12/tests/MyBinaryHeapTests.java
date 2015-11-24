package HW12.tests;

import HW12.MyBinaryHeap;
import junit.framework.TestCase;
import org.junit.Test;
import org.omg.CORBA.StringHolder;

/**
 * @author Sherafgan Kandov
 *         17.11.15
 */
public class MyBinaryHeapTests extends TestCase {
    @Test
    public void test() {
        MyBinaryHeap<Integer, StringHolder> myBinaryHeap = new MyBinaryHeap<>();

        assertTrue(myBinaryHeap.isEmpty());

        assertTrue(myBinaryHeap.size() == 0);

        myBinaryHeap.put(4, "C");

        assertFalse(myBinaryHeap.isEmpty());

        assertTrue(myBinaryHeap.size() == 1);

        assertSame(myBinaryHeap.getMin(), "C");

        myBinaryHeap.put(5, "A");
        myBinaryHeap.put(6, "Z");
        myBinaryHeap.put(15, "K");
        myBinaryHeap.put(9, "F");
        myBinaryHeap.put(7, "Q");
        myBinaryHeap.put(20, "B");

        assertSame(myBinaryHeap.getLast(), "B");

        myBinaryHeap.put(16, "X");
        myBinaryHeap.put(25, "J");

        assertSame(myBinaryHeap.getLast(), "J");

        myBinaryHeap.put(14, "E");
        myBinaryHeap.put(12, "H");
        myBinaryHeap.put(11, "S");
        myBinaryHeap.put(13, "W");

        assertSame(myBinaryHeap.getLast(), "W");

        assertTrue(myBinaryHeap.size() == 13);

        //removing node with key '4'
        myBinaryHeap.removeMin();

        assertTrue(myBinaryHeap.size() == 12);
        assertSame(myBinaryHeap.getMin(), "W");
        assertSame(myBinaryHeap.getLast(), "S");

        //removing node with key '13'
        myBinaryHeap.removeMin();

        assertTrue(myBinaryHeap.size() == 11);
        assertSame(myBinaryHeap.getMin(), "S");
        assertSame(myBinaryHeap.getLast(), "H");

        assertTrue(myBinaryHeap.containsKey(7));
        assertFalse(myBinaryHeap.containsKey(13));

        assertTrue(myBinaryHeap.containsValue("K"));
        assertFalse(myBinaryHeap.containsValue("C"));

        //ending test
        myBinaryHeap.clear();
        assertTrue(myBinaryHeap.isEmpty());

        Integer[] keys = new Integer[]{4, 5, 6, 7, 9, 11, 12, 13, 14, 15, 16, 20, 25};
        String[] values = new String[]{"C", "A,", "Z", "X", "F", "S", "H", "W", "E", "K", "X", "B", "J"};

        myBinaryHeap = new MyBinaryHeap(keys, values);

        assertSame(myBinaryHeap.getMin(), "C");
        assertTrue(myBinaryHeap.size() == 13);

        assertTrue(myBinaryHeap.containsValue("Z"));
        assertTrue(myBinaryHeap.containsKey(25));
    }
}
