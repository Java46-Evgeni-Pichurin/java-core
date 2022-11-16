package telran.multithreading;

public class Printer extends Thread {
    private static final long TIMEOUT = 1000;
    private final int number;
    private int N_NUMBERS;
    private final int N_PORTIONS;
    private boolean running = false;
    private Printer otherPrinter;


    public Printer(int N_NUMBERS, int N_PORTIONS, int number) {
        this.number = number;
        this.N_NUMBERS = N_NUMBERS;
        this.N_PORTIONS = N_PORTIONS;
        if (N_NUMBERS <= 0 || N_NUMBERS < N_PORTIONS || N_NUMBERS % N_PORTIONS != 0) {
            throw new IllegalArgumentException();
        }
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void connectTo(Printer other) {
        this.otherPrinter = other;
    }

    public void run() {
        setRunning(true);
        while (running) {
            if (N_NUMBERS > 0) {
                System.out.printf(String.valueOf(number).repeat(N_PORTIONS) + "\n");
                N_NUMBERS -= N_PORTIONS;
            } else {
                setRunning(false);
                break;
            }
            try {
                sleep(TIMEOUT);
            } catch (InterruptedException e) {
                if (!otherPrinter.isRunning()) {
                    otherPrinter.start();
                    otherPrinter.interrupt();
                }
            }
        }
    }
}
