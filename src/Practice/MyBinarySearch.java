package Practice;

/**
 * @author Sherafgan Kandov
 *         20.09.2015
 */
public class MyBinarySearch {
    /**
     * Returns -1 if the array does not contain the element, otherwise
     * calls the another recursive method to get the index of target value.
     */
    public int binarySearch(int[] array, int value) {
        if (value < array[0] || value > array[array.length - 1]) {
            return -1;
        } else {
            return binarySearch(array, value, 0, array.length - 1);
        }
    }

    /**
     * Calculates middle index of array. Returns 'midIndex' if the middle number in
     * array is equal to target value, otherwise checks if it is greater or less than
     * target value and calls itself changing the 'highIndex' to 'midIndex - 1' or
     * 'lowIndex' to 'midIndex + 1' respectively.
     */
    private static int binarySearch(int[] array, int value, int lowIndex, int highIndex) {
        int midIndex = (lowIndex + highIndex) / 2;
        if (array[midIndex] == value) {
            return midIndex;
        } else if (array[midIndex] > value) {
            return binarySearch(array, value, lowIndex, midIndex - 1);
        } else if (array[midIndex] < value) {
            return binarySearch(array, value, midIndex + 1, highIndex);
        } else {
            return -1;
        }
    }
}
