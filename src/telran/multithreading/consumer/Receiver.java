package telran.multithreading.consumer;

import telran.multithreading.MessageBox;

import java.util.concurrent.atomic.AtomicInteger;

public class Receiver extends Thread {
    private final MessageBox messageBox;
    private static final AtomicInteger messagesCounter = new AtomicInteger(0);

    public Receiver(MessageBox messageBox) {
        this.messageBox = messageBox;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String message = messageBox.get();
                if (message != null) {
                    System.out.println(message + getName());
                    messagesCounter.incrementAndGet();
                }
                else break;
            } catch (InterruptedException ignored) {}
        }
    }

    public static int getMessagesCounter() {
        return messagesCounter.get();
    }
}
