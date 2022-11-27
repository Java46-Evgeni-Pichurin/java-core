package telran.multithreading;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.stream.IntStream;

public class FermAppl {

    private static final int N_TRUCKS = 2000;
    private static final int N_LOADS = 500;

    public static void main(String[] args) {
        Truck[] trucks = new Truck[N_TRUCKS];
        Instant start = Instant.now();
        System.out.println("Start trucks with common mutex:\n");
        startTrucks(trucks, true);
        waitingForFinishing(trucks);
        printReport(start);
        Truck.refreshElevators();
        System.out.println("Start trucks with different mutexes:\n");
        start = Instant.now();
        startTrucks(trucks, false);
        waitingForFinishing(trucks);
        printReport(start);
    }

    private static void printReport(Instant start) {
        System.out.printf("""
                        Report: elevator1 contains %d tons; elevator2 contains %d tons
                        running time is %d
                        number of waiting iterations is %d
                        ===============================================================
                        
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

    private static void startTrucks(Truck[] trucks, boolean isCommonMutex) {
        IntStream.range(0, trucks.length)
                .forEach(i -> {
                    trucks[i] = new Truck(1, N_LOADS, isCommonMutex);
                    trucks[i].start();
                });
    }
}
