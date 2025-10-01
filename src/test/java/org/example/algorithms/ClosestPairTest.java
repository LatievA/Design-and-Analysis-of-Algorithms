package org.example.algorithms;

import org.junit.jupiter.api.Test;

import java.awt.Point;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClosestPairTest {
    private static double bruteForce(Point[] points) {
        double min = Double.MAX_VALUE;
        for (int i = 0; i < points.length; i++)
            for (int j = i + 1; j < points.length; j++)
                min = Math.min(min, points[i].distance(points[j]));
        return min;
    }

    @Test
    void testRandomPointsSmall() {
        Random rand = new Random();
        Point[] points = new Point[10];
        for (int i = 0; i < points.length; i++)
            points[i] = new Point(rand.nextInt(100), rand.nextInt(100));

        double expected = bruteForce(points);
        double actual = ClosestPair.closest(points);

        assertEquals(expected, actual, 1e-6);
    }

    @Test
    void testRandomPointsLarge() {
        Random rand = new Random();
        int n = 1000;
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++)
            points[i] = new Point(rand.nextInt(10000), rand.nextInt(10000));

        double brute = bruteForce(java.util.Arrays.copyOf(points, 20)); // small sample for quick check
        double actual = ClosestPair.closest(points); // just check runs without exception
        System.out.println("Closest distance computed for large n: " + actual);
    }
}
