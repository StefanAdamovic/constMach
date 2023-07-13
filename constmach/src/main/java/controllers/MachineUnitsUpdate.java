package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.machine.DaoMachine;
import models.machine.Machine;

/**
 * Servlet implementation class MachineUnitsUpdate
 */
@WebServlet("/MachineUnitsUpdate")
public class MachineUnitsUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MachineUnitsUpdate() {
		super();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String machineId = request.getParameter("machineId");
		String errMsg = "";
		DaoMachine dm = DaoMachine.getInstance();
		Machine m = null;
		String units = request.getParameter("units");
		ArrayList<Machine> ml = null;
		int unitsValue = 0;
		int machineIdValue = 0;

		if (action == null) {
			errMsg = "Failed action. Try Again.";
			request.setAttribute("errMsg", errMsg);
			request.getRequestDispatcher("adminHome.jsp").forward(request, response);
			return;
		} else if (action.isEmpty() || action.isBlank()) {
			errMsg = "Failed action. Try Again.";
			request.setAttribute("errMsg", errMsg);
			request.getRequestDispatcher("adminHome.jsp").forward(request, response);
			return;
		}

		try {
			unitsValue = Integer.parseInt(units);
			machineIdValue = Integer.parseInt(machineId);

		} catch (Exception e) {
			errMsg = "Please try inserting units again.";
			request.setAttribute("errMsg", errMsg);
			ml = dm.selectMachines();
			request.setAttribute("machineList", ml);
			request.getRequestDispatcher("adminMachineList.jsp").forward(request, response);
			return;
		}

		if (unitsValue < 0) {
			errMsg = "Please enter positive number.";
			request.setAttribute("errMsg", errMsg);
			ml = dm.selectMachines();
			request.setAttribute("machineList", ml);
			request.getRequestDispatcher("adminMachineList.jsp").forward(request, response);
			return;
		}

		m = dm.selectMachineById(machineIdValue);

		if (action.equalsIgnoreCase("addUnits")) {
			m.setStockUnits(m.getStockUnits() + unitsValue);
			dm.updateMachineUnits(m);
			ml = dm.selectMachines();
			request.setAttribute("machineList", ml);
			request.getRequestDispatcher("adminMachineList.jsp").forward(request, response);
			return;
		}

		if (action.equalsIgnoreCase("removeUnits")) {
			if ((m.getStockUnits() - unitsValue) >= 0) {
				m.setStockUnits(m.getStockUnits() - unitsValue);
				dm.updateMachineUnits(m);
				ml = dm.selectMachines();
				request.setAttribute("machineList", ml);
				request.getRequestDispatcher("adminMachineList.jsp").forward(request, response);
				return;
			}
		}

	}
}
