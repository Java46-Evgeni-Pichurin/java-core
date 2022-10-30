package telran.util;

import java.time.Instant;

public class LoggerRecord {

    public final Instant timestamp;
    public final String zoneId;
    public final Level level;
    public final String loggerName;
    public final String message;

    public LoggerRecord(Instant timestamp, String zoneId, Level level, String loggerName, String message) {
        super();
        this.timestamp = timestamp;
        this.zoneId = zoneId;
        this.level = level;
        this.loggerName = loggerName;
        this.message = message;
    }
}
