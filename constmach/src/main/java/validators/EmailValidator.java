package validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.user.DaoUser;
import models.user.User;

public class EmailValidator {

	private static final String REGEX = "^[\\w!#$%&amp;'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&amp;'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

	public static boolean patternCheck(String email) {
		Pattern pattern = Pattern.compile(REGEX);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public static boolean checkDb(String email) {

		DaoUser du = DaoUser.getInstance();

		User user = du.selectUserByEmail(email);

		if (user == null)
			return false;

		if (user.getEmail().equals(email))
			return true;

		return false;
	}

}
