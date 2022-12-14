package telran.messages;

public class TcpMessage implements Message {
    String ipPort; //ipv4:port

    public TcpMessage(String ipPort) {
        if (ipPort.matches("[1-2][0-4]?\\d?.[0-2][0-5]?[0-5]?.[0-2][0-5]?[0-5]?.[0-2][0-5]?[0-5]?:\\d{2}\\d?\\d?\\d?")) {
            this.ipPort = ipPort;
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public void send(String text) {
        System.out.printf("tcp message '%s' has been sent to ip:port %s\n", text, ipPort);
    }
}
