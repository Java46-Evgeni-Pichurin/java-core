package telran.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class TcpClientHandler implements Handler {
    Socket socket;
    PrintStream output;
    BufferedReader input;

    public TcpClientHandler(Socket socket, PrintStream output, BufferedReader input) {
        this.socket = socket;
        this.output = output;
        this.input = input;
    }

    @Override
    public void publish(LoggerRecord loggerRecord) {
        LocalDateTime ldt = LocalDateTime.ofInstant(loggerRecord.timestamp,
                ZoneId.of(loggerRecord.zoneId));
        try {
            output.printf("%s %s %s %s\n", ldt, loggerRecord.level,
                    loggerRecord.loggerName, loggerRecord.message);
            input.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
