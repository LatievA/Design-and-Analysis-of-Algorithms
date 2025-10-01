package org.example.algorithms;

import org.example.algorithms.utils.Metrics;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MergeSortTest {
    @Test
    void testRandomArray() {
        int n = 1000;
        int[] a = new Random().ints(n, 0, 10000).toArray();
        int[] copy = a.clone();

        Metrics metrics = new Metrics();
        new MergeSort(metrics).sort(a);

        Arrays.sort(copy);
        assertArrayEquals(copy, a);
        System.out.println("Recursion depth: " + metrics.recursionDepth);
    }
}
