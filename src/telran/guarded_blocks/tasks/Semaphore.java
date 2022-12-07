package telran.guarded_blocks.tasks;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class Semaphore represents synchronized counter of resources. Consuming
 * threads acquire, use and then release resources. The maximal count of
 * available simultaneously resources is controlled by Semaphore, which suspends
 * the acquiring thread until at least one resource is freed by others.
 */
public class Semaphore {
    private final int resourceCount;
    private static final AtomicInteger count = new AtomicInteger(0);

    public Semaphore(int resourceCount) {
        this.resourceCount = resourceCount;
    }

    /**
     * Acquires resource if available. If no resources available, suspends the
     * current thread until other one releases at least one resource.
     *
     * @throws InterruptedException
     */
    public synchronized void acquire() throws InterruptedException {
        if (resourceCount > count.get()) {
            count.incrementAndGet();
        }
        else {
            wait();
            count.incrementAndGet();
        }
    }

    /**
     * Releases previously acquired resource. Never suspends current thread.
     */
    public synchronized void release() {
        count.decrementAndGet();
        notify();
    }

    /**
     * Simple test: 10 threads are competing to repeatedly use 5 resources.
     * See that actual number of simultaneously used resources never more than 5.
     */
    public static void main(String[] args) throws InterruptedException {
        final int RESOURCE_COUNT = 5;
        final int THREADS_COUNT = 10;
        final int PER_THREAD_ACQUISITIONS_COUNT = 3; // number "acquire" operations performed by each thread
        AtomicInteger acquiredResources = new AtomicInteger(0);

        Semaphore sem = new Semaphore(RESOURCE_COUNT);

        Runnable r = () -> {
            try {
                for (int i = 0; i < PER_THREAD_ACQUISITIONS_COUNT; i++) {
                    System.out.printf("Thread %s needs resource%n", Thread.currentThread().getName());
                    sem.acquire();
                    int newCount = acquiredResources.incrementAndGet();
                    System.out.printf("Thread %s acquired resource, totally acquired: %d %n",
                            Thread.currentThread().getName(), newCount);
                    Thread.sleep((int) (Math.random() * 2000));
                    newCount = acquiredResources.decrementAndGet(); // before release to avoid racing
                    sem.release();
                    System.out.printf("Thread %s released resource, totally acquired: %d %n",
                            Thread.currentThread().getName(), newCount);
                }
            } catch (InterruptedException e) {
                // noop;
            }
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
