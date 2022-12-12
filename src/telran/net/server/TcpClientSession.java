package telran.net.server;

import telran.net.common.ApplProtocol;
import telran.net.common.Request;
import telran.net.common.Response;

import java.net.*;
import java.io.*;

public class TcpClientSession implements Runnable {
    private static final int CLIENT_TIMEOUT = 1_000;
    private static final int CLIENT_IDLE_TIMEOUT = 20_000;
    private final Socket socket;
    private final ObjectInputStream input;
    private final ObjectOutputStream output;
    private final ApplProtocol protocol;
    private final TcpServer tcpServer;

    public TcpClientSession(Socket socket, ApplProtocol protocol, TcpServer tcpServer) throws Exception {
        this.protocol = protocol;
        this.socket = socket;
        this.socket.setSoTimeout(CLIENT_TIMEOUT);
        this.tcpServer = tcpServer;
        input = new ObjectInputStream(socket.getInputStream());
        output = new ObjectOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        long idlePeriod = 0;
        while (!tcpServer.isShutdown) {
            try {
                Request request = (Request) input.readObject();
                idlePeriod = 0;
                Response response = protocol.getResponse(request);
                output.writeObject(response);
                output.reset();
            } catch (SocketTimeoutException e) {
                if ((idlePeriod += CLIENT_TIMEOUT) == CLIENT_IDLE_TIMEOUT &&
                    tcpServer.threadCount.get() > tcpServer.executor.getLargestPoolSize()) {
                    System.out.println("Client connection closed by time out for " + socket.getRemoteSocketAddress());
                    try {
                        socket.close();
                    } catch (IOException ioe) {}
                    break;
                }
            } catch (EOFException e) {
                System.out.println("Client: " + socket.getRemoteSocketAddress() + " closed connection. ");
                break;
            } catch (Exception e) {
                System.out.println("Abnormal closing connection. " + e.getMessage());
                break;
            }
        }
        if (tcpServer.isShutdown) {
            System.out.println("Client connection closed by server shutdown for " + socket.getRemoteSocketAddress());
            try {
                socket.close();
            } catch (IOException ioe) {}
        }
        tcpServer.threadCount.decrementAndGet();
        System.out.println("Current number of threads: " + tcpServer.threadCount.get());
    }
}
