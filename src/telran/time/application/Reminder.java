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
        getStart(intervalValue, cUnit, reminderDuration);
    }

    private static long getNumber(String number) throws Exception {
        long res;
        try {
            res = Long.parseLong(number);
        } catch (NumberFormatException e) {
            throw new Exception("Interval and duration should be a number.");
        }
        if (res <= 0) {
            throw new IllegalArgumentException("Interval and duration should be a positive number.");
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

    private static void getStart(long intervalValue, ChronoUnit cUnit, long reminderDuration) {
        long intervalInMills = intervalValue * cUnit.getDuration().toMillis();
        long durationInMills;
        if (reminderDuration == 1) {
            durationInMills = ChronoUnit.HOURS.getDuration().toMillis();
        } else {
            durationInMills = reminderDuration * cUnit.getDuration().toMillis();
        }
        if (intervalInMills > durationInMills) {
            throw new IllegalArgumentException("Duration can not be less than given interval.");
        }
        getRock(intervalInMills, durationInMills);
    }

    private static void getRock(long intervalInMills, long durationInMills) {
        final long mills = System.currentTimeMillis();
        final long[] realInterval = new long[1];
        Timer timer = new Timer();
        LocalDateTime ldt = LocalDateTime.now();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                realInterval[0] = System.currentTimeMillis() - mills;
                Duration duration = Duration.between(ldt, LocalDateTime.now());
                // LOW ACCURACY
                System.out.printf("\007\007\007 - %d seconds, %d milliseconds\n", duration.getSeconds(), duration.getNano() / FROM_NANO_TO_MILLI);
                if (realInterval[0] > durationInMills) {
                    cancel();
                    timer.cancel();
                }
            }
        };
        timer.schedule(task, intervalInMills, intervalInMills);
    }
}