package telran.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;

public class StackInt {
    private Integer[] arr;
    private int curMaxValue;
    private int prevMaxValue;
    private int lastIndex;
    private int capacity;

    public StackInt() {
        arr = new Integer[0];
        lastIndex = -1;
        capacity = 1;
    }

    public int pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        if (arr[lastIndex] == curMaxValue) {
            curMaxValue = prevMaxValue;
        }
        int removedElement = arr[lastIndex--];
        arr[lastIndex + 1] = null;
        return removedElement;
    }

    public void push(int number) {
        if (lastIndex >= 0 && number >= curMaxValue) {
            prevMaxValue = curMaxValue;
        }
        curMaxValue = isEmpty() ? number : Math.max(curMaxValue, number);
        if (isEmpty()) {
            arr = Arrays.copyOf(arr, capacity);
            arr[++lastIndex] = number;
        } else {
            lastIndex++;
            if (lastIndex == arr.length) {
                capacity *= 2;
                arr = Arrays.copyOf(arr, capacity);
            }
            arr[lastIndex] = number;
        }
    }

    public boolean isEmpty() {
        return lastIndex == -1;
    }

    public int getMaxNumber() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return curMaxValue;
    }

    public int getSize() {
        return lastIndex + 1;
    }
}
