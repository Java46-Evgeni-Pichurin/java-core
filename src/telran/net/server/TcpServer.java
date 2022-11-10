package telran.net.server;

import telran.net.common.ApplProtocol;

import java.net.*;

public class TcpServer implements Runnable {
    private final ServerSocket serverSocket;
    private final int port;
    private final ApplProtocol protocol;

    public TcpServer(int port, ApplProtocol protocol) throws Exception {
        this.port = port;
        this.protocol = protocol;
        serverSocket = new ServerSocket(port);
    }

    @Override
    public void run() {
        System.out.println("Server is listening on the port " + port);
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Accept client: " + socket.getRemoteSocketAddress());
                TcpClientServer clientServer = new TcpClientServer(socket, protocol);
                clientServer.run();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}