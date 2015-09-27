package Practice.SortingAlgorithms;

import Practice.Comparators.MyDateComparator;
import Practice.Comparators.MyIntegerComparator;
import Practice.Comparators.MyStringComparator;
import Practice.DataStructures.Date;

/**
 * @author Sherafgan Kandov
 *         25.09.2015
 *         !!!NOT DONE YET!!!
 */
public class MyCocktailSort {
    public void cocktailSort(int[] array) {
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

    public void cocktailSortWithIntegerComparator(int[] array, MyIntegerComparator myNumComparator) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (myNumComparator.compare(array[j], array[j + 1]) == 1) {
                    int tmp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = tmp;
                }
            }
        }
    }

    public void cocktailSortWithDateComparator(Date[] array, MyDateComparator myDateComparator) {
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

    public void cocktailSortWithStringComparatorAsc(String[] strings, MyStringComparator myStringComparator) {
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

    public void cocktailSortWithStringComparatorDesc(String[] strings, MyStringComparator myStringComparator) {
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
}
