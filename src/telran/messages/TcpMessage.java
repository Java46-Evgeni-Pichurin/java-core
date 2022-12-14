package telran.messages;

public class TcpMessage implements Message {
    String ipPort; //ipv4:port

    public TcpMessage(String ipPort) {
        this.ipPort = ipPort;
    }

    @Override
    public void send(String text) {
        System.out.printf("tcp message '%s' has been sent to ip:port %s\n", text, ipPort);
    }
}
