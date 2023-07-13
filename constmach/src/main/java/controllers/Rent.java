package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.machine.DaoDtoMachine;
import dao.machine.DaoMachine;
import models.machine.Machine;
import models.user.User;

@WebServlet("/rent")
public class Rent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String WORKER_HOME_PAGE = "pages?page=home";
	private static final String WORKER_RENT_PAGE = "pages?page=rent";
	private static final String LOGIN_PAGE = "pages?page=logout";

	public Rent() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = (User) request.getSession().getAttribute("user");
		int rentPieces = 0;
		int stockPieces = 0;
		Integer machineId = 0;
		int rentId = 0;

		if (request.getParameter("rentPieces") != null) {
			rentPieces = Integer.valueOf(request.getParameter("rentPieces"));
		}

		if (request.getParameter("stockPieces") != null) {
			stockPieces = Integer.valueOf(request.getParameter("stockPieces"));

		}

		if (request.getParameter("rentId") != null) {
			rentId = Integer.valueOf(request.getParameter("rentId"));

		}

		if (request.getParameter("machineId") != null) {
			machineId = Integer.valueOf(request.getParameter("machineId"));

		}

		System.out.println("stock 62:   " + stockPieces);
		System.out.println("want to rent 63:   " + rentPieces);

		String rentStatus = request.getParameter("rentStatus");
		String rentAgain = request.getParameter("rentAgain");

		DaoDtoMachine ddm = DaoDtoMachine.getInstance();
		DaoMachine dm = DaoMachine.getInstance();

		Machine mach = null;

		String errMsg = "";

		if (user == null) {
			response.sendRedirect(LOGIN_PAGE);
			return;
		}

		if (rentPieces == 0) {
			errMsg = "Please enter number of pieces";
			request.setAttribute("errMsg", errMsg);
			request.setAttribute("errId", machineId);
			System.out.println("uleto u rent komada");
			request.getRequestDispatcher(WORKER_RENT_PAGE).forward(request, response);
			return;

		}

		if (rentStatus.equalsIgnoreCase("completed")) {
			ddm.updateRentStatus(rentId, rentStatus);
			mach = dm.selectMachineById(machineId);
			mach.setStockUnits(mach.getStockUnits() + rentPieces);
			System.out.println("Kad je completed postavice unite na:" + mach.getStockUnits());
			dm.updateMachineUnits(mach);
			response.sendRedirect(WORKER_HOME_PAGE);
			System.out.println("Prosao update status u completed");
			return;
		}

		if (machineId == null || machineId == 0) {
			errMsg = "Try renting again";
			request.setAttribute("errMsg", errMsg);
			request.setAttribute("errId", machineId);
			System.out.println("uleto u machine null ili 0");
			request.getRequestDispatcher(WORKER_RENT_PAGE).forward(request, response);
			return;

		}

		if (rentAgain == null) {
			errMsg = "Try renting again";
			request.setAttribute("errMsg", errMsg);
			request.setAttribute("errId", machineId);
			System.out.println("uleto u rent Again null");
			request.getRequestDispatcher(WORKER_RENT_PAGE).forward(request, response);
			return;

		}

		if (rentAgain.equalsIgnoreCase("rentAgain")) {
			mach = dm.selectMachineById(machineId.intValue());
			if (mach.getStockUnits() - rentPieces >= 0) {
				// System.out.println("Rent Again Upis u Bazu");
				ddm.insertMachineRent(user.getId(), machineId.intValue(), rentStatus, rentPieces);
				
				mach.setStockUnits(stockPieces - rentPieces);
				dm.updateMachineUnits(mach);
				request.getRequestDispatcher(WORKER_HOME_PAGE).forward(request, response);
				return;
			} else {
				// System.out.println("stock: " + stockPieces + " - - - -want to rent: " +
				// rentPieces);
				errMsg = "There is: " + stockPieces + " units";
				request.setAttribute("errMsg", errMsg);
				request.setAttribute("errId", rentId);
				request.getRequestDispatcher(WORKER_HOME_PAGE).forward(request, response);
				return;
			}
		}

		else if (rentStatus.equalsIgnoreCase("renting")) {
			mach = dm.selectMachineById(machineId.intValue());
			if (mach.getStockUnits() - rentPieces >= 0) {
				// System.out.println("Renting Upis u Bazu");
				ddm.insertMachineRent(user.getId(), machineId.intValue(), rentStatus, rentPieces);
				
				mach.setStockUnits(stockPieces - rentPieces);
				dm.updateMachineUnits(mach);
				request.getRequestDispatcher(WORKER_HOME_PAGE).forward(request, response);
				return;
			}
			request.getRequestDispatcher(WORKER_RENT_PAGE).forward(request, response);
			return;
		}

	}

}
