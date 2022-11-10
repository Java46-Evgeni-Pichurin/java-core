package telran.net.server;

import telran.net.common.ApplProtocol;
import telran.net.common.Request;
import telran.net.common.Response;

import java.net.*;
import java.io.*;

public class TcpClientServer implements Runnable {
    private final Socket socket;
    private final ApplProtocol protocol;

    public TcpClientServer(Socket socket, ApplProtocol protocol) {
        this.protocol = protocol;
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                socket;
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream())
        ) {
            while (true) {
                Request request = (Request) input.readObject();
                Response response = protocol.getResponse(request);
                output.writeObject(response);
                output.reset();
            }
        } catch (EOFException e) {
            System.out.println("Client closed connection. ");
        } catch (Exception e) {
            System.out.println("Abnormal closing connection. " + e.getMessage());
        }
    }
}
