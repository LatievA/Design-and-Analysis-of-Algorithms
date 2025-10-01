package org.example.algorithms;

import org.example.algorithms.utils.Metrics;
import org.example.algorithms.utils.ArrayUtils;

import java.util.Random;

public class QuickSort {
    private final Metrics metrics;
    private static final Random RANDOM = new Random();

    public QuickSort(Metrics metrics) {
        this.metrics = metrics;
    }

    public void sort(int[] a) {
        sort(a, 0, a.length - 1);
    }

    private void sort(int[] a, int lo, int hi) {
        metrics.enterRecursion();
        while (lo < hi) {
            int p = partition(a, lo, hi);
            // recurse on smaller partition
            if (p - lo < hi - p) {
                sort(a, lo, p - 1);
                lo = p + 1; // iterate on larger
            } else {
                sort(a, p + 1, hi);
                hi = p - 1; // iterate on larger
            }
        }
        metrics.exitRecursion();
    }

    private int partition(int[] a, int lo, int hi) {
        //  Pick random pivot index between lo and hi
        int pivotIndex = lo + RANDOM.nextInt(hi - lo + 1);
        ArrayUtils.swap(a, pivotIndex, hi);

        int pivot = a[hi];
        int i = lo;

        for (int j = lo; j < hi; j++) {
            metrics.comparisons++;
            if (a[j] <= pivot) {
                ArrayUtils.swap(a, i++, j);
                metrics.swaps++;
            }
        }

        ArrayUtils.swap(a, i, hi);
        metrics.swaps++;

        return i;
    }
}
