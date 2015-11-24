package HW12.tests;

import HW12.MyPriorityQueue;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author Sherafgan Kandov
 *         17.11.15
 */
public class MyPriorityQueueTests extends TestCase {
    @Test
    public void test() {
        MyPriorityQueue<String> myPriorityQueue = new MyPriorityQueue<>();

        myPriorityQueue.add("Max");
        myPriorityQueue.add("John");
        myPriorityQueue.add("Bruce");

        assertFalse(myPriorityQueue.isEmpty());

        assertSame(myPriorityQueue.peek(), "Bruce");
        assertSame(myPriorityQueue.remove(), "Bruce");
        assertSame(myPriorityQueue.poll(), "John");
        assertSame(myPriorityQueue.peek(), "Max");
        assertTrue(myPriorityQueue.size() == 1);
        myPriorityQueue.clear();
        assertTrue(myPriorityQueue.size() == 0);
    }
}
