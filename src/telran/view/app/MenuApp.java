package telran.view.app;

import telran.view.*;
import telran.view.menu.DateOperationsMenu;
import telran.view.menu.Menu;
import telran.view.menu.NumbersOperationsMenu;

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
