package telran.multithreading.games;

public class Runner extends Thread {
    private final Race race;
    private final int runnerId;
    private static final long start = System.currentTimeMillis();

    public Runner(Race race, int runnerId) {
        this.race = race;
        this.runnerId = runnerId;
    }

    @Override
    public void run() {
        int sleepRange = race.getMaxSleep() - race.getMinSleep() + 1;
        int minSleep = race.getMinSleep();
        int distance = race.getDistance();
        //long start = System.currentTimeMillis();
        for (int i = 0; i < distance; i++) {
            try {
                sleep((long) (minSleep + Math.random() * sleepRange));
            } catch (InterruptedException e) {
                throw new IllegalStateException();
            }
            System.out.println(runnerId);
        }
        addToTable(runnerId);
    }

    synchronized private void addToTable(int runnerId) {
        race.setPlace(System.currentTimeMillis() - Runner.start, runnerId);
    }
}
