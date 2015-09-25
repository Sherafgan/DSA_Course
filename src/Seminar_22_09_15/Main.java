package Seminar_22_09_15;

import Practice.*;

/**
 * @author Sherafgan Kandov
 *         24.09.2015
 */
public class Main {
    public static void main(String[] args) {
//        MyBubbleSort myBubbleSort = new MyBubbleSort();

//        int[] array = new int[]{5, 1, 4, 6, 3, 7, 9, 2, -1, 2, 25, -2};

//        myBubbleSort.bubbleSort(array);
//        printArray(array);

//        MyIntegerComparator comp = new MyIntegerComparator();
//        myBubbleSort.bubbleSortWithIntegerComparator(array, comp);
//        printArray(array);

//        Date[] arrayOfDates = new Date[]{new Date(12, 12, 2015), new Date(1, 5, 2013), new Date(3, 7, 2001), new Date(31, 9, 2003), new Date(11, 12, 2015)};
//        MyDateComparator myDateComparator = new MyDateComparator();
//        myBubbleSort.bubbleSortWithDateComparator(arrayOfDates, myDateComparator);
//        printArrayOfDates(arrayOfDates);

        String[] strings = new String[]{"Movie", "Lesson", "Business", "Work", "You", "Conglomerate", "vowel"};

        MyStringComparator myStringComparator = new MyStringComparator();
//        myBubbleSort.bubbleSortWithStringComparatorAsc(strings, myStringComparator);
//        myBubbleSort.bubbleSortWithStringComparatorDesc(strings, myStringComparator);
//        printStrings(strings);

//        MySelectionSort mySelectionSort = new MySelectionSort();
//        mySelectionSort.selectionSort(array);
//        MyIntegerComparator myIntegerComparator = new MyIntegerComparator();
//        mySelectionSort.selectionSortWithIntegerComparator(array, myIntegerComparator);
//        printArray(array);

//        mySelectionSort.selectionSortWihStringComparatorAsc(strings, myStringComparator);
//        mySelectionSort.selectionSortWihStringComparatorDesc(strings, myStringComparator);
//        printStrings(strings);


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
