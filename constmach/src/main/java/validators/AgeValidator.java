package validators;

import java.time.Year;

public class AgeValidator {
	
	public static boolean yearBornCheck(int year) {
		Year y = Year.now();
		int yearAtm = y.getValue();
		
		if(year >= 1930 && year <= (yearAtm - 18)) {
			return true;
		} else		
			return false;
	}

}
