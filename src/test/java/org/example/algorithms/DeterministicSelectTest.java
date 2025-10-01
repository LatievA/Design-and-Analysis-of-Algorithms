package org.example.algorithms;

import org.example.algorithms.utils.Metrics;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeterministicSelectTest {
    @Test
    void testRandomArray() {
        int n = 1000;
        int[] a = new Random().ints(n, 0, 10000).toArray();
        Metrics metrics = new Metrics();
        DeterministicSelect select = new DeterministicSelect(metrics);

        for (int k = 0; k < 10; k++) { // test first 10 elements
            int expected = Arrays.stream(a).sorted().toArray()[k];
            int actual = select.select(a.clone(), k);
            assertEquals(expected, actual);
        }
        System.out.println("Recursion depth: " + metrics.recursionDepth);
    }

    @Test
    void testSmallArray() {
        int[] a = {9, 2, 7, 4, 1};
        Metrics metrics = new Metrics();
        DeterministicSelect select = new DeterministicSelect(metrics);

        for (int k = 0; k < a.length; k++) {
            int expected = Arrays.stream(a).sorted().toArray()[k];
            int actual = select.select(a.clone(), k);
            assertEquals(expected, actual);
        }
    }
}
