package Practice.SortingAlgorithms;


/**
 * @author Sherafgan Kandov
 *         24.09.2015
 */
public class MyBubbleSort {

    public void bubbleSort(Comparable[] a) {
        int n = a.length;
        Comparable tmp;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (a[j].compareTo(a[j + 1]) == 1) {
                    tmp = a[j + 1];
                    a[j + 1] = a[j];
                    a[j] = tmp;
                }
            }
        }
    }

    /*
    public void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = tmp;
                }
            }
        }
    }

    public void bubbleSortWithIntegerComparator(int[] array, MyIntegerComparator myIntegerComparator) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (myIntegerComparator.compare(array[j], array[j + 1]) == 1) {
                    int tmp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = tmp;
                }
            }
        }
    }

    public void bubbleSortWithDateComparator(Date[] array, MyDateComparator myDateComparator) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (myDateComparator.compare(array[j], array[j + 1]) == 1) {
                    Date tmp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = tmp;
                }
            }
        }
    }

    public void bubbleSortWithStringComparatorAsc(String[] strings, MyStringComparator myStringComparator) {
        int n = strings.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (myStringComparator.compare(strings[j], strings[j + 1]) == 1) {
                    String tmp = strings[j + 1];
                    strings[j + 1] = strings[j];
                    strings[j] = tmp;
                }
            }
        }
    }

    public void bubbleSortWithStringComparatorDesc(String[] strings, MyStringComparator myStringComparator) {
        int n = strings.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (myStringComparator.compare(strings[j], strings[j + 1]) == -1) {
                    String tmp = strings[j + 1];
                    strings[j + 1] = strings[j];
                    strings[j] = tmp;
                }
            }
        }
    }
    */
}
