package validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.user.DaoUser;
import models.user.User;

public class PasswordValidator {
	// TODO regex odradi
	private static final String REGEX = "";

	public static boolean patternCheck(String password) {

		Pattern pattern = Pattern.compile(REGEX);
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}

	public static boolean checkDb(String email, String password) {

		DaoUser du = DaoUser.getInstance();

		User user = du.selectUserByEmail(email);

		if (user == null)
			return false;

		if (!user.getEmail().equals(email))
			return false;

		if (user.getPassword().equals(password))
			return true;

		return false;
	}

}
