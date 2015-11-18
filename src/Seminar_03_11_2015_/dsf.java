package Seminar_03_11_2015_;

import java.util.PriorityQueue;

/**
 * @author Sherafgan Kandov
 *         17.11.15
 */
public class dsf {
    public static void main(String[] args) {
        PriorityQueue<String> priorityQueue = new PriorityQueue<>();
        priorityQueue.add("Max");
        priorityQueue.add("Jonh");
        priorityQueue.add("Bruce");

        System.out.println(priorityQueue.peek());
        priorityQueue.remove();
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.peek());
    }
}
