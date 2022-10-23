package telran.util;

import java.time.Instant;
import java.time.OffsetDateTime;

public class Logger {
Level level = Level.INFO;
Handler handler;
String name;

    public Logger(Handler handler, String name) {
        this.handler = handler;
        this.name = name;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    private LoggerRecord getLoggerRecord (String message, Level level) {
        return new LoggerRecord(
                Instant.now(),
                OffsetDateTime.now().getOffset().toString(),
                level,
                name,
                message);
    }
    public void error(String message) {
        LoggerRecord errorRecord = getLoggerRecord(message, Level.ERROR);
        handler.publish(errorRecord);
    }
    public void warn(String message) {
        LoggerRecord warnRecord = getLoggerRecord(message, Level.WARN);
        if (!level.equals(Level.ERROR)) {
            handler.publish(warnRecord);
        }
    }
    public void info(String message) {
        LoggerRecord infoRecord = getLoggerRecord(message, Level.INFO);
        if (level.equals(Level.TRACE) || level.equals(Level.DEBUG) || level.equals(Level.INFO)) {
            handler.publish(infoRecord);
        }
    }
    public void debug(String message) {
        LoggerRecord debugRecord = getLoggerRecord(message, Level.INFO);
        if (level.equals(Level.TRACE) || level.equals(Level.DEBUG)) {
            handler.publish(debugRecord);
        }
    }
    public void trace(String message) {
        LoggerRecord traceRecord = getLoggerRecord(message, Level.INFO);
        if (level.equals(Level.TRACE)) {
            handler.publish(traceRecord);
        }
    }
}
