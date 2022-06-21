package telran.numbers;

import java.util.Arrays;

public class ArrayInt {
    public static int[] addNumber(int[] ar, int number) {
        int[] arCopy = Arrays.copyOf(ar, ar.length + 1);
        arCopy[arCopy.length - 1] = number;
        return arCopy;
    }

    public static int[] insertNumber(int[] ar, int index, int number) {
        if (index > ar.length) index = ar.length;
        if (index < 0) index = 0;
        int[] destArray = new int[ar.length + 1];
        System.arraycopy(ar, 0, destArray, 0, index); // Copy elements to the left side of destination array
        destArray[index] = number; // Insert number into destination array
        System.arraycopy(ar, index, destArray, index + 1, ar.length - index); // Copy elements to the right side of destination array
        return destArray;
    }

    public static int[] removeNumber(int[] ar, int index) {
        if (index >= ar.length) index = ar.length - 1;
        if (index < 0) index = 0;
        int[] destArray = new int[ar.length - 1];
        System.arraycopy(ar, 0, destArray, 0, index); // Copy elements to the left side of destination array
        System.arraycopy(ar, index + 1, destArray, index, ar.length - index - 1); // Copy elements to the right side of destination array
        return destArray;
    }

    public static int indexOf(int[] ar, int number) {
        int res = -1;
        for (int i = 0; i < ar.length; i++) {
            if (ar[i] == number) {
                res = i;
                break;
            }
        }
        return res;
    }
}
