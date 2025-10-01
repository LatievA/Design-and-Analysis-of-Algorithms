package org.example.algorithms.utils;

public class Metrics {
    public long comparisons = 0;
    public long swaps = 0;
    public int recursionDepth = 0;
    private int currentDepth = 0;

    public void enterRecursion() {
        currentDepth++;
        if (currentDepth > recursionDepth) recursionDepth = currentDepth;
    }

    public void exitRecursion() {
        currentDepth--;
    }

    public void reset() {
        comparisons = 0;
        swaps = 0;
        recursionDepth = 0;
        currentDepth = 0;
    }
}
