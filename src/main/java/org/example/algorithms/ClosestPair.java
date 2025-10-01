package org.example.algorithms;

import java.awt.Point;
import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {
    public static double closest(Point[] points) {
        Point[] px = points.clone();
        Point[] py = points.clone();
        Arrays.sort(px, Comparator.comparingInt(p -> p.x));
        Arrays.sort(py, Comparator.comparingInt(p -> p.y));
        return closest(px, py);
    }

    private static double closest(Point[] px, Point[] py) {
        int n = px.length;
        if (n <= 3) return bruteForce(px);

        int mid = n / 2;
        Point midPoint = px[mid];

        Point[] pyl = Arrays.stream(py).filter(p -> p.x <= midPoint.x).toArray(Point[]::new);
        Point[] pyr = Arrays.stream(py).filter(p -> p.x > midPoint.x).toArray(Point[]::new);

        double dl = closest(Arrays.copyOfRange(px, 0, mid), pyl);
        double dr = closest(Arrays.copyOfRange(px, mid, n), pyr);
        double d = Math.min(dl, dr);

        Point[] strip = Arrays.stream(py).filter(p -> Math.abs(p.x - midPoint.x) < d).toArray(Point[]::new);
        return Math.min(d, stripClosest(strip, d));
    }

    private static double stripClosest(Point[] strip, double d) {
        double min = d;
        for (int i = 0; i < strip.length; i++)
            for (int j = i + 1; j < strip.length && (strip[j].y - strip[i].y) < min; j++)
                min = Math.min(min, strip[i].distance(strip[j]));
        return min;
    }

    private static double bruteForce(Point[] points) {
        double min = Double.MAX_VALUE;
        for (int i = 0; i < points.length; i++)
            for (int j = i + 1; j < points.length; j++)
                min = Math.min(min, points[i].distance(points[j]));
        return min;
    }
}
