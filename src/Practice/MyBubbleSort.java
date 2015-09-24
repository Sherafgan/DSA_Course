package Practice;


/**
 * @author Sherafgan Kandov
 *         24.09.2015
 */
public class MyBubbleSort {
    public void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 2; i++) {
            for (int j = 0; j < n - 2; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = tmp;
                }
            }
        }
    }

    public void bubbleSortWithIntegerComparator(int[] array, MyNumComparator myNumComparator) {
        int n = array.length;
        for (int i = 0; i < n - 2; i++) {
            for (int j = 0; j < n - 2; j++) {
                if (myNumComparator.compare(array[j], array[j + 1]) == 1) {
                    int tmp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = tmp;
                }
            }
        }
    }

    public void bubbleSortWithDateComparator(Date[] array, MyDateComparator myDateComparator) {
        int n = array.length;
        for (int i = 0; i < n - 2; i++) {
            for (int j = 0; j < n - 2; j++) {
                if (myDateComparator.compare(array[j], array[j + 1]) == 1) {
                    Date tmp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = tmp;
                }
            }
        }
    }

    public void bubbleSortWithStringComparator(String[] strings, MyStringComparator myStringComparator) {
        int n = strings.length;
        for (int i = 0; i < n - 2; i++) {
            for (int j = 0; j < n - 2; j++) {
                if (myStringComparator.compare(strings[j], strings[j + 1]) == 1) {
                    String tmp = strings[j + 1];
                    strings[j + 1] = strings[j];
                    strings[j] = tmp;
                }
            }
        }
    }
}
