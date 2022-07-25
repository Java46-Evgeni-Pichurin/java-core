public class Lottery {
    private int[] winningCombination;

    public Lottery(int[] winningCombination) {
        this.winningCombination = winningCombination;
    }

    public int rateCombination(int[] playerCombination) {
        int rate = 0;
        int[] lookUpTable = new int[50];
        for (int j : winningCombination) {
            lookUpTable[j]++;
        }
        for (int j : playerCombination) {
            if (lookUpTable[j] > 0) {
                rate++;
            }
        }
        return rate;
    }

    public static void main(String[] args) {
        Lottery loto = new Lottery(new int[]{5, 2, 17, 48, 43, 7, 9});
        if (loto.rateCombination(new int[]{3, 5, 12, 17, 44, 10, 7}) != 3) {
            System.out.println("Test 1 failed");
            return;
        }
        if (loto.rateCombination(new int[]{3, 5, 12, 17, 49, 9, 7}) != 4) {
            System.out.println("Test 2 failed");
            return;
        }
        if (loto.rateCombination(new int[]{5, 2, 17, 48, 43, 7, 9}) != 7) {
            System.out.println("Test 3 failed");
            return;
        }
        if (loto.rateCombination(new int[]{1, 3, 18, 44, 45, 8, 10}) != 0) {
            System.out.println("Test 4 failed");
            return;
        }
        System.out.println("Success");
    }
}
