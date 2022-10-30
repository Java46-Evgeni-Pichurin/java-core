package telran.util.tests;
import java.io.*;
import java.net.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.junit.jupiter.api.*;
import telran.net.ServerLogAppl;
import telran.util.Level;
import telran.util.Logger;
import telran.util.TcpClientHandler;

import static org.junit.jupiter.api.Assertions.*;
import static telran.util.Level.*;

public class TcpClientHandlerTest {
    static PrintStream output;
    static BufferedReader input;
    static Socket socket;
    static TcpClientHandler handler;
    static String response;

    @BeforeAll
    static void createConnection() throws Exception{
        socket = new Socket("localhost", ServerLogAppl.PORT);
        output = new PrintStream(socket.getOutputStream());
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        handler = new TcpClientHandler(socket, output, input);
    }
    @AfterAll
    static void closeConnection() throws Exception {
        handler.close();
    }


    @Test
    void logTest() throws Exception{
        output.println("log#" + getLoggerRecord(INFO));
        response = input.readLine();
        assertEquals("OK", response);
    }
    @Test
    void falseLogTest() throws Exception{
        output.println("log#" + getLoggerRecord(null));
        response = input.readLine();
        assertEquals("Wrong logger record", response);
    }
    @Test
    void counterTest() throws Exception{
        output.println("counter#error");
        response = input.readLine();
        assertEquals("0", response);
        output.println("counter#info");
        response = input.readLine();
        assertEquals("1", response);
    }

    private String getLoggerRecord(Level level) {
        LocalDateTime ldt = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
        return String.format("%s;%s;%s;%s\n", ldt, level,
                    "TestLogger", "Message");
    }

}
