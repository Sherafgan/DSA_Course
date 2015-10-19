package Assignment2.ProblemA;

import java.io.*;
import java.util.Comparator;

/**
 * @author Sherafgan Kandov
 * @email s.kandov@innopolis.ru
 * @date 29th September, 2015
 * In accordance with the academic honor, I (Sherafgan Kandov) certify that
 * the answers here are my own work without copying of others and
 * solutions from Internet or other sources."
 */

public class ProblemA {

    private static final int NOT_FOUND = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader inputFile = new BufferedReader(new FileReader("sort.in"));
        PrintWriter outputFile = new PrintWriter(new BufferedWriter(new FileWriter("sort.out")));

        String line1 = inputFile.readLine().trim();
        String line2 = inputFile.readLine().trim(); //targetValue
        String[] parsedLine1 = line1.split("\\s");
        String[] targetArray = new String[parsedLine1.length - 1]; //targetArray
        for (int i = 0; i < parsedLine1.length - 1; i++) {
            targetArray[i] = parsedLine1[i + 1];
        }

        if (parsedLine1[0].equals("String")) {
            MyStringComparator myStringComparator = new MyStringComparator();
            insertionSortString(targetArray);

            String result = String.valueOf(binarySearchString(targetArray, line2, myStringComparator));
            outputFile.write(result);
            outputFile.close();
        } else if (parsedLine1[0].equals("Float")) {
            Comparable[] nums = new Comparable[targetArray.length];
            for (int i = 0; i < targetArray.length; i++) {
                nums[i] = Float.parseFloat(targetArray[i]);
            }
            Comparable targetNum = Float.parseFloat(line2);

            insertionSort(nums);

            String result = String.valueOf(binarySearch(nums, targetNum));
            outputFile.write(result);
            outputFile.close();
        } else if (parsedLine1[0].equals("Integer")) {
            Comparable[] nums = new Comparable[targetArray.length];
            for (int i = 0; i < targetArray.length; i++) {
                nums[i] = Integer.parseInt(targetArray[i]);
            }
            Comparable targetNum = Integer.parseInt(line2);

            insertionSort(nums);

            String result = String.valueOf(binarySearch(nums, targetNum));
            outputFile.write(result);
            outputFile.close();
        }
    }

    public static void insertionSortString(String[] arrayToSort) {
        MyStringComparator myStringComparator = new MyStringComparator();
        String tmp;
        for (int i = 1; i < arrayToSort.length; i++) {
            for (int j = 0; j < i; j++) {
                if (myStringComparator.compare(arrayToSort[j], arrayToSort[i]) == 1) {
                    tmp = arrayToSort[j];
                    arrayToSort[j] = arrayToSort[i];
                    arrayToSort[i] = tmp;
                }
            }
        }
    }

    public static void insertionSort(Comparable[] arrayToSort) {
        Comparable tmp;
        for (int i = 1; i < arrayToSort.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arrayToSort[j].compareTo(arrayToSort[i]) == 1) {
                    tmp = arrayToSort[j];
                    arrayToSort[j] = arrayToSort[i];
                    arrayToSort[i] = tmp;
                }
            }
        }
    }

    /**
     * Returns -1 if the array does not contain the element, otherwise
     * calls the another recursive method to get the index of target value.
     */
    public static int binarySearch(Comparable[] a, Comparable value) {
        if (a[0].compareTo(value) == 1 || value.compareTo(a[a.length - 1]) == 1) {
            return NOT_FOUND;
        } else {
            return binarySearch(a, value, 0, a.length - 1);
        }
    }

    /**
     * Calculates middle index of array. Returns 'midIndex' if the middle number in
     * array is equal to target value, otherwise checks if it is greater or less than
     * target value and calls itself changing the 'highIndex' to 'midIndex - 1' or
     * 'lowIndex' to 'midIndex + 1' respectively.
     */
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

    public static int binarySearchString(String[] arrayOfStrings, String targetString, MyStringComparator myStringComparator) {
        if (myStringComparator.compare(arrayOfStrings[0], targetString) == 1 || myStringComparator.compare(targetString, arrayOfStrings[arrayOfStrings.length - 1]) == 1) {
            return NOT_FOUND;
        } else {
            return binarySearchString(arrayOfStrings, targetString, 0, arrayOfStrings.length - 1, myStringComparator);
        }
    }

    private static int binarySearchString(String[] arrayOfStrings, String targetString, int lowIndex, int highIndex, MyStringComparator myStringComparator) {
        if (lowIndex > highIndex) {
            return NOT_FOUND;
        }

        int midIndex = (lowIndex + highIndex) / 2;
        if (arrayOfStrings[midIndex].equals(targetString)) {
            return midIndex;
        } else if (myStringComparator.compare(arrayOfStrings[midIndex], targetString) == 1) {
            return binarySearchString(arrayOfStrings, targetString, lowIndex, midIndex - 1, myStringComparator);
        } else if (myStringComparator.compare(targetString, arrayOfStrings[midIndex]) == 1) {
            return binarySearchString(arrayOfStrings, targetString, midIndex + 1, highIndex, myStringComparator);
        } else {
            return -1;
        }
    }

    static class MyStringComparator implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {
            String s1 = (String) o1;
            String s2 = (String) o2;

            int n;
            if (s1.length() > s2.length()) {
                n = s2.length();
            } else {
                n = s1.length();
            }

            for (int i = 0; i < n; i++) {
                int c1;
                if (s1.charAt(i) > 90) {
                    c1 = s1.charAt(i) - 32;
                } else {
                    c1 = s1.charAt(i);
                }

                int c2;
                if (s2.charAt(i) > 90) {
                    c2 = s2.charAt(i) - 32;
                } else {
                    c2 = s2.charAt(i);
                }

                if (c1 > c2) {
                    return 1;
                } else if (c1 < c2) {
                    return -1;
                }
            }

            return 0;
        }
    }
}
