package telran.multithreading;


import java.util.ArrayList;

public class PrinterControllerAppl {
    private static final int N_NUMBERS = 100;
    private static final int N_PORTIONS = 10;
    private static final int N_PRINTERS = 4;

    public static void main(String[] args) {
        int[] argsAr;
        try {
            argsAr = getArgs(args);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        ArrayList<Printer> list = new ArrayList<>();
        createChain(list, argsAr[0], argsAr[1], argsAr[2]);
        list.get(0).start();
        list.get(0).interrupt();
    }

    private static void createChain(ArrayList<Printer> list, int numbers, int portions, int printers) {
        for (int i = 0; i < printers; i++) {
            list.add(new Printer(numbers, portions, i));
        }
        for (int j = 0; j < list.size(); j++) {
            if (j == list.size() - 1) list.get(j).connectTo(list.get(0));
            else list.get(j).connectTo(list.get(j + 1));
        }
    }

    private static int[] getArgs(String[] args) throws Exception {
        int[] res = new int[3];
        if (args.length < 3) {
            res[0] = N_NUMBERS;
            res[1] = N_PORTIONS;
            res[2] = N_PRINTERS;
        }
        else {
            try {
                res[0] = Integer.parseInt(args[0]);
                res[1] = Integer.parseInt(args[1]);
                res[2] = Integer.parseInt(args[2]);
            } catch (Exception e) {
                throw new Exception(e.getMessage() + " " + e.getCause());
            }
        }
        return res;
    }
}
