package telran.multithreading;

public class Printer extends Thread {
    private final int number;
    private int N_NUMBERS;
    private final int N_PORTIONS;
    private Printer otherPrinter;


    public Printer(int N_NUMBERS, int N_PORTIONS, int number) {
        this.number = number;
        this.N_NUMBERS = N_NUMBERS;
        this.N_PORTIONS = N_PORTIONS;
        if (N_NUMBERS <= 0 || N_NUMBERS < N_PORTIONS || N_NUMBERS % N_PORTIONS != 0) {
            throw new IllegalArgumentException();
        }
    }

    public void connectTo(Printer other) {
        this.otherPrinter = other;
    }

    public void run() {
        while (N_NUMBERS > 0) {
            try {
                sleep(Long.MAX_VALUE);
            } catch (InterruptedException e) {
                System.out.printf(String.valueOf(number).repeat(N_PORTIONS) + "\n");
                N_NUMBERS -= N_PORTIONS;
                otherPrinter.interrupt();
            }
        }
    }
}