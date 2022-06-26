package telran.numbers;
/**
 * 
 * @author User
 * application should print out 7 random numbers in the range [1-49]
 */
public class SportLotoAppl {

	private static final int N_NUMBERS = 7;
	private static final int MIN_NUMBER = 1;
	private static final int MAX_NUMBER = 49;
	public static void main(String[] args) {
		int numbers[] = new int[N_NUMBERS];
		for (int i = 0; i < N_NUMBERS; i++) {
			int number = getUniqueNumber(numbers, MIN_NUMBER, MAX_NUMBER);
			System.out.print(number + " ");
			numbers[i] = number;
		}
		

	}
	private static int getUniqueNumber(int[] ar, int minNumber, int maxNumber) {
		int res = 0;
		do {
			res = getRandomNumber(minNumber, maxNumber);
		} while(ArrayInt.indexOf(ar, res) >= 0);
		return res;
	}
	//helper method for getting one random number in the range [min-max]
	private static int getRandomNumber(int min, int max) {
		return (int) (min + Math.random() * (max - min + 1));
	}

}
