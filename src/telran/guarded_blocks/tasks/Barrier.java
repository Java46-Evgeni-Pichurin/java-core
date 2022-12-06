package telran.guarded_blocks.tasks;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * Class Barrier allows a set of threads to all wait for each other to reach a common barrier point.
 */
public class Barrier {
    private final int threadsCount;
    private final AtomicInteger count = new AtomicInteger(0);

    public Barrier(int threadsCount) {
        this.threadsCount = threadsCount;
    }

    /**
     * This is common barrier wait point.
     * Each thread waits until all participant threads reach this point,
     * and only when continues to run.
     */
    public synchronized void await() {
        try {
            if (count.incrementAndGet() < threadsCount) wait();
            else notifyAll();
        } catch (InterruptedException ignored) {}
        finally {
            count.compareAndSet(threadsCount, 0);
        }
    }

    /**
     * Simple test: the number of threads wait one another and "pass barrier" only when last thread arrives
     * Example of output:
     * 2296 Thread Thread-3 arrived to barrier
     * 2339 Thread Thread-4 arrived to barrier
     * 2646 Thread Thread-1 arrived to barrier
     * 4048 Thread Thread-2 arrived to barrier
     * 9598 Thread Thread-0 arrived to barrier  // last arrived
     * 9599 Thread Thread-0 passed the barrier  // below all threads simultaneously passed the barrier
     * 9599 Thread Thread-3 passed the barrier
     * 9599 Thread Thread-4 passed the barrier
     * 9599 Thread Thread-1 passed the barrier
     * 9599 Thread Thread-2 passed the barrier
     */
    public static void main(String[] args) throws InterruptedException {
        final int THREADS_COUNT = 5;
        long startTime = System.currentTimeMillis();
        Barrier barrier = new Barrier(THREADS_COUNT);
        Runnable r = () -> {
            try {
                Thread.sleep((int) (Math.random() * 10000));
            } catch (InterruptedException e) {
                // noop
            }
            System.out.printf("%4d Thread %s arrived to barrier%n", System.currentTimeMillis() - startTime,
                    Thread.currentThread().getName());
            barrier.await();
            System.out.printf("%4d Thread %s passed the barrier%n", System.currentTimeMillis() - startTime,
                    Thread.currentThread().getName());
        };

        List<Thread> threads = Stream.generate(() -> new Thread(r))
                .limit(THREADS_COUNT)
                .peek(Thread::start)
                .toList();

        for (Thread t : threads) {
            t.join();
        }
    }

}
