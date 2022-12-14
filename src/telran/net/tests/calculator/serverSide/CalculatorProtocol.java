package telran.net.tests.calculator.serverSide;

import telran.net.tests.calculator.common.Calculator;
import telran.net.common.*;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

import static telran.net.tests.calculator.common.CalculatorApi.*;

public class CalculatorProtocol implements ApplProtocol {
    Calculator calculator;

    public CalculatorProtocol(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public Response getResponse(Request request) {
        Response response;
        try {
            double[] arguments = getArguments(request.requestData);
            response = (Response) this.getClass().getDeclaredMethod(request.requestType, double[].class).invoke(this, arguments);

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