package com.telran.philosophers.task;

public class Philosophers {
    public static void main(String[] args) throws Exception {

        Philosopher[] philosophers = new Philosopher[5];
        Object[] forks = new Object[philosophers.length];

        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Object();
        }

        for (int i = 0; i < philosophers.length; i++) {
            Object leftFork = forks[i];
            Object rightFork = forks[(i + 1) % forks.length];

            philosophers[i] = new Philosopher(leftFork, rightFork);

            Thread t = new Thread(philosophers[i], "Philosopher " + (i + 1));
            t.start();
        }
    }

    static class Philosopher implements Runnable {

        private final Object leftFork;
        private final Object rightFork;
        private static final Object accept = new Object();

        public Philosopher(Object leftFork, Object rightFork) {
            this.leftFork = leftFork;
            this.rightFork = rightFork;
        }

        private void doAction(String action) throws InterruptedException {
            System.out.println(Thread.currentThread().getName() + " " + action);
            Thread.sleep(((int) (Math.random() * 10)));
        }


        @Override
        public void run() {
            try {
                while (true) {
                    // thinking
                    doAction(System.nanoTime() + ": Thinking");
                    synchronized (accept) {
                        synchronized (leftFork) {
                            doAction(System.nanoTime() + ": Picked up left fork");
                            synchronized (rightFork) {
                                // eating
                                doAction(System.nanoTime() + ": Picked up right fork - eating");

                                doAction(System.nanoTime() + ": Put down right fork");
                            }
                            // Back to thinking
                            doAction(System.nanoTime() + ": Put down left fork. Back to thinking");
                        }
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
