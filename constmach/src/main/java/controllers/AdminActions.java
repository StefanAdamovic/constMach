package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.user.DaoUser;
import models.user.User;
import models.user.UserType;
import validators.AgeValidator;
import validators.EmailValidator;
import validators.PasswordValidator;

@WebServlet("/AdminActions")
public class AdminActions extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminActions() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		String errMsg = "";

		if (action == null) {
			errMsg = "Failed to see all users. Try Again.";
			request.setAttribute("errMsg", errMsg);
			request.getRequestDispatcher("adminHome.jsp").forward(request, response);
			return;
		}

		if (action.equalsIgnoreCase("seeAllUsers")) {
			DaoUser du = DaoUser.getInstance();

			ArrayList<User> allusers = du.selectUsers();
			request.setAttribute("userList", allusers);
			request.getRequestDispatcher("adminUserList.jsp").forward(request, response);
		}

		System.out.println("Kraj geta:" + action);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		String errMsg = "";
		String userId = "";
		DaoUser du = DaoUser.getInstance();

		if (action == null) {
			errMsg = "Failed action. Try Again.";
			request.setAttribute("errMsg", errMsg);
			request.getRequestDispatcher("adminHome.jsp").forward(request, response);
			return;
		}

		if (action.equalsIgnoreCase("createUser") || action.equalsIgnoreCase("submitEditedUser")) {
			System.out.println("Usao u create ili submitEditeUser");
			if (action.equalsIgnoreCase("submitEditedUser")) {
				System.out.println("Usao submitEditeUser testiram ID, linija 74");

				userId = request.getParameter("userId");

				if (userId == null) {
					errMsg = "Error in editing user. Try Again.";
					request.setAttribute("errMsg", errMsg);
					ArrayList<User> allusers = du.selectUsers();
					request.setAttribute("userList", allusers);
					request.getRequestDispatcher("adminUserList.jsp").forward(request, response);
					return;
				} else if (userId.isEmpty() || userId.isBlank()) {
					errMsg = "Error in editing user. Try Again.";
					request.setAttribute("errMsg", errMsg);
					ArrayList<User> allusers = du.selectUsers();
					request.setAttribute("userList", allusers);
					request.getRequestDispatcher("adminUserList.jsp").forward(request, response);
					return;
				}

				System.out.println(userId + " user id na liniji 95");

			}

			String userType = request.getParameter("user-type");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String yearBorn = request.getParameter("yearBorn");

			System.out.println("Pre validacije linija 105");
			// validacija da li su parametri sa adminCreateUser null
			if (userType == null) {
				errMsg = "Please select user type.";
				request.setAttribute("errMsg", errMsg);
				request.getRequestDispatcher("adminCreateUser.jsp").forward(request, response);
			} else if (email == null) {
				errMsg = "Please enter email.";
				request.setAttribute("errMsg", errMsg);
				request.getRequestDispatcher("adminCreateUser.jsp").forward(request, response);
			} else if (password == null) {
				errMsg = "Please enter password";
				request.setAttribute("errMsg", errMsg);
				request.getRequestDispatcher("adminCreateUser.jsp").forward(request, response);
			} else if (firstName == null) {
				errMsg = "Please enter first name";
				request.setAttribute("errMsg", errMsg);
				request.getRequestDispatcher("adminCreateUser.jsp").forward(request, response);
			} else if (lastName == null) {
				errMsg = "Please enter last name";
				request.setAttribute("errMsg", errMsg);
				request.getRequestDispatcher("adminCreateUser.jsp").forward(request, response);
			} else if (yearBorn == null) {
				errMsg = "Please enter year of birth";
				request.setAttribute("errMsg", errMsg);
				request.getRequestDispatcher("adminCreateUser.jsp").forward(request, response);
			}

			System.out.println("Pre validacije linija 133");

			// validacija da li su parametri sa adminCreateUser prazni
			if (userType.isEmpty() || userType.isBlank()) {
				errMsg = "Please select user type.";
				request.setAttribute("errMsg", errMsg);
				request.getRequestDispatcher("adminCreateUser.jsp").forward(request, response);
			} else if (email.isEmpty() || email.isBlank()) {
				errMsg = "Please enter email.";
				request.setAttribute("errMsg", errMsg);
				request.getRequestDispatcher("adminCreateUser.jsp").forward(request, response);
			} else if (password.isEmpty() || password.isBlank()) {
				errMsg = "Please enter password";
				request.setAttribute("errMsg", errMsg);
				request.getRequestDispatcher("adminCreateUser.jsp").forward(request, response);
			} else if (firstName.isEmpty() || firstName.isBlank()) {
				errMsg = "Please enter first name";
				request.setAttribute("errMsg", errMsg);
				request.getRequestDispatcher("adminCreateUser.jsp").forward(request, response);
			} else if (lastName.isEmpty() || lastName.isBlank()) {
				errMsg = "Please enter last name";
				request.setAttribute("errMsg", errMsg);
				request.getRequestDispatcher("adminCreateUser.jsp").forward(request, response);
			} else if (yearBorn.isEmpty() || yearBorn.isBlank()) {
				errMsg = "Please enter year of birth";
				request.setAttribute("errMsg", errMsg);
				request.getRequestDispatcher("adminCreateUser.jsp").forward(request, response);
			}

			System.out.println("Pre validatora linija 162");

			if (!EmailValidator.patternCheck(email)) {
				errMsg = "Please enter email with valid pattern.";
				System.out.println(errMsg);
				request.setAttribute("errMsg", errMsg);
				request.getRequestDispatcher("adminCreateUser.jsp").forward(request, response);
				return;
			}

			if (!action.equalsIgnoreCase("submitEditedUser")) {
				if (EmailValidator.checkDb(email)) {
					errMsg = "There is account with this email";
					System.out.println(errMsg);
					request.setAttribute("errMsg", errMsg);
					request.getRequestDispatcher("adminCreateUser.jsp").forward(request, response);
					return;
				}
			}

			if (PasswordValidator.patternCheck(password)) {
				errMsg = "Please enter password with valid pattern.";
				System.out.println(errMsg);
				request.setAttribute("errMsg", errMsg);
				request.getRequestDispatcher("adminCreateUser.jsp").forward(request, response);
				return;
			}

			int yearB = Integer.parseInt(yearBorn);

			if (!AgeValidator.yearBornCheck(yearB)) {
				errMsg = "Please enter valid year of birth";
				request.setAttribute("errMsg", errMsg);
				request.getRequestDispatcher("adminCreateUser.jsp").forward(request, response);
				return;
			}
			System.out.println("Pre user null kreiranja linija 196");

			User user = null;

			if (UserType.WORKER.toString().equalsIgnoreCase(userType)) {
				user = new User(email, password, firstName, lastName, yearB, UserType.WORKER);
			}

			else if (!UserType.ADMIN.toString().equalsIgnoreCase(userType)) {
				user = new User(email, password, firstName, lastName, yearB, UserType.ADMIN);
			} else {
				errMsg = "Please try again UserType Error.";
				request.setAttribute("errMsgPassword", errMsg);
				request.getRequestDispatcher("adminCreateUser.jsp").forward(request, response);
				return;
			}

			if (action.equalsIgnoreCase("submitEditedUser")) {
				System.out.println("usao u submitEditedUser");
				int userIdValue = Integer.parseInt(userId);
				user.setId(userIdValue);
				System.out.println("User pre slanja na update: " + user);
				du.updateUser(user);
				errMsg = "Error in updating in DB. Try Again.";
				request.setAttribute("errMsg", errMsg);
				ArrayList<User> allusers = du.selectUsers();
				request.setAttribute("userList", allusers);
				request.getRequestDispatcher("adminUserList.jsp").forward(request, response);
				return;

			}

			du.insertUser(user);
			request.getRequestDispatcher("adminHome.jsp").forward(request, response);

			return;
			// kraj createUser
		}

		if (action.equalsIgnoreCase("deleteUser"))

		{

			userId = request.getParameter("userId");

			if (userId == null) {
				errMsg = "Error in deleting user. Try Again.";
				request.setAttribute("errMsg", errMsg);
				ArrayList<User> allusers = du.selectUsers();
				request.setAttribute("userList", allusers);
				request.getRequestDispatcher("adminUserList.jsp").forward(request, response);
				return;
			} else if (userId.isEmpty() || userId.isBlank()) {
				errMsg = "Error in deleting user. Try Again.";
				request.setAttribute("errMsg", errMsg);
				ArrayList<User> allusers = du.selectUsers();
				request.setAttribute("userList", allusers);
				request.getRequestDispatcher("adminUserList.jsp").forward(request, response);
				return;
			}

			int userIdValue = Integer.parseInt(userId);

			du.deleteUserByID(userIdValue);
			ArrayList<User> allusers = du.selectUsers();
			request.setAttribute("userList", allusers);
			request.getRequestDispatcher("adminUserList.jsp").forward(request, response);
			return;
			// kraj deleteUser
		}

		if (action.equalsIgnoreCase("editUser")) {

			System.out.println("usao u editUser");

			userId = request.getParameter("userId");

			if (userId == null) {
				errMsg = "Error in editing user. Try Again.";
				request.setAttribute("errMsg", errMsg);
				ArrayList<User> allusers = du.selectUsers();
				request.setAttribute("userList", allusers);
				request.getRequestDispatcher("adminUserList.jsp").forward(request, response);
				return;
			} else if (userId.isEmpty() || userId.isBlank()) {
				errMsg = "Error in editing user. Try Again.";
				request.setAttribute("errMsg", errMsg);
				ArrayList<User> allusers = du.selectUsers();
				request.setAttribute("userList", allusers);
				request.getRequestDispatcher("adminUserList.jsp").forward(request, response);
				return;
			}

			int userIdValue = Integer.parseInt(userId);
			User userEdit = null;
			userEdit = du.selectUserById(userIdValue);

			if (userEdit != null) {
				request.setAttribute("userEmail", userEdit.getEmail());
				request.setAttribute("userPassword", userEdit.getPassword());
				request.setAttribute("userFirstName", userEdit.getFirstName());
				request.setAttribute("userLastName", userEdit.getLastName());
				request.setAttribute("userYear", userEdit.getYearBorn());
				request.setAttribute("userId", userEdit.getId());
				request.getRequestDispatcher("adminEditUser.jsp").forward(request, response);
				return;
			} else {
				errMsg = "Error in editing user. Try Again.";
				ArrayList<User> allusers = du.selectUsers();
				request.setAttribute("userList", allusers);
				request.getRequestDispatcher("adminUserList.jsp").forward(request, response);
				
			}
		}
		// kraj edit usera koji prosledjuje na edit formu

	} // kraj posta

} // kraj servleta
