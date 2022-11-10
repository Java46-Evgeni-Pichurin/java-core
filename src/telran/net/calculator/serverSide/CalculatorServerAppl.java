package telran.net.calculator.serverSide;

import telran.net.server.TcpServer;

import static telran.net.calculator.common.CalculatorApi.PORT;

public class CalculatorServerAppl {

    public static void main(String[] args) {
        try {
            TcpServer server = new TcpServer(PORT, new CalculatorProtocol(new CalculatorImpl()));
            server.run();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}