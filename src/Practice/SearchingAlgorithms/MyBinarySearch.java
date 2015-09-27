package Practice.SearchingAlgorithms;

import java.util.Stack;

/**
 * @author Sherafgan Kandov
 *         20.09.2015
 */
public class MyBinarySearch {

    private static final int NOT_FOUND = -1;

    public int binarySearch(Comparable[] a, Comparable value) {
        if (a[0].compareTo(value) == 1 || value.compareTo(a[a.length - 1]) == 1) {
            return NOT_FOUND;
        } else {
            return binarySearch(a, value, 0, a.length - 1);
        }
    }

    private static int binarySearch(Comparable[] a, Comparable value, int lowIndex, int highIndex) {
        if (lowIndex > highIndex) {
            return NOT_FOUND;
        }

        int midIndex = (lowIndex + highIndex) / 2;
        if (a[midIndex].compareTo(value) == 0) {
            return midIndex;
        } else if (a[midIndex].compareTo(value) == 1) {
            return binarySearch(a, value, lowIndex, midIndex - 1);
        } else if (value.compareTo(a[midIndex]) == 1) {
            return binarySearch(a, value, midIndex + 1, highIndex);
        } else {
            return -1;
        }
    }

    /**
     * Returns -1 if the array does not contain the element, otherwise
     * calls the another recursive method to get the index of target value.
     */
    public int binarySearchInt(int[] array, int value) {
        if (value < array[0] || value > array[array.length - 1]) {
            return NOT_FOUND;
        } else {
            return binarySearchInt(array, value, 0, array.length - 1);
        }
    }

    /**
     * Calculates middle index of array. Returns 'midIndex' if the middle number in
     * array is equal to target value, otherwise checks if it is greater or less than
     * target value and calls itself changing the 'highIndex' to 'midIndex - 1' or
     * 'lowIndex' to 'midIndex + 1' respectively.
     */
    private static int binarySearchInt(int[] array, int value, int lowIndex, int highIndex) {
        if (lowIndex > highIndex) {
            return NOT_FOUND;
        }

        int midIndex = (lowIndex + highIndex) / 2;
        if (array[midIndex] == value) {
            return midIndex;
        } else if (array[midIndex] > value) {
            return binarySearchInt(array, value, lowIndex, midIndex - 1);
        } else if (array[midIndex] < value) {
            return binarySearchInt(array, value, midIndex + 1, highIndex);
        } else {
            return -1;
        }
    }

    /**
     * Loop + Stack based Binary Search.
     * Returns -1 if the array does not contain the element, otherwise
     * calls the 'helper' method to get the index of target value.
     */
    public int binarySearchLS(int[] array, int value) {
        if (value < array[0] || value > array[array.length - 1]) {
            return NOT_FOUND;
        }

        int midIndex, lowIndex = 0, highIndex = array[array.length - 1];

        Stack<Integer> stack = new Stack<>();
        stack.push(highIndex);
        stack.push(lowIndex);
        while (!stack.isEmpty()) {

            lowIndex = stack.pop();
            highIndex = stack.pop();

            if (lowIndex > highIndex) {
                return NOT_FOUND;
            }

            midIndex = (lowIndex + highIndex) / 2;
            if (array[midIndex] == value) {
                return midIndex;
            } else if (array[midIndex] > value) {
                stack.push(midIndex - 1);
                stack.push(lowIndex);
            } else if (array[midIndex] < value) {
                stack.push(highIndex);
                stack.push(midIndex + 1);
            } else {
                return -1;
            }
        }
        return NOT_FOUND;
    }

    /**
     * Loop based Binary Search.
     */
    public int binarySearchL(int[] array, int value) {
        int lowIndex = 0;
        int highIndex = array.length - 1;
        while (lowIndex <= highIndex) {
            int midIndex = (lowIndex + highIndex) / 2;

            if (array[midIndex] == value) {
                return midIndex;
            } else if (array[midIndex] < value) {
                lowIndex = midIndex + 1;
            } else {
                highIndex = midIndex - 1;
            }
        }
        return -1;
    }
}
