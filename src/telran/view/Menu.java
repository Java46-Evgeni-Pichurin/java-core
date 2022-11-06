package telran.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Menu implements Item {
    private static final int N_ASTERISKS = 50;
    private String name;
    private ArrayList<Item> items;

    public Menu(String name, ArrayList<Item> items) {
        this.name = name;
        this.items = items;
    }

    public Menu(String name, Item... items) {
        this(name, new ArrayList<>(Arrays.asList(items)));
    }

    @Override
    public String displayName() {
        return name;
    }

    @Override
    public void perform(InputOutput io) {
        while (true) {
            try {
                displayMenu(io);
                int itemNumber = io.readInt("Enter item number", "Wrong item number", 1, items.size());
                Item item = items.get(itemNumber - 1);
                item.perform(io);
                if (item.isExit()) {
                    break;
                }
            } catch (Exception e) {
                io.writeLine(e.getMessage());
            }
        }


    }

    private void displayMenu(InputOutput io) {
        io.writeLine("*".repeat(N_ASTERISKS));
        int nameLength = name.length();
        io.writeObject(" ".repeat((N_ASTERISKS - nameLength) / 2));
        io.writeLine(name);
        io.writeLine("*".repeat(N_ASTERISKS));
        IntStream.rangeClosed(1, items.size())
                .forEach(i -> io.writeLine(String.format("%d. %s", i, items.get(i - 1).displayName())));
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
