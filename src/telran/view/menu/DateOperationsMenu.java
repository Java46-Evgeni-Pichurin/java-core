package telran.view.menu;

import telran.view.InputOutput;
import telran.view.Item;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;

public class DateOperationsMenu {
    static final String ERROR = "ERROR";
    public static Item getDateOperationsMenu() {
        return new Menu("Operations with dates", getItems());
    }

    private static Item[] getItems() {
        return new Item[]{
                Item.of("Date after given number days", DateOperationsMenu::getDateAfterDays),
                Item.of("Date before given number days", DateOperationsMenu::getDateBeforeDays),
                Item.of("Number days between two dates", DateOperationsMenu::getDurationInDays),
                Item.of("Age according to the birthdate", DateOperationsMenu::getAge),
                Item.exit("Back to main menu.")
        };
    }

    private static void getDateAfterDays(InputOutput io) {
        LocalDate localDate = io.readDate("Put a date in format yyyy-MM-dd.", ERROR);
        int numOfDays = io.readInt("Enter number of days", "Wrong number");
        io.writeLine(localDate.plus(Period.ofDays(numOfDays)));
    }

    private static void getDateBeforeDays(InputOutput io) {
        LocalDate localDate = io.readDate("Put a date in format yyyy-MM-dd.", ERROR);
        int numOfDays = io.readInt("Enter number of days", "Wrong number");
        io.writeLine(localDate.minus(Period.ofDays(numOfDays)));
    }

    private static void getDurationInDays(InputOutput io) {
        LocalDate from = io.readDate("Put a first date in format yyyy-MM-dd.", ERROR);
        LocalDate to = io.readDate("Put a second date in format yyyy-MM-dd.", ERROR);
        io.writeLine(Duration.between(from, to).toDays());
    }

    private static void getAge(InputOutput io) {
        LocalDate birthdate = io.readDate("Put your birthdate in format yyyy-MM-dd.", ERROR);
        io.writeLine(Period.between(birthdate, LocalDate.now()).getYears());
    }
}