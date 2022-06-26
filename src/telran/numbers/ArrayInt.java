package telran.numbers;

import java.util.Arrays;

public class ArrayInt {
    public static int[] addNumber(int[] ar, int number) {
        int[] res = Arrays.copyOf(ar, ar.length + 1);
        res[ar.length] = number;
        return res;

    }

    public static int[] insertNumber(int[] ar, int index, int number) {
        int[] res = new int[ar.length + 1];
        System.arraycopy(ar, 0, res, 0, index);
        res[index] = number;
        System.arraycopy(ar, index, res, index + 1, ar.length - index);
        return res;
    }

    public static int[] removeNumber(int[] ar, int index) {
        int[] res = new int[ar.length - 1];
        System.arraycopy(ar, 0, res, 0, index);
        System.arraycopy(ar, index + 1, res, index, res.length - index);
        return res;

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

    public static void sort(int[] ar) {
        for (int i = ar.length - 1; i >= 1; i--) {
            moveMaxToEnd(ar, i);
        }
    }

    private static void moveMaxToEnd(int[] ar, int i) {
        for (int j = 0; j < i; j++) {
            if (ar[j] > ar[j + 1]) {
                swap(ar, j);
            }
        }
    }

    private static void swap(int[] ar, int index) {
        int tmp = ar[index + 1];
        ar[index + 1] = ar[index];
        ar[index] = tmp;

    }

    public static int binaryIndexOf(int[] ar, int number) {
        int left = 0;
        int right = ar.length - 1;
        int middle = ar.length / 2;
        while (middle > 0 && ar[middle] == ar[middle - 1]) middle--;
        while (left <= right && ar[middle] != number) {
            if (ar[middle] < number) {
                left = middle + 1; //looking for the number will be in right part of the array
            } else {
                right = middle - 1; //looking for the number will be in the left part of the array
            }
            middle = (left + right) / 2;
        }
        if (left > right) {
            if (middle == 0) {
                middle = ar[middle] < number ? -2 : -1;
            } else if (left >= 1) middle = -middle - 2;
        }
        return middle;
    }
}
