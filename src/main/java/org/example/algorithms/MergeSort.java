package org.example.algorithms;

import org.example.algorithms.utils.Metrics;

public class MergeSort {
    private int[] aux; //reusable buffer
    private final Metrics metrics;

    public MergeSort(Metrics metrics) {
        this.metrics = metrics;
    }

    public void sort(int[] a) {
        aux = new int[a.length];
        sort(a, 0, a.length - 1);
    }

    private void sort(int[] a, int lo, int hi) {
        metrics.enterRecursion();
        if (hi - lo + 1 <= 16) { // small-n cutoff
            insertionSort(a, lo, hi);
            metrics.exitRecursion();
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
        metrics.exitRecursion();
    }

    private void merge(int[] a, int lo, int mid, int hi) {
        System.arraycopy(a, lo, aux, lo, hi - lo + 1);
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            metrics.comparisons++;
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (aux[i] <= aux[j]) a[k] = aux[i++];
            else a[k] = aux[j++];
        }
    }

    private void insertionSort(int[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            int key = a[i], j = i - 1;
            while (j >= lo) {
                metrics.comparisons++;
                if (a[j] > key) {
                    a[j + 1] = a[j--];
                    metrics.swaps++;
                } else break;
            }
            a[j + 1] = key;
        }
    }
}
