package telran.multithreading.games;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import telran.view.*;
import telran.view.menu.Menu;

public class RaceAppl {

    private static final int MAX_THREADS = 20;
    private static final int MIN_DISTANCE = 10;
    private static final int MAX_DISTANCE = 1000;
    private static final int MIN_SLEEP = 2;
    private static final int MAX_SLEEP = 5;

    public static void main(String[] args) {
        InputOutput io = new ConsoleInputOutput();
        Item[] items = getItems();
        Menu menu = new Menu("Race Game", items);
        menu.perform(io);
    }

    private static Item[] getItems() {
        return new Item[]{
                Item.of("Start new game", RaceAppl::startGame),
                Item.exit("Exit")
        };
    }

    static void startGame(InputOutput io) {
            int nThreads = io.readInt("Enter number of the runners", "Wrong number of the runners" , 2, MAX_THREADS);
            int distance = io.readInt("Enter distance", "Wrong Distance ", MIN_DISTANCE, MAX_DISTANCE);
            Race race = new Race(distance, MIN_SLEEP, MAX_SLEEP);
            Runner[] runners = new Runner[nThreads];
            startRunners(runners, race);
            joinRunners(runners);
            Map<Integer, Long> sortedMap = getSorted(race);
            displayTable(sortedMap);
            displayWinner(sortedMap);
    }

    private static Map<Integer, Long> getSorted(Race race) {
        return race.getMap().entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.naturalOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }


    private static void displayTable(Map<Integer, Long> sortedMap) {
        Object[] time = sortedMap.values().toArray();
        ArrayList<Integer> racerNums = new ArrayList<>(sortedMap.keySet());
        System.out.println("\nPlace" + " ".repeat(5) + "Racer number" + " ".repeat(5) + "Time" + "\n");
        IntStream.range(0, racerNums.size()).forEach(r -> {
            int placeStringLength = ("" + (r + 1)).length();
            int raceNumberStringLength = ("" + racerNums.get(r).toString()).length();

            System.out.println((r + 1)  + " ".repeat(7 - placeStringLength) + "|"
                    + " ".repeat(7) +  racerNums.get(r)  + " ".repeat(7 - raceNumberStringLength) + "|"
                    + " ".repeat(5) + time[r]);
            System.out.println("-----------------------------------");
        });

    }

    private static void displayWinner(Map<Integer, Long> sortedMap) {
        System.out.println("Congratulations to runner " + sortedMap.keySet().toArray()[0]);

    }

    private static void joinRunners(Runner[] runners) {
        IntStream.range(0, runners.length).forEach(i -> {
            try {
                runners[i].join();
            } catch (InterruptedException e) {
                throw new IllegalStateException();
            }
        });
    }

     private static void startRunners(Runner[] runners, Race race) {
        IntStream.range(0, runners.length).forEach(i -> {
            runners[i] = new Runner(race, i + 1);
            runners[i].start();
        });
    }
}