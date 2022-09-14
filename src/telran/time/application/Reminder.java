package telran.time.application;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Timer;
import java.util.TimerTask;

public class Reminder {

    private static final int FROM_NANO_TO_MILLI = 1_000_000;

    public static void main(String[] args) {
        try {
            getReminder(args);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void getReminder(String[] args) throws Exception {
        switch (args.length) {
            case 2 -> buildReminder(args[0], args[1], null);
            case 3 -> buildReminder(args[0], args[1], args[2]);
            default -> throw new IllegalArgumentException("Wrong number of arguments");
        }
    }

    private static void buildReminder(String interval, String unit, String duration) throws Exception {
        long intervalValue = getNumber(interval);                           // mandatory args[0] interval value
        ChronoUnit cUnit = getUnit(unit);                                   // mandatory args[1] ChronoUnit enum string value (case insensitive)
        long reminderDuration = duration == null ? 1 : getNumber(duration); // optional args[2] duration
        getRock(intervalValue, cUnit, reminderDuration);
    }

    private static long getNumber(String number) throws Exception {
        long res;
        try {
            res = number == null ? -1 : Long.parseLong(number);
        } catch (NumberFormatException e) {
            throw new Exception("Interval and duration should be a number.");
        }
        if (res <= 0 && number != null) {
            throw new Exception("Interval and duration should be a positive number.");
        }
        return res;
    }

    private static ChronoUnit getUnit(String unit) throws Exception {
        try {
            return ChronoUnit.valueOf(unit.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new Exception("Wrong second argument. Must be ChronoUnit enum string value");
        }
    }

    private static void getRock(long intervalValue, ChronoUnit cUnit, long reminderDuration) {
        long intervalInMills = intervalValue * cUnit.getDuration().toMillis();
        long[] durationInMills = new long[1];
        Timer timer = new Timer();
        final long mills = System.currentTimeMillis();
        LocalDateTime ldt = LocalDateTime.now();
        if (reminderDuration == 1) {
            durationInMills[0] = ChronoUnit.HOURS.getDuration().toMillis();
        }
        durationInMills[0] = reminderDuration * cUnit.getDuration().toMillis();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                long between = System.currentTimeMillis() - mills;
                Duration duration = Duration.between(ldt, LocalDateTime.now());
                System.out.printf("\007\007\007 - %d seconds, %d milliseconds\n", duration.getSeconds(), duration.getNano() / FROM_NANO_TO_MILLI) ;
                if (between > durationInMills[0]) {
                    cancel();
                    timer.cancel();
                }
            }
        };
        timer.schedule(task, intervalInMills, intervalInMills);
    }
}