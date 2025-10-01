package org.example.algorithms.utils;

import java.util.Random;

public class ArrayUtils {
    private static final Random RANDOM = new Random();

    public static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void shuffle(int[] a) {
        for (int i = a.length - 1; i > 0; i--) {
            int j = RANDOM.nextInt(i + 1);
            swap(a, i, j);
        }
    }
}
