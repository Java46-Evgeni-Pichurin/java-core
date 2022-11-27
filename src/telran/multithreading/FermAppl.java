package telran.multithreading;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.stream.IntStream;

public class FermAppl {

    private static final int N_TRUCKS = 2000;
    private static final int N_LOADS = 500;
    private static final int N_RUNS = 10;

    public static void main(String[] args) {
        Truck[] trucks = new Truck[N_TRUCKS];
        System.out.printf("Start trucks with common mutex (%d runs):\n", N_RUNS);
        runApp(trucks, true);
        System.out.printf("\nStart trucks with different mutexes (%d runs):\n", N_RUNS);
        runApp(trucks, false);
    }

    private static void runApp(Truck[] trucks, boolean hasCommonMutex) {
        long avgTime = startRunning(trucks , hasCommonMutex);
        System.out.printf("Average running time is :%d\n", avgTime);
    }

    private static long startRunning(Truck[] trucks, boolean hasCommonMutex) {
        Instant start = Instant.now();
        long totalRunningTime = 0;
        for (int i = 0; i < N_RUNS; i++) {
            startTrucks(trucks, hasCommonMutex);
            waitingForFinishing(trucks);
            printReport(start);
            Truck.refreshElevators();
            totalRunningTime += ChronoUnit.MILLIS.between(start, Instant.now());
            start = Instant.now();
        }
        return totalRunningTime / N_RUNS;
    }

    private static void printReport(Instant start) {
        System.out.printf("""
                        Report: elevator1 contains %d tons; elevator2 contains %d tons; running time is %d; number of waiting iterations is %d
                        """,
                Truck.getElevator1(), Truck.getElevator2(),
                ChronoUnit.MILLIS.between(start, Instant.now()), Truck.getWaitingCounter());
    }

    private static void waitingForFinishing(Truck[] trucks) {
        Arrays.stream(trucks).forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException ignored) {
            }
        });

    }

    private static void startTrucks(Truck[] trucks, boolean hasCommonMutex) {
        IntStream.range(0, trucks.length)
                .forEach(i -> {
                    trucks[i] = new Truck(1, N_LOADS, hasCommonMutex);
                    trucks[i].start();
                });
    }
}
