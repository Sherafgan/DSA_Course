package Practice.SortingAlgorithms;

import java.util.List;

/**
 * @author Sherafgan Kandov
 *         28.09.2015
 */
public class MergeSort {

    //mergeSort((List<Comparable>) data, 0, data.size() - 1);

    public static void mergeSort(List<Comparable> data, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2; //Same as (l+r)/2, but avoids overflow for large l and h
            mergeSort(data, l, m);
            mergeSort(data, m + 1, r);
            merge(data, l, m, r);
        }
    }

    private static void merge(List<Comparable> data, int l, int m, int r) {
        int i, j, k;
        int n1 = m - l + 1;
        int n2 = r - m;

    /* create temp arrays */
        Comparable[] L = new Comparable[n1];
        Comparable[] R = new Comparable[n2];

    /* Copy data to temp arrays L[] and R[] */
        for (i = 0; i < n1; i++)
            L[i] = data.get(l + i);
        for (j = 0; j < n2; j++)
            R[j] = data.get(m + 1 + j);

    /* Merge the temp arrays back into arr[l..r]*/
        i = 0;
        j = 0;
        k = l;
        while (i < n1 && j < n2) {
            if (R[j].compareTo(L[i]) >= 0) {
                data.set(k, L[i]);
                i++;
            } else {
                data.set(k, R[j]);
                j++;
            }
            k++;
        }

    /* Copy the remaining elements of L[], if there are any */
        while (i < n1) {
            data.set(k, L[i]);
            i++;
            k++;
        }

    /* Copy the remaining elements of R[], if there are any */
        while (j < n2) {
            data.set(k, R[j]);
            j++;
            k++;
        }
    }
}
