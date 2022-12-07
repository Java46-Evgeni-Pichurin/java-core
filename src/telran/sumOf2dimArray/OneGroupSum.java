package telran.sumOf2dimArray;

import java.util.Arrays;

public class OneGroupSum implements Runnable {
    private final int[] group;
    private Long res;

    public OneGroupSum(int[] group) {
        this.group = group;
    }

    public Long getRes() {
        return res;
    }

    @Override
    public void run() {
        res = (long) Arrays.stream(group).sum();
    }
}
