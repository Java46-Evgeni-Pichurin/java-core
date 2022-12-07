package telran.net.tests.calculator.clientSide;

import telran.net.client.TcpHandler;
import telran.view.ConsoleInputOutput;
import telran.view.menu.Menu;

import java.util.stream.IntStream;

import static telran.net.tests.calculator.common.CalculatorApi.PORT;

public class CalculatorAppl {
    public static void main(String[] args) {
        try (
                TcpHandler handler = new TcpHandler("localhost", PORT);
                NetCalculatorProxy proxy = new NetCalculatorProxy(handler)
        ) {
            Menu menu = new Menu("Main menu", CalculatorMenu.getCalculatorItems(proxy));
            menu.perform(new ConsoleInputOutput());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}