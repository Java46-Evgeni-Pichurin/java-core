package telran.net.calculator.serverSide;

import telran.net.calculator.common.Calculator;
import telran.net.common.*;

import java.io.Serializable;

import static telran.net.calculator.common.CalculatorApi.*;

public class CalculatorProtocol implements ApplProtocol {
    Calculator calculator;

    public CalculatorProtocol(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public Response getResponse(Request request) {
        Response response;
        try {
            response = switch (request.requestType) {
                case CMD_ADD -> add(getArguments(request.requestData));
                case CMD_SUBTRACT -> subtract(getArguments(request.requestData));
                case CMD_DIVIDE -> divide(getArguments(request.requestData));
                case CMD_MULTIPLY -> multiply(getArguments(request.requestData));
                default -> new Response(ResponseCode.WRONG_REQUEST_TYPE, request.requestType);
            };
        } catch (Exception e) {
            response = new Response(ResponseCode.WRONG_REQUEST_DATA, e.getMessage());
        }
        return response;
    }


    //Data verification on server side
    private double[] getArguments(Serializable requestData) throws Exception {
        try {
            double[] res = (double[]) requestData;
            if (res.length != 2) {
                throw new Exception("No two operands. ");
            }
            return res;
        } catch (ClassCastException e) {
            throw new Exception("No array of doubles. ");
        }
    }

    Response add(double[] operands) {
        double res = calculator.add(operands[0], operands[1]);
        return new Response(ResponseCode.OK, res);
    }

    Response subtract(double[] operands) {
        double res = calculator.subtract(operands[0], operands[1]);
        return new Response(ResponseCode.OK, res);
    }

    Response divide(double[] operands) {
        double res = calculator.divide(operands[0], operands[1]);
        return new Response(ResponseCode.OK, res);
    }

    Response multiply(double[] operands) {
        double res = calculator.multiply(operands[0], operands[1]);
        return new Response(ResponseCode.OK, res);
    }
}