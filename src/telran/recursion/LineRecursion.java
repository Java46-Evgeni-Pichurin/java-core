package telran.recursion;

public class LineRecursion {

    /*-------------------------------------Homework--------------------------------------------------/
    /**
     * @param x number
     * @return x ^ 2
     */
    public static int square(int x) {
        if (x == 0) {
            return 0;
        }
        if (x < 0) {
            x = -x;
        }
        return x == 1 ? x : square(x - 1) + x + x - 1;
    }

    /**
     * @param a either negative or positive
     * @param b positive
     * @return a ^ b
     */
    public static long pow(int a, int b) {
        boolean isPositiveNumber = a > 0;
        boolean isOddPow = isOddNumber(b);
        switch (b) {
            case 0:
                return 1;
            case 1:
                return a;
            default:
                if (!isPositiveNumber && isOddPow) {
                    a = -a;
                    return -getPow(a, a, b - 2);
                }
                return getPow(a, a, b - 2);
        }
    }

    private static boolean isOddNumber(int b) {
        return switch (b) {
            case -1 -> true;
            case 0 -> false;
            default -> isOddNumber(b - 2);
        };
    }

    private static int getPow(int num, int sum, int pow) {
        /*-----------------------------------------------------------------------
        Example:
                sum     pow     sum     pow       sum      pow       sum      pow
        3^5 = (3+3+3) * 3^3 = (9+9+9) * 3^2 = (27+27+27) * 3^1 = (81+81+81) * 3^0
        -----------------------------------------------------------------------*/
        sum = getSum(sum, num);
        if (pow == 0) {
            return sum;
        }
        return getPow(num, sum, pow - 1);
    }

    private static int getSum(int a, int count) {
        if (count == 0) {
            return 0;
        }
        if (a < 0) {
            a = -a;
            count = -count;
        }
        return a + getSum(a, count - 1);
    }

    /*----------------------------------------------------------------------------------------------*/

    public static long factorial(int n) {
        if (n == 0) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    /**
     * @param ar - array of in integer numbers
     * @return sum of all numbers from the given array
     */
    public static int sum(int[] ar) {
        return sum(0, ar);
    }

    private static int sum(int firstIndex, int[] ar) {
        if (firstIndex == ar.length) {
            return 0;
        }
        return ar[firstIndex] + sum(firstIndex + 1, ar);
    }
}
