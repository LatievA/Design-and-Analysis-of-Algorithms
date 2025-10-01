package org.example.algorithms;

import org.example.algorithms.utils.Metrics;
import org.example.algorithms.utils.ArrayUtils;

public class DeterministicSelect {
    private final Metrics metrics;

    public DeterministicSelect(Metrics metrics) {
        this.metrics = metrics;
    }

    public int select(int[] a, int k) {
        return select(a, 0, a.length - 1, k);
    }

    private int select(int[] a, int lo, int hi, int k) {
        metrics.enterRecursion();
        if (lo == hi) {
            metrics.exitRecursion();
            return a[lo];
        }

        int pivot = medianOfMedians(a, lo, hi);
        int pivotIndex = partition(a, lo, hi, pivot);

        int length = pivotIndex - lo + 1;
        int result;
        if (k == length - 1) result = a[pivotIndex];
        else if (k < length) result = select(a, lo, pivotIndex - 1, k);
        else result = select(a, pivotIndex + 1, hi, k - length);
        metrics.exitRecursion();
        return result;
    }

    private int partition(int[] a, int lo, int hi, int pivot) {
        int pivotIndex = lo;
        for (int i = lo; i <= hi; i++) {
            metrics.comparisons++;
            if (a[i] == pivot) { pivotIndex = i; break; }
        }
        ArrayUtils.swap(a, pivotIndex, hi);
        metrics.swaps++;

        int i = lo;
        for (int j = lo; j < hi; j++) {
            metrics.comparisons++;
            if (a[j] < pivot) ArrayUtils.swap(a, i++, j);
        }
        ArrayUtils.swap(a, i, hi);
        metrics.swaps++;
        return i;
    }

    private int medianOfMedians(int[] a, int lo, int hi) {
        int n = hi - lo + 1;
        if (n <= 5) {
            insertionSort(a, lo, hi);
            return a[lo + n / 2];
        }

        int numMedians = 0;
        for (int i = lo; i <= hi; i += 5) {
            int subHi = Math.min(i + 4, hi);
            insertionSort(a, i, subHi);
            ArrayUtils.swap(a, lo + numMedians, i + (subHi - i) / 2);
            numMedians++;
        }

        return select(a, lo, lo + numMedians - 1, numMedians / 2);
    }

    private void insertionSort(int[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            int key = a[i], j = i - 1;
            while (j >= lo) {
                metrics.comparisons++;
                if (a[j] > key) { a[j + 1] = a[j--]; metrics.swaps++; }
                else break;
            }
            a[j + 1] = key;
        }
    }
}
