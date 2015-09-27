package Seminar_22_09_15;

import Practice.DataStructures.Date;
import Practice.SortingAlgorithms.QuickSort;

public class Main {

    public static void main(String[] args) {
//        MyBubbleSort myBubbleSort = new MyBubbleSort();

        int[] array = new int[]{5, 1, 4, 6, 3, 7, 9, 2, -1, 2, 25, -2};
        Comparable[] num = {5, 1, 4, 6, 3, 7, 9, 2, -1, 2, 25, -2};
        Comparable[] strings = {"Movie", "Lesson", "Business", "Work", "You", "Conglomerate", "vowel"};
        String[] arrayOfStrings = new String[]{"Movie", "Lesson", "Business", "Work", "You", "Conglomerate", "vowel"};
        Date[] arrayOfDates = new Date[]{new Date(12, 12, 2015), new Date(1, 5, 2013), new Date(3, 7, 2001), new Date(31, 9, 2003), new Date(11, 12, 2015)};
        Comparable[] dates = {new Date(12, 12, 2015), new Date(1, 5, 2013), new Date(3, 7, 2001), new Date(31, 9, 2003), new Date(11, 12, 2015)};

        QuickSort quickSort = new QuickSort();
        System.out.println("================================================");
        for (int i = 100; i <= 100000000; i *= 10) {
            long[] arrayOfMixedNumbers = creteMixedArrayOfSize(i);
            long timeInMilliSec = System.nanoTime();
            quickSort.quickSort(array, 0, array.length - 1);
            timeInMilliSec = (System.nanoTime() - timeInMilliSec);
            System.out.println("Size: " + i + " Time: " + timeInMilliSec + " ns.");
            System.out.println("================================================");
        }
    }

    private static long[] creteMixedArrayOfSize(int n) {
        long[] array = new long[n];
        for (int i = 0; i < n; i++) {
            array[i] = (int) (Math.random() * 1000000);
        }
        return array;
    }

    private static void printIntArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void printArray(Comparable[] a) {
        for (Comparable i : a) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
