package telran.multithreading;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.*;

public class MessageBox {
    private String message;
    private final AtomicBoolean finished = new AtomicBoolean(false);
    private final Lock monitor = new ReentrantLock();
    private final Condition producerWaitingCondition = monitor.newCondition();
    private final Condition consumerWaitingCondition = monitor.newCondition();

    public void setFinished(boolean finished) {
        this.finished.set(finished);
    }

    public void put(String message) throws InterruptedException {
        monitor.lock();
        try {
            while (this.message != null) {
                producerWaitingCondition.await();
            }
            this.message = message;
            consumerWaitingCondition.signal();
        } finally {
            monitor.unlock();
        }
    }

    public String get() throws InterruptedException {
        monitor.lock();
        try {
            while (!finished.get() && message == null) {
                consumerWaitingCondition.await();
            }
            String res = message;
            message = null;
            producerWaitingCondition.signal();
            return res;
        } finally {
            monitor.unlock();
        }
    }

    public String take() {
        monitor.lock();
        try {
            return message; //possible null
        } finally {
            monitor.unlock();
        }
    }
}