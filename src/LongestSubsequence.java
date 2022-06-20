import java.util.Arrays;

public class LongestSubsequence {
    public static int maxSubsequenceLength(int[] arr) {
       int counter = 0;
       if (arr.length == 0) return counter;
       if (arr.length == 1) return ++counter;
       int jumper = 1;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == arr[i + 1]) ++jumper;
            else jumper = 1;
            if (counter <= jumper) counter = jumper;
        }
        return counter;
    }

    public static int getRandomNum(){
        return (int)(Math.random() * 10);
    }
    public static void getTest(){
        int[] testArr = new int[getRandomNum()];
        if (testArr.length > 0) {
            for (int i = 0; i <= testArr.length - 1; i++) {
                testArr[i] = getRandomNum();
            }
        }
        System.out.println("Initial array " + Arrays.toString(testArr));
        System.out.println("The length of longest sub-sequence is " + maxSubsequenceLength(testArr) + "\n");
    }


    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            getTest();
        }
    }
}
