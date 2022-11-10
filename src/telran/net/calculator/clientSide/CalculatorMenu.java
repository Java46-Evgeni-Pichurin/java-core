package telran.net.calculator.clientSide;

import telran.net.calculator.common.Calculator;
import telran.view.InputOutput;
import telran.view.Item;

import java.util.Arrays;

public class CalculatorMenu {
    private static Calculator calculator;
    public static Item[] getCalculatorItems(Calculator calculator) {
        CalculatorMenu.calculator = calculator;
        return new Item[]{
                Item.of("Adding", CalculatorMenu::add),
                Item.of("Subtracting", CalculatorMenu::subtract),
                Item.of("Dividing", CalculatorMenu::divide),
                Item.of("Multiplying", CalculatorMenu::multiply),
                Item.exit("Exit")
        };
    }

    public static void add(InputOutput io) {
        double[] numbers = (double[]) checkData(enterNumbers(io));
        io.writeLine(calculator.add(numbers[0], numbers[1]));
    }

    private static void subtract(InputOutput io) {
        double[] numbers = (double[]) checkData(enterNumbers(io));
        io.writeLine(calculator.subtract(numbers[0], numbers[1]));
    }

    private static void divide(InputOutput io) {
        double[] numbers = (double[]) checkData(enterNumbers(io));
        io.writeLine(calculator.divide(numbers[0], numbers[1]));
    }

    private static void multiply(InputOutput io) {
        double[] numbers = (double[]) checkData(enterNumbers(io));
        io.writeLine(calculator.multiply(numbers[0], numbers[1]));
    }

    //Data verification on client side
    private static Object checkData(double[] numbers) {
        if (numbers.length != 2) {
            throw new RuntimeException("Wrong number of operands. ");
        }
        return numbers;
    }
    private static double[] enterNumbers(InputOutput io) {
        return io.readObject("Enter two numbers. ", "ERROR: ", s -> {
            String[] arr = s.split(" ");
            return Arrays.stream(arr).mapToDouble(Double::parseDouble).toArray();
        });
    }
}
