package telran.util;

import java.time.Instant;

public class LoggerRecord {
    final Instant timeStamp;
    final String zoneId;
    final Level level;
    final String loggerName;
    final String message;

    public LoggerRecord(Instant timeStamp, String zoneId, Level level, String loggerName, String message) {
        this.timeStamp = timeStamp;
        this.zoneId = zoneId;
        this.level = level;
        this.loggerName = loggerName;
        this.message = message;
    }
}
