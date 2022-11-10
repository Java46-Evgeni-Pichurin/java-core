package telran.net.calculator.clientSide;

import telran.net.calculator.common.Calculator;
import telran.net.client.NetworkHandler;

import java.io.*;

public class NetCalculatorProxy implements Calculator, Closeable {
    private final NetworkHandler handler;

    public NetCalculatorProxy(NetworkHandler handler) {
        this.handler = handler;
    }

    @Override
    public void close() throws IOException {
        handler.close();
    }

    @Override
    public double add(double op1, double op2) {
        return handler.send("add", new double[]{op1, op2});
    }

    @Override
    public double subtract(double op1, double op2) {
        return handler.send("subtract", new double[]{op1, op2});
    }

    @Override
    public double divide(double op1, double op2) {
        return handler.send("divide", new double[]{op1, op2});
    }

    @Override
    public double multiply(double op1, double op2) {
        return handler.send("multiply", new double[]{op1, op2});
    }
}