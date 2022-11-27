package telran.multithreading;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Truck extends Thread {
    private final int load;
    private final int nLoads;
    private final boolean isCommonMutex;
    private static final AtomicLong counterOfWaits = new AtomicLong(0);
    private static long elevator1;
    private static long elevator2;
    private static final Lock mutex1 = new ReentrantLock();
    private static final Lock mutex2 = new ReentrantLock();

    public Truck(int load, int nLoads, boolean isCommonMutex) {
        this.load = load;
        this.nLoads = nLoads;
        this.isCommonMutex = isCommonMutex;
    }

    @Override
    public void run() {
        if (isCommonMutex) {
            for (int i = 0; i < nLoads; i++) {
                loadElevator1withMutex1(load);
                loadElevator2withMutex1(load);
            }
        }
        else {
            for (int i = 0; i < nLoads; i++) {
                loadElevator1withMutex1(load);
                loadElevator2withMutex2(load);
            }
        }
    }

    public static long getWaitingCounter() {
        return counterOfWaits.get();
    }

    static private void loadElevator1withMutex1(int load) {
        while (true) {
            if (mutex1.tryLock()) {
                try {
                    elevator1 += load;
                } finally {
                    mutex1.unlock();
                }
                break;
            }
            else {
                counterOfWaits.incrementAndGet();
            }
        }
    }

    static private void loadElevator2withMutex1(int load) {
        while (true) {
            if (mutex1.tryLock()) {
                try {
                    elevator2 += load;
                } finally {
                    mutex1.unlock();
                }
                break;
            }
            else {
                counterOfWaits.incrementAndGet();
            }
        }
    }

    private void loadElevator2withMutex2(int load) {
        while (true) {
            if (mutex2.tryLock()) {
                try {
                    elevator2 += load;
                } finally {
                    mutex2.unlock();
                }
                break;
            }
            else {
                counterOfWaits.incrementAndGet();
            }
        }
    }

    public static void refreshElevators() {
        elevator1 = 0;
        elevator2 = 0;
        counterOfWaits.set(0);
    }
    public static long getElevator1() {
        return elevator1;
    }

    public static long getElevator2() {
        return elevator2;
    }
}
