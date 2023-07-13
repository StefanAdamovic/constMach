package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.machine.DaoDtoMachine;

import dao.user.DaoUser;
import dto.DtoRentMachine;
import models.user.User;
import models.user.UserType;
import validators.EmailValidator;
import validators.PasswordValidator;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static String workerHomeRedirect = "workerHome.jsp";
	private static String adminHomeRedirect = "adminHome.jsp";
	private static String loginRedirect = "login.jsp";

	public Login() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String errMsgEmail = "jbg";
		String errMsgPassword = "jbg";

		DaoUser du = DaoUser.getInstance();
		User user = null;

		if (email == null) {
			errMsgEmail = "Please enter email.";
			request.setAttribute("errMsgEmail", errMsgEmail);
			request.getRequestDispatcher(loginRedirect).forward(request, response);
		}

		if (password == null) {
			errMsgPassword = "Please enter password.";
			request.setAttribute("errMsgPassword", errMsgPassword);
			request.getRequestDispatcher(loginRedirect).forward(request, response);
		}

		if (email.isEmpty() && email.isBlank()) {
			errMsgEmail = "Please enter email.";
			request.setAttribute("errMsgEmail", errMsgEmail);
			request.getRequestDispatcher(loginRedirect).forward(request, response);
		}

		if (password.isEmpty() && password.isBlank()) {
			errMsgPassword = "Please enter email.";
			request.setAttribute("errMsgPassword", errMsgPassword);
			request.getRequestDispatcher(loginRedirect).forward(request, response);
		}

		if (!EmailValidator.patternCheck(email)) {
			errMsgEmail = "Please enter email with valid pattern.";
			System.out.println(errMsgEmail);
			request.setAttribute("errMsgEmail", errMsgEmail);
			request.getRequestDispatcher(loginRedirect).forward(request, response);
		}

		if (PasswordValidator.patternCheck(password)) {
			errMsgPassword = "Please enter password with valid pattern.";
			System.out.println(errMsgPassword);
			request.setAttribute("errMsgPassword", errMsgPassword);
			request.getRequestDispatcher(loginRedirect).forward(request, response);
		}

		if (!EmailValidator.checkDb(email)) {
			errMsgEmail = "Contact your Administrator. There is no account with this email";
			System.out.println(errMsgEmail);
			request.setAttribute("errMsgEmail", errMsgEmail);
			request.getRequestDispatcher(loginRedirect).forward(request, response);
		}

		if (!PasswordValidator.checkDb(email, password)) {
			errMsgPassword = "Password is not correct.";
			System.out.println(errMsgPassword);
			request.setAttribute("errMsgPassword", errMsgPassword);
			request.getRequestDispatcher(loginRedirect).forward(request, response);
		}

		// proso sve validacije uzimam usera iz baze
		user = du.selectUserByEmail(email);

		request.getSession().setAttribute("user", user);

		DaoDtoMachine ddm = DaoDtoMachine.getInstance();

		ArrayList<DtoRentMachine> user_history = ddm.selectDtoMachinesByUser(user.getId());

		request.setAttribute("user_history", user_history);
		//System.out.println("Posle stavljanja atributa: " + user_history);

		if (user.getType().equals(UserType.ADMIN)) {
			// response.sendRedirect(adminHomeRedirect);
			request.getRequestDispatcher(adminHomeRedirect).forward(request, response);
		}

		if (user.getType().equals(UserType.WORKER)) {
			// response.sendRedirect(workerHomeRedirect);
			request.getRequestDispatcher(workerHomeRedirect).forward(request, response);
		}
	}

}
