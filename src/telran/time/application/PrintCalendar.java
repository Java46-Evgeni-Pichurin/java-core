package telran.time.application;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.Locale;

public class PrintCalendar {

    public static void main(String[] args) {
        int[] monthYear;
        try {
            monthYear = getMonthYear(args);
            printCalendar(monthYear[0], monthYear[1], monthYear[2]);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printCalendar(int month, int year, int dayOfWeek) {
        printTitle(month, year);
        printWeekDays(dayOfWeek);
        printDates(month, year, dayOfWeek);
    }

    private static void printDates(int month, int year, int dayOfWeek) {
        int column = getFirstColumn(month, year, dayOfWeek);
        printOffset(column);
        int nDays = getMonthDays(month, year);
        int nWeekDays = DayOfWeek.values().length;
        for (int day = 1; day <= nDays; day++) {
            System.out.printf("%4d", day);
            column++;
            if (column == nWeekDays) {
                column = 0;
                System.out.println();
            }
        }
    }

    private static int getMonthDays(int month, int year) {
        return YearMonth.of(year, month).lengthOfMonth();
    }

    private static void printOffset(int column) {
        System.out.printf("%s", " ".repeat(column * 4));

    }

    private static int getFirstColumn(int month, int year, int dayOfWeek) {
        LocalDate firstMonthDate = LocalDate.of(year, month, 1);
        int weekDay = firstMonthDate.getDayOfWeek().getValue();
        return Math.abs(weekDay - dayOfWeek);
    }

    private static void printWeekDays(int dayOfWeek) {
        DayOfWeek[] dayWeeks = DayOfWeek.values();
        int index = Arrays.binarySearch(dayWeeks, DayOfWeek.of(dayOfWeek));
        DayOfWeek[] copyDayWeeks = new DayOfWeek[dayWeeks.length];
        System.arraycopy(dayWeeks, index, copyDayWeeks, 0, dayWeeks.length - index);
        System.arraycopy(dayWeeks, 0, copyDayWeeks, dayWeeks.length - index, index);

        System.out.print("  ");
        for (DayOfWeek weekDay : copyDayWeeks) {
            System.out.printf("%s ", weekDay.getDisplayName(TextStyle.SHORT, Locale.getDefault()));
        }
        System.out.println();
    }

    private static void printTitle(int month, int year) {
        Month monthEn = Month.of(month);
        System.out.printf("%s, %d\n", monthEn.getDisplayName(TextStyle.FULL,
                Locale.getDefault()), year);
    }

    private static int[] getMonthYear(String[] args) throws Exception {
        LocalDate current = LocalDate.now();
        int[] res = {current.getMonthValue(), current.getYear(), DayOfWeek.of(1).ordinal() + 1};
        if (args.length > 0) {
            res[0] = getMonth(args[0]);
            if (args.length > 1) {
                res[1] = getYear(args[1]);
                if (args.length > 2) {
                    res[2] = getDayOfWeek(args[2]);
                }
            }
        }
        return res;
    }

    private static int getDayOfWeek(String dayOfWeek) throws IllegalArgumentException {
        try {
            dayOfWeek = dayOfWeek.toUpperCase();
            DayOfWeek day = DayOfWeek.valueOf(dayOfWeek);
            return day.ordinal() + 1;
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid value for enum of DayOfWeek class");
        }
    }

    private static int getYear(String yearStr) throws Exception {
        try {
            int res = Integer.parseInt(yearStr);
            if (res <= 0) {
                throw new Exception("year should be a positive number");
            }
            return res;
        } catch (NumberFormatException e) {
            throw new Exception("year should be a number");
        }


    }

    private static int getMonth(String monthStr) throws Exception {
        try {
            int res = Integer.parseInt(monthStr);
            int nMonths = Month.values().length;
            if (res < 1 || res > nMonths) {
                throw new Exception(String.format("month %d is wrong value;"
                        + " should be in the range [1, %d]", res, nMonths));
            }
            return res;
        } catch (NumberFormatException e) {
            throw new Exception("month should be a number");
        }

    }

}
