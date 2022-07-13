import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *  This example demonstrates how file names could be selected 
 *  by different criteria: Location, Date, Period of time, type of file -
 *  using Regular exception
 */
public class PhotoSelector {
	/*
	 * Full list of picture file Names for selection in format:
	 * folder\\YYYYMMDD_hhmmss.ext, where:
	 *    folder - name of folder equal to location where picture was taken
	 *    YYYY, MM, DD - year, month, day
	 *    hh,mm,ss - hours, minutes, seconds
	 */
	private static final String pictures[] = {
		"Paris\\20140101_090000.jpg",
		"Paris\\20140201_121005.jpg",				
		"Paris\\20150301_211035.jpg",				
		"Paris\\20150401_110023.gif",
		"Paris\\20150401_181705.jpg",				
		"Paris\\20150501_231035.gif",				
		"London\\20140205_090000.jpg",
		"London\\20140205_121005.jpg",				
		"London\\20140601_211035.gif",				
		"London\\20151001_110023.jpg",
		"London\\20151001_121705.jpg",				
		"London\\20151001_231035.jpg",
		"Chicago\\20150301_120001.png",
		"Chicago\\20151111_232000.png"
	};

	/**
	 * Prints list of picture file Names due to filtering criterion
	 * @param regex The filtering criterion 
	 */
	static void selectPictures(String title, String regex) {
		System.out.printf("%n%s%n------------------------%n", title);
        Pattern pattern = Pattern.compile(regex);
        for (String picture: pictures) {
        	if (pattern.matcher(picture).find()) {
        		System.out.println(picture);
        	}
        }
	}
	
	/**
	 * Selects pictures due to regular expression typed by user
	 */
	private static void selectCustom() {
		Scanner scanner = new Scanner(System.in);
		while(true){					
        	System.out.printf("%n=====>Enter your regex:");
        	String regex = scanner.nextLine();
        	if (regex.isEmpty()) 
        		break;
        	selectPictures("Custom:" + regex, regex);
		}
		scanner.close();
		System.out.println("\n=====>Good bye");
	}
	
	/**
	 * Testing example
	 */
	public static void main(String[] args){
		
		/*======== Uncomment this test to work in dialog mode ======*/
		selectCustom();
		
		// Test 1. All London pictures
		selectPictures("All London pictures", "London");

		// ====> TODO: Write the following tests ==========
		// Test 2. All Europe pictures
		selectPictures("All Europe pictures", "^[^Chicago]");
		// Test 3. All autumn pictures
		selectPictures("All autumn pictures", "2\\d{3}(09|10|11).*");
		// Test 4  All 2015 spring pictures
		selectPictures("All 2015 spring pictures", "20150[3-5].*");
		// Test 5. All night pictures (from 18:00 till 24:00)
		selectPictures("All night pictures (from 18:00 till 24:00)", "_((1[8-9])|(2[0-4])).*");
		// Test 6. All night pictures from Chicago
		selectPictures("All night pictures from Chicago", "^Chicago.*_((1[8-9])|(2[0-4])).*");
		// Test 7. All jpg and png pictures
		selectPictures(" All jpg and png pictures", ".(jpg|png)$");
		// Test 8* All pictures taken in dates with equal number of month and day
		selectPictures("All pictures taken in dates with equal number of month and day", "2\\d{3}" +
				"(((01){2})|((02){2})" +
				"|((03){2})|((04){2})|((05){2})|((06){2})" +
				"|((07){2})|((08){2})|((09){2})|((10){2})" +
				"|((11){2})|((12){2}))" +
				".*");
	}
}