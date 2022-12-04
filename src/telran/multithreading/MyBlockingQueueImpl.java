package telran.multithreading;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BooleanSupplier;

public class MyBlockingQueueImpl<E> implements BlockingQueue<E> {
    private final LinkedList<E> queue;
    private final int capacity;
	private final Lock monitor;
	private final Condition consumerCondition;
	private final Condition producerCondition;

    public MyBlockingQueueImpl(int capacity) {
        this.capacity = capacity;
		this.queue = new LinkedList<>();
		this.monitor = new ReentrantLock();
		this.consumerCondition = monitor.newCondition();
		this.producerCondition = monitor.newCondition();
    }

    public MyBlockingQueueImpl() {
        this(Integer.MAX_VALUE);
    }

    @Override
    public E remove() {
        monitor.lock();
        try {
            return queue.remove();
        } finally {
            monitor.unlock();
        }
    }

    @Override
    public E poll() {
        try {
            return isEmpty() ? null : poll(0, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public E element() {
        monitor.lock();
        try {
            return queue.element();
        } finally {
            monitor.unlock();
        }
    }

    @Override
    public E peek() {
        monitor.lock();
        try {
            return queue.peek();
        } finally {
            monitor.unlock();
        }
    }

    @Override
    public int size() {
        monitor.lock();
        try {
            return queue.size();
        } finally {
            monitor.unlock();
        }
    }

    @Override
    public boolean isEmpty() {
        monitor.lock();
        try {
            return queue.isEmpty();
        } finally {
            monitor.unlock();
        }
    }

    @Override
    public Iterator<E> iterator() {
        monitor.lock();
        try {
            return queue.iterator();
        } finally {
            monitor.unlock();
        }
    }

    @Override
    public Object[] toArray() {
        monitor.lock();
        try {
            return queue.toArray(); // safe
        } finally {
            monitor.unlock();
        }
    }

    @Override
    public <T> T[] toArray(T[] a) {
        monitor.lock();
        try {
            return queue.toArray(a);
        } finally {
            monitor.unlock();
        }
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        monitor.lock();
        try {
            return queue.containsAll(c);
        } finally {
            monitor.unlock();
        }
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        monitor.lock();
        try {
            return queue.addAll(c);
        } finally {
            monitor.unlock();
        }
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        monitor.lock();
        try {
            return queue.removeAll(c);
        } finally {
            monitor.unlock();
        }
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        monitor.lock();
        try {
            return queue.retainAll(c);
        } finally {
            monitor.unlock();
        }
    }

    @Override
    public void clear() {
        monitor.lock();
        try {
            queue.clear();
        } finally {
            monitor.unlock();
        }
    }

    @Override
    public boolean add(E e) {
        monitor.lock();
        try {
            return queue.add(e);
        } finally {
            monitor.unlock();
        }
    }

    @Override
    public boolean offer(E e) {
        try {
            return !isEmpty() && offer(e, 0, TimeUnit.NANOSECONDS);
        } catch (InterruptedException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void put(E e) throws InterruptedException {
        if (e == null) {
            throw new IllegalArgumentException();
        }
        try {
            monitor.lock();
            while (queue.size() >= capacity) {
                producerCondition.await();
            }
            queue.addLast(e);
            if (queue.size() == 1) {
                consumerCondition.signal();
            }
        } finally {
            monitor.unlock();
        }
    }

    @Override
    public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
        long millis = TimeUnit.valueOf(String.format("%s", unit)).toMillis(timeout);
        if (e == null || millis < 0) {
            throw new IllegalArgumentException();
        }
        try {
            monitor.lock();
            if (!timeWaiting(producerCondition,
                    () -> queue.size() < capacity,
                    millis,
                    unit)) {
                return false;
            }
            queue.addLast(e);
            if (queue.size() == 1) {
                consumerCondition.signal();
            }
            return true;
        } finally {
            monitor.unlock();
        }
    }

    @Override
    public E take() throws InterruptedException {
        try {
            monitor.lock();
            while (queue.isEmpty()) {
                consumerCondition.await();
            }
            if (queue.size() == capacity) {
                producerCondition.signal();
            }
            return queue.removeFirst();
        } finally {
            monitor.unlock();
        }
    }

    @Override
    public E poll(long timeout, TimeUnit unit) throws InterruptedException {
        if (timeout < 0) {
            throw new IllegalArgumentException();
        }
        try {
            monitor.lock();
            if (!timeWaiting(
                    consumerCondition,
                    () -> !queue.isEmpty(),
                    timeout,
                    unit)) {
                return null;
            }
            if (queue.size() == capacity) {
                producerCondition.signal();
            }
            return queue.removeFirst();
        } finally {
            monitor.unlock();
        }
    }

    private boolean timeWaiting(Condition consumerCondition, BooleanSupplier fitCondition, long timeout, TimeUnit unit) throws InterruptedException {
        long timeToWaitNanos = TimeUnit.valueOf(String.format("%s", unit)).toNanos(timeout);
        while (! fitCondition.getAsBoolean()) {
            if (timeToWaitNanos <= 0) {
                return false;
            }
            timeToWaitNanos = consumerCondition.awaitNanos(timeToWaitNanos);
        }
        return true;
    }

    @Override
    public int remainingCapacity() {
        monitor.lock();
        try {
            return capacity == Integer.MIN_VALUE ? Integer.MAX_VALUE : capacity - queue.size();
        } finally {
            monitor.unlock();
        }
    }

    @Override
    public boolean remove(Object o) {
        //No implement
        return false;
    }

    @Override
    public boolean contains(Object o) {
        monitor.lock();
        try {
            return queue.contains(o);
        } finally {
            monitor.unlock();
        }
    }

    @Override
    public int drainTo(Collection<? super E> c) {
        //No implement
        return 0;
    }

    @Override
    public int drainTo(Collection<? super E> c, int maxElements) {
        //No implement
        return 0;
    }
}