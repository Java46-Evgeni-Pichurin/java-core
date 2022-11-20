package telran.multithreading.games;

import java.util.TreeMap;

public class Race {
    private final int distance;
    private final int minSleep;
    private final int maxSleep;
    private final TreeMap<Integer, Long> map = new TreeMap<>();

    public Race(int distance, int minSleep, int maxSleep) {
        this.distance = distance;
        this.minSleep = minSleep;
        this.maxSleep = maxSleep;
    }

    public TreeMap<Integer, Long> getMap() {
        return map;
    }

    synchronized public void setPlace(long time, int id) {
        map.put(id, time);
    }

    public int getDistance() {
        return distance;
    }

    public int getMinSleep() {
        return minSleep;
    }

    public int getMaxSleep() {
        return maxSleep;
    }
}