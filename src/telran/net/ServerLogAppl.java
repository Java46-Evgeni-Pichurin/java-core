package telran.net;

import telran.util.Level;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.stream.Collectors;

import static telran.util.Level.*;

public class ServerLogAppl {
    public static int PORT = 3000;
    private static final HashMap<Level, Integer> loggerMap = new HashMap<>() {{
        put(TRACE, 0);
        put(WARN, 0);
        put(INFO, 0);
        put(DEBUG, 0);
        put(ERROR, 0);
    }};
    private static Collection<String> values = Arrays.stream(values()).map(Enum::toString).toList();

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server is listening to connections on port " + PORT);
        while (true) {
            Socket socket = serverSocket.accept();
            runProtocol(socket);
        }
    }

    private static void runProtocol(Socket socket) {
        try {
            BufferedReader input =
                    new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintStream output = new PrintStream(socket.getOutputStream());
            while (true) {
                String request = input.readLine();
                if (request == null) {
                    break;
                }
                String response = getResponse(request);
                output.println(response);
            }
        } catch (IOException e) {
            System.out.println("client has closed connection improperly");
        }
    }

    private static String getResponse(String request) {
        String[] tokens = request.split("#");
        String response = "";
        if (tokens.length != 2) {
            response = "Wrong request: should be <type>#<string";
        } else if (tokens[0].equals("log")) {
            String[] data = tokens[1].split(";");
            if (data.length == 4) {
                response = fillMap(data[1]) ? "OK" : "Wrong logger record";
            } else response = "Wrong logger record";
        } else if (tokens[0].equals("counter")) {
            String level = tokens[1].toUpperCase();
            if (!values.contains(level)) {
                response = "Wrong Level";
            } else {
                response = loggerMap.get(Level.valueOf(level)).toString();
            }
        } else {
            response = "Wrong request type: should be either 'log' or 'counter'";
        }
        return response;
    }

    private static boolean fillMap(String curLevel) {
        int index;
        String level = curLevel.toUpperCase();
        if (!values.contains(level)) {
            return false;
        }
        index = Level.valueOf(level).ordinal();
        Integer res = loggerMap.get(Level.values()[index]);
        loggerMap.put((values()[index]), res + 1);
        printMsg();
        return true;
    }

    private static void printMsg() {
        System.out.println("********************************************");
        System.out.println("*********LOGGER MAP WAS CHANGED*************");
        System.out.println(loggerMap);
        System.out.println("********************************************");
    }
}