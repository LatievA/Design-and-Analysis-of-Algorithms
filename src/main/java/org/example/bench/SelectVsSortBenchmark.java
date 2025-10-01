package org.example.bench;

import org.openjdk.jmh.annotations.*;
import java.util.concurrent.TimeUnit;
import java.util.Random;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class SelectVsSortBenchmark {

    @Param({"1000", "10000", "100000"})
    public int n;

    private int[] array;
    private Random rand = new Random(123);

    @Setup(Level.Invocation)
    public void setup() {
        array = new int[n];
        for (int i = 0; i < n; i++) array[i] = rand.nextInt();
    }

    @Benchmark
    public int quickSelectMedian() {
        int[] copy = array.clone();
        // call your QuickSelect implementation which returns k-th element
        // return QuickSelect.select(copy, copy.length / 2);
        return 0; // placeholder
    }

    @Benchmark
    public int sortAndPickMedian() {
        int[] copy = array.clone();
        java.util.Arrays.sort(copy);
        return copy[copy.length / 2];
    }
}
