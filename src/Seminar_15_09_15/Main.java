package Seminar_15_09_15;

import Practice.SearchingAlgorithms.MyBinarySearch;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        check(3);
    }

    private static void check(int index) {
        int[] array = new int[]{1, 2, 4, 5, 6, 7, 8, 9, 10, 11, 13};
        MyBinarySearch myBinarySearch = new MyBinarySearch();

        System.out.println(myBinarySearch.binarySearchInt(array, index));
        System.out.println(myBinarySearch.binarySearchLS(array, index));
        System.out.println(myBinarySearch.binarySearchL(array, index));
    }
}
