package telran.view.app;

import telran.view.*;

public class MenuApp {
    public static void main(String[] args) {
        Menu menu = new Menu("Main menu", getItems());
        menu.perform(new ConsoleInputOutput());

    }

    private static Item[] getItems() {
        return new Item[]{
                NumbersOperationsMenu.getNumbersOperationsMenu(),
                DateOperationsMenu.getDateOperationsMenu(),
                Item.exit("Exit")
        };
    }
}
