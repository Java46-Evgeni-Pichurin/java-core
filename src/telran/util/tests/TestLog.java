package telran.util.tests;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import telran.util.Level;
import telran.util.Logger;
import telran.util.SimpleStreamHandler;

import java.io.*;

public class TestLog {
    File file = new File("file.txt");

    @BeforeEach
    void setUp() {
        file.delete();
        file = new File("file.txt");
    }

    @Test
    void errorLevelTest() throws Exception {
        try (PrintStream printWriter = new PrintStream("file.txt");
             BufferedReader reader = new BufferedReader(new FileReader("file.txt"));) {
            SimpleStreamHandler handler = new SimpleStreamHandler(printWriter);
            Logger logger = new Logger(handler, "Error Logger");
            logger.setLevel(Level.ERROR);
            logger.error("ERROR message");
            assertNotEquals("", reader.readLine());
        }
    }
}
