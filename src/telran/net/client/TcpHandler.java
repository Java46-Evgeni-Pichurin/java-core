package telran.net.client;

import telran.net.common.Request;
import telran.net.common.Response;
import telran.net.common.ResponseCode;

import java.io.*;
import java.net.*;

public class TcpHandler implements NetworkHandler {
    private final String hostname;
    private final int port;
    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;

    public TcpHandler(String hostname, int port) throws Exception {
        this.hostname = hostname;
        this.port = port;
        connect();
    }

    private void connect() {
        try {
            socket = new Socket(hostname, port);
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T send(String requestType, Serializable requestData) {
        Request request = new Request(requestType, requestData);
        boolean running = false;
        Response response = null;
        do {
            running = false;
            try {
                output.writeObject(request);
                response = (Response) input.readObject();
                if (response.code != ResponseCode.OK) {
                    throw new Exception(response.responseData.toString());
                }

            } catch (Exception e) {
                if (e.getMessage().contains("host")) {
                    running = true;
                    connect();
                } else {
                    throw new RuntimeException(e.getMessage());
                }
            }
        } while (running);
        return (T) response.responseData;
    }

    @Override
    public void close() throws IOException {
        socket.close();
    }
}