package telran.net.server;

import telran.net.common.ApplProtocol;

import java.net.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TcpServer implements Runnable {
    private static final int ACCEPT_TIME_OUT = 100;
    private static final int MIN_POOL_SIZE = 3;
    private static final int MAX_POOL_SIZE = 3;
    private static final long ALIVE_TIME = 5;
    protected final ThreadPoolExecutor executor;
    volatile boolean isShutdown = false;
    private final ServerSocket serverSocket;
    private final int port;
    private final ApplProtocol protocol;
    protected AtomicInteger threadCount;

    public TcpServer(int port, ApplProtocol protocol) throws Exception {
        this.port = port;
        this.protocol = protocol;
        serverSocket = new ServerSocket(port);
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(MAX_POOL_SIZE);
        executor = new ThreadPoolExecutor(MIN_POOL_SIZE, MAX_POOL_SIZE, ALIVE_TIME, TimeUnit.SECONDS, workQueue);
        serverSocket.setSoTimeout(ACCEPT_TIME_OUT);
        threadCount = new AtomicInteger(0);
    }

    @Override
    public void run() {
        System.out.println("Server is listening on the port " + port);
        while (!isShutdown) {
            try {
                Socket socket = serverSocket.accept();
                threadCount.incrementAndGet();
                System.out.println("Accept client: " + socket.getRemoteSocketAddress());
                TcpClientSession clientServer = new TcpClientSession(socket, protocol, this);
                //executor.allowCoreThreadTimeOut(true);
                executor.execute(clientServer);
            } catch (SocketTimeoutException e) {

            } catch (Exception e) {
                System.out.println(e.getMessage());
                break;
            }
        }
    }

    public void shutdown() {
        isShutdown = true;
        executor.shutdown();
    }
}