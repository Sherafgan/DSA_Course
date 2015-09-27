package Practice.SortingAlgorithms;

/**
 * @author Sherafgan Kandov
 *         25.09.2015
 */
public class MyInsertionSort {
    public void insertionSort(Comparable a[]) {
        int n = a.length;
        Comparable tmp;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (a[j].compareTo(a[i]) == 1) {
                    tmp = a[j];
                    a[j] = a[i];
                    a[i] = tmp;
                }
            }
        }
    }

    /*
    public void insertionSort(int[] array) {
        int n = array.length;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[i]) {
                    int tmp = array[j];
                    array[j] = array[i];
                    array[i] = tmp;
                }
            }
        }
    }

    public void insertionSort2(int[] arr) {
        int i, j, newValue;
        for (i = 1; i < arr.length; i++) {
            newValue = arr[i];
            j = i;
            while (j > 0 && arr[j - 1] > newValue) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = newValue;
        }
    }

    public void insertionSortWithIntegerComparator(int[] array, MyIntegerComparator myIntegerComparator) {
        int n = array.length;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (myIntegerComparator.compare(array[j], array[i]) == 1) {
                    int tmp = array[j];
                    array[j] = array[i];
                    array[i] = tmp;
                }
            }
        }
    }

    public void insertionSortWithStringComparatorAsc(String[] strings, MyStringComparator myStringComparator) {
        int n = strings.length;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (myStringComparator.compare(strings[j], strings[i]) == 1) {
                    String tmp = strings[j];
                    strings[j] = strings[i];
                    strings[i] = tmp;
                }
            }
        }
    }

    public void insertionSortWithStringComparatorDesc(String[] strings, MyStringComparator myStringComparator) {
        int n = strings.length;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (myStringComparator.compare(strings[j], strings[i]) == -1) {
                    String tmp = strings[j];
                    strings[j] = strings[i];
                    strings[i] = tmp;
                }
            }
        }
    }
    */
}
