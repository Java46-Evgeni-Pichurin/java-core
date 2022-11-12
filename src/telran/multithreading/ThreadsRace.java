package telran.multithreading;

import telran.view.ConsoleInputOutput;
import telran.view.menu.Menu;

public class ThreadsRace {
    public static void main(String[] args) {
        Menu menu = new Menu("Main menu", RaceMenu.getItems());
        menu.perform(new ConsoleInputOutput());
    }
}
