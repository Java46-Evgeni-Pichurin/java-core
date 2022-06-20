package A02method;

public class NumberConvertor {
    public static String toBinaryStr(int number) {
        if (number < 0) number = 0;
        StringBuilder binaryString = new StringBuilder();
        do {
            binaryString.append(number % 2);
            number /= 2;
        }while (number > 0);
        return binaryString.reverse().toString();
    }
}
