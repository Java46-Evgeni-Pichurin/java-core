package telran.messages.test;

import telran.view.ConsoleInputOutput;
import telran.view.Item;
import telran.view.Menu;

public class MessageSenderApplication {

    public static void main(String[] args) {
        Menu menu = new Menu("Main menu", getItems());
        menu.perform(new ConsoleInputOutput());
    }

    private static Item[] getItems() {
        return new Item[]{
                Item.of("Send message", Sender::send),
                Item.exit("Exit")
        };
    }
}
