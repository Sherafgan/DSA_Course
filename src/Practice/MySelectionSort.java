package Practice;

/**
 * @author Sherafgan Kandov
 *         25.09.2015
 */
public class MySelectionSort {
    public void selectionSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n; i++) {
            int min = array[i];
            int minIndex = i;
            for (int j = i; j < n; j++) {
                if (min > array[j]) {
                    min = array[j];
                    minIndex = j;
                }
            }
            if (array[i] > array[minIndex]) {
                int tmp = array[minIndex];
                array[minIndex] = array[i];
                array[i] = tmp;
            }
        }
    }

    public void selectionSortWithIntegerComparator(int[] array, MyIntegerComparator myInteger) {
        int n = array.length;
        for (int i = 0; i < n; i++) {
            int min = array[i];
            int minIndex = i;
            for (int j = i; j < n; j++) {
                if (myInteger.compare(min, array[j]) == 1) {
                    min = array[j];
                    minIndex = j;
                }
            }
            if (myInteger.compare(array[i], array[minIndex]) == 1) {
                int tmp = array[minIndex];
                array[minIndex] = array[i];
                array[i] = tmp;
            }
        }
    }

    public void selectionSortWihStringComparatorAsc(String[] strings, MyStringComparator myStringComparator) {
        int n = strings.length;
        for (int i = 0; i < n; i++) {
            String min = strings[i];
            int minIndex = i;
            for (int j = i; j < n; j++) {
                if (myStringComparator.compare(min, strings[j]) == 1) {
                    min = strings[j];
                    minIndex = j;
                }
            }
            if (myStringComparator.compare(strings[i], strings[minIndex]) == 1) {
                String tmp = strings[minIndex];
                strings[minIndex] = strings[i];
                strings[i] = tmp;
            }
        }
    }

    public void selectionSortWihStringComparatorDesc(String[] strings, MyStringComparator myStringComparator) {
        int n = strings.length;
        for (int i = 0; i < n; i++) {
            String min = strings[i];
            int minIndex = i;
            for (int j = i; j < n; j++) {
                if (myStringComparator.compare(min, strings[j]) == -1) {
                    min = strings[j];
                    minIndex = j;
                }
            }
            if (myStringComparator.compare(strings[i], strings[minIndex]) == -1) {
                String tmp = strings[minIndex];
                strings[minIndex] = strings[i];
                strings[i] = tmp;
            }
        }
    }
}
