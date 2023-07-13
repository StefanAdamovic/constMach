package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.machine.DaoDtoMachine;
import dao.machine.DaoMachine;
import dto.DtoRentMachine;
import models.machine.Machine;
import models.user.User;

@WebServlet("/pages")
public class Pages extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String WORKER_HOME_PAGE = "workerHome.jsp";
	private static final String WORKER_RENT_PAGE = "workerViewRent.jsp";
	private static final String LOGIN_PAGE = "login.jsp";

	public Pages() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String page = request.getParameter("page");
		DaoDtoMachine ddm = DaoDtoMachine.getInstance();
		DaoMachine dm = DaoMachine.getInstance();
		User user = (User) request.getSession().getAttribute("user");

		if (user == null) {
			request.getSession().invalidate();
			response.sendRedirect(LOGIN_PAGE);
		}
		
		if (page == null) {
			request.getSession().invalidate();
			response.sendRedirect(LOGIN_PAGE);
		}

		if (page.equals("home")) {

			
			
			ArrayList<DtoRentMachine> user_history = ddm.selectDtoMachinesByUser(user.getId());
			request.setAttribute("user_history", user_history);
			request.getRequestDispatcher(WORKER_HOME_PAGE).forward(request, response);
		}

		if (page.equals("rent")) {
			
			ArrayList<Machine> rent_list = dm.selectMachines();
			request.setAttribute("rent_list", rent_list);
			request.getRequestDispatcher(WORKER_RENT_PAGE).forward(request, response);
		}

		if (page.equals("logout")) {
			request.getSession().invalidate();
			response.sendRedirect(LOGIN_PAGE);
		}

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
