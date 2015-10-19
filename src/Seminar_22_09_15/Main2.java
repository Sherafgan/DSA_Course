package Seminar_22_09_15;

import Practice.SortingAlgorithms.MyCountingSort;

public class Main2 {
    public static void main(String[] args) {
        int[] arrayToSort = new int[]{5, 1, 4, 6, 3, 7, 9, 2, 94, 25, 18, -1};

        MyCountingSort myCountingSort = new MyCountingSort();
        int[] sortedArray = myCountingSort.countingSort(arrayToSort);

        printArrayInt(sortedArray);
    }

    private static void printArrayInt(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
}
