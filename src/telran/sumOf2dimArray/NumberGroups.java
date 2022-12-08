package telran.sumOf2dimArray;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
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
        long time0 = System.currentTimeMillis();
        AtomicLong res = new AtomicLong();
        ExecutorService executor = Executors.newFixedThreadPool(nThreads);
        ArrayList<OneGroupSum> tasks = new ArrayList<>();
        Arrays.stream(groups).forEach(group -> {
            OneGroupSum groupSum = new OneGroupSum(group);
            tasks.add(groupSum);
            executor.execute(groupSum);
        });
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        tasks.forEach(task -> res.addAndGet(task.getRes()));
        System.out.printf("Number of threads: %d, total time: %d\n", nThreads, System.currentTimeMillis() - time0);
        return res.get();
    }
}
