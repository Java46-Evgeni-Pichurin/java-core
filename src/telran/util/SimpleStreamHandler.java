package telran.util;

import java.io.PrintStream;

public class SimpleStreamHandler implements Handler{
    PrintStream stream;

    public SimpleStreamHandler(PrintStream stream) {
        this.stream = stream;
    }

    @Override
    public void publish(LoggerRecord loggerRecord) {
        stream.print(loggerRecord.timeStamp + "\n" +
                loggerRecord.zoneId + "\n" +
                loggerRecord.level + "\n" +
                loggerRecord.loggerName + "\n" +
                loggerRecord.message);
    }
}
