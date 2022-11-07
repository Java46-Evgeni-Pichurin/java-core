package telran.view.menu;

import telran.view.InputOutput;
import telran.view.Item;

public class NumbersOperationsMenu {

    public static Item getNumbersOperationsMenu() {
        return new Menu("Arithmetic operations", getItems());
    }

    private static Item[] getItems() {
        return new Item[]{
                Item.of("Adding", NumbersOperationsMenu::add),
                Item.of("Subtracting", NumbersOperationsMenu::subtract),
                Item.of("Dividing", NumbersOperationsMenu::divide),
                Item.of("Multiplying", NumbersOperationsMenu::multiply),
                Item.exit("Back to main menu.")
        };
    }

    static void add(InputOutput io) {
        double[] numbers = enterNumbers(io);
        io.writeLine(numbers[0] + numbers[1]);
    }
    static void subtract(InputOutput io) {
        double[] numbers = enterNumbers(io);
        io.writeLine(numbers[0] - numbers[1]);
    }
    static void multiply(InputOutput io) {
        double[] numbers = enterNumbers(io);
        io.writeLine(numbers[0] * numbers[1]);
    }

    static void divide(InputOutput io) {
        double[] numbers = enterNumbers(io);
        io.writeLine(numbers[0] / numbers[1]);
    }

    private static double[] enterNumbers(InputOutput io) {
        double[] res = new double[2];
        res[0] = io.readDouble("enter first number", "no number");
        res[1] = io.readDouble("enter second number", "no number");
        return res;
    }
}
