package telran.numbers;
/**
 * 
 * @author User
 * application should print out 7 random numbers in the range [1-49]
 */
public class SportLotoAppl {

	public static void main(String[] args) {
		for (int i = 0; i < 7; i++) {
			System.out.printf(getRandomNumber(1,49)+" ");
		}

	}
	//helper method for getting one random number in the range [min-max]
	private static int getRandomNumber(int min, int max) {
		return (int) (min + Math.random() * (max - min + 1));
	}

}
