package telran.recursion;

public class LineRecursion {

    /*-------------------------------------Homework--------------------------------------------------*/
    public static int square(int x) {
        if (x == 0) {
            return 0;
        }
        if (x < 0) {
            x = -x;
        }
        return x == 1 ? x : square(x - 1) + x + x - 1;
    }

    public static long pow(int a, int b) {
        boolean isNegativeNumber = a < 0;
        boolean isOddPow = isOddNumber(b);
        switch (b) {
            case 0:
                return 1;
            case 1:
                return a;
            case 2:
                return square(a);
            default:
                if (isNegativeNumber && isOddPow) {
                    return -getPow(-a, -a, b);
                }
                return isNegativeNumber ? getPow(-a, -a, b) : getPow(a, a, b);
        }
    }

    private static boolean isOddNumber(int b) {
        return switch (b) {
            case -1 -> true;
            case 0 -> false;
            default -> isOddNumber(b - 2);
        };
    }

    private static long getPow(long num, long initNum, int pow) {
        if (pow == 1) {
            return num;
        }
        return getPow(multiply(num, initNum), initNum, pow - 1);
    }

    private static long multiply(long a, long b) {
        if (a == 0 || b == 0) {
            return 0;
        }
        return a + multiply(a, b - 1);
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
