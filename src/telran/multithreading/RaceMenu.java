package telran.multithreading;

import telran.view.InputOutput;
import telran.view.Item;

import java.util.ArrayList;
import java.util.List;

public class RaceMenu extends Thread {
    private static final int MIN_PARTICIPANTS = 3;
    private static final int MAX_PARTICIPANTS = 10;
    private static final int MIN_DISTANCE = 100;
    private static final int MAX_DISTANCE = 3500;

    private static int inputParticipants;
    private static int inputDistance;

    static Item[] getItems() {
        return new Item[]{
                Item.of("Number of participants", RaceMenu::addParticipants),
                Item.of("Distance", RaceMenu::addDistance),
                Item.of("Start Race", RaceMenu::startRace, true)
        };
    }

    private static void startRace(InputOutput io) {
        List<Participant> list = new ArrayList<>();
        for (int i = 0; i < inputParticipants; i++) {
            list.add(new Participant(i, inputDistance));
        }
        list.forEach(Thread::start);
    }

    private static void addDistance(InputOutput io) {
        inputDistance = io.readInt("Enter distance: ", "ERROR: ", MIN_DISTANCE, MAX_DISTANCE);
    }

    private static void addParticipants(InputOutput io) {
        inputParticipants = io.readInt("Enter number of participants: ", "ERROR: ", MIN_PARTICIPANTS, MAX_PARTICIPANTS);
    }
}