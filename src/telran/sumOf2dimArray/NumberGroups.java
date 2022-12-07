package telran.sumOf2dimArray;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class NumberGroups {
    private final int[][] groups;
    private int nThreads = 5;

    public NumberGroups(int[][] groups) {
        this.groups = groups;
    }

    public int getnThreads() {
        return nThreads;
    }

    public void setnThreads(int nThreads) {
        this.nThreads = nThreads;
    }

    public long computeSum() {
        AtomicLong res = new AtomicLong();
        ExecutorService executor = Executors.newFixedThreadPool(nThreads);
        executor.execute(() -> Arrays.stream(groups).forEach(group -> {
                        OneGroupSum groupSum = new OneGroupSum(group);
                        groupSum.run();
                        res.addAndGet(groupSum.getRes());
                    }));
        executor.shutdown();
        while (true) {
            if (executor.isTerminated()) break;
        }
        return res.get();
    }

}
