package Seminar_22_09_15;

import Practice.*;

/**
 * @author Sherafgan Kandov
 *         24.09.2015
 */
public class Main {
    public static void main(String[] args) {
        MyBubbleSort myBubbleSort = new MyBubbleSort();
        int[] array = new int[]{5, 1, 4, 6, 3, 7, 9, 2, 65};
        myBubbleSort.bubbleSort(array);

//        MyNumComparator comp = new MyNumComparator();
//        myBubbleSort.bubbleSortWithIntegerComparator(array, comp);

//        Date[] arrayOfDates = new Date[]{new Date(12, 12, 2015), new Date(1, 5, 2013), new Date(3, 7, 2001), new Date(31, 9, 2003)};
//        MyDateComparator myDateComparator = new MyDateComparator();
//        myBubbleSort.bubbleSortWithDateComparator(arrayOfDates, myDateComparator);
//        printArrayOfDates(arrayOfDates);

        String[] strings = new String[]{"Movie", "Lesson", "Business", "Work", "You", "Conglomerate"};
        MyStringComparator myStringComparator = new MyStringComparator();
        myBubbleSort.bubbleSortWithStringComparator(strings, myStringComparator);
        printStrings(strings);
    }

    private static void printStrings(String[] strings) {
        for (String s : strings) {
            System.out.print(s + " ");
        }
        System.out.println();
    }

    private static void printArrayOfDates(Date[] arrayOfDates) {
        for (Date d : arrayOfDates) {
            System.out.print(d + "  ");
        }
        System.out.println();
    }

    public static void printArray(int[] array) {
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
