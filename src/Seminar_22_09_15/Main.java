package Seminar_22_09_15;

import Practice.DataStructures.Date;
import Practice.SortingAlgorithms.MyBubbleSort;

public class Main {
    public static void main(String[] args) {
//        MyBubbleSort myBubbleSort = new MyBubbleSort();

        int[] array = new int[]{5, 1, 4, 6, 3, 7, 9, 2, -1, 2, 25, -2};
        Comparable[] num = {5, 1, 4, 6, 3, 7, 9, 2, -1, 2, 25, -2};
        Comparable[] strings = {"Movie", "Lesson", "Business", "Work", "You", "Conglomerate", "vowel"};
        String[] arrayOfStrings = new String[]{"Movie", "Lesson", "Business", "Work", "You", "Conglomerate", "vowel"};
        Date[] arrayOfDates = new Date[]{new Date(12, 12, 2015), new Date(1, 5, 2013), new Date(3, 7, 2001), new Date(31, 9, 2003), new Date(11, 12, 2015)};
        Comparable[] dates = {new Date(12, 12, 2015), new Date(1, 5, 2013), new Date(3, 7, 2001), new Date(31, 9, 2003), new Date(11, 12, 2015)};

        MyBubbleSort myBubbleSort = new MyBubbleSort();
        myBubbleSort.bubbleSort(strings);
        printArray(strings);
    }

    public static void printArray(Comparable[] a) {
        for (Comparable i : a) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
