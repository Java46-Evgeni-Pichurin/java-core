package telran.util;

import java.time.Instant;
import java.time.ZoneId;

public class Logger {
private Level level = Level.INFO;
private Handler handler;
private String name;
public Logger(Handler handler, String name) {
	this.handler = handler;
	this.name = name;
}
private LoggerRecord createLoggerRecord(String message, Level level) {
	return new LoggerRecord(Instant.now(), ZoneId.systemDefault().toString(),
			level, name, message);
}
public void setLevel(Level level) {
	this.level = level;
}
public void error(String message) {
	LoggerRecord loggerRecord = createLoggerRecord(message, Level.ERROR);
	handler.publish(loggerRecord);
	
}
public void warn(String message) {
	if (level.compareTo(Level.WARN) <= 0) {
		LoggerRecord loggerRecord = createLoggerRecord(message, Level.WARN);
		handler.publish(loggerRecord);
	}
	
}
public void info(String message) {
	if (level.compareTo(Level.INFO) <= 0) {
		LoggerRecord loggerRecord = createLoggerRecord(message, Level.INFO);
		handler.publish(loggerRecord);
	}
	
}
public void debug(String message) {
	if (level.compareTo(Level.DEBUG) <= 0) {
		LoggerRecord loggerRecord = createLoggerRecord(message, Level.DEBUG);
		handler.publish(loggerRecord);
	}
	
}
public void trace(String message) {
	if (level.compareTo(Level.TRACE) == 0) {
		LoggerRecord loggerRecord = createLoggerRecord(message, Level.TRACE);
		handler.publish(loggerRecord);
	}
}
}
