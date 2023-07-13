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
import models.machine.MachineType;

@WebServlet("/AdminMachineActions")
public class AdminMachineActions extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminMachineActions() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		DaoMachine dm = DaoMachine.getInstance();
		ArrayList<Machine> ml = null;
		String errMsg = "";
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

		if (action.equalsIgnoreCase("seeMachinesList")) {
			ml = dm.selectMachines();
			request.setAttribute("machineList", ml);
			request.getRequestDispatcher("adminMachineList.jsp").forward(request, response);

			return;

		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	} // kraj geta

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		String machineId = request.getParameter("machineId");
		String errMsg = "";
		DaoMachine dm = DaoMachine.getInstance();
		Machine m = null;
		ArrayList<Machine> ml = null;

		// parametri za primanje sa stranice za kreiranje i update masina
		String machineType = request.getParameter("machine-type");
		MachineType mt = null;
		String machineModel = request.getParameter("machineModel");
		String stockUnits = request.getParameter("units");
		int stockUnitsValue = 0;
		String manufactName = request.getParameter("manufactName");
		String yearProduced = request.getParameter("yearProduced");
		int yearProducedValue = 0;

		String imgName = request.getParameter("imgName");

		// validacija za parametre sa create i update masina i za akciju
		// validacija akcije
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
		
		if (action.equalsIgnoreCase("deleteMachine")) {
			int machineIdValue = 0;
			try {
				machineIdValue = Integer.parseInt(machineId);

			} catch (Exception e) {
				errMsg = "Please insert number of units of machine.";
				request.setAttribute("errMsg", errMsg);
				request.getRequestDispatcher("adminCreateMachine.jsp").forward(request, response);
				return;
			}

			
			dm.deleteMachineByID(machineIdValue);
			ml = dm.selectMachines();
			request.setAttribute("machineList", ml);
			request.getRequestDispatcher("adminMachineList.jsp").forward(request, response);
			return;
		}

		
		// validacija ostalih parametara
		if (machineType == null) {
			errMsg = "Please select machine type.";
			request.setAttribute("errMsg", errMsg);
			request.getRequestDispatcher("adminCreateMachine.jsp").forward(request, response);
			return;
		}

		if (MachineType.TYPE1.toString().equalsIgnoreCase(machineType)) {
			mt = MachineType.TYPE1;
		} else if (MachineType.TYPE2.toString().equalsIgnoreCase(machineType)) {
			mt = MachineType.TYPE2;
		} else if (MachineType.TYPE3.toString().equalsIgnoreCase(machineType)) {
			mt = MachineType.TYPE3;
		} else {
			errMsg = "Please select machine type.";
			request.setAttribute("errMsg", errMsg);
			request.getRequestDispatcher("adminCreateMachine.jsp").forward(request, response);
			return;
		}

		if (machineType.isEmpty() || machineType.isBlank()) {
			errMsg = "Please select machine type.";
			request.setAttribute("errMsg", errMsg);
			request.getRequestDispatcher("adminCreateMachine.jsp").forward(request, response);
			return;
		}

		if (machineModel == null) {
			errMsg = "Please insert machine model.";
			request.setAttribute("errMsg", errMsg);
			request.getRequestDispatcher("adminCreateMachine.jsp").forward(request, response);
			return;
		}

		if (machineModel.isEmpty() || machineModel.isBlank()) {
			errMsg = "Please insert machine model.";
			request.setAttribute("errMsg", errMsg);
			request.getRequestDispatcher("adminCreateMachine.jsp").forward(request, response);
			return;
		}

		if (stockUnits == null) {
			errMsg = "Please insert units of machine.";
			request.setAttribute("errMsg", errMsg);
			request.getRequestDispatcher("adminCreateMachine.jsp").forward(request, response);
			return;
		}

		if (stockUnits.isEmpty() || stockUnits.isBlank()) {
			errMsg = "Please insert units of machine.";
			request.setAttribute("errMsg", errMsg);
			request.getRequestDispatcher("adminCreateMachine.jsp").forward(request, response);
			return;
		}

		try {
			stockUnitsValue = Integer.parseInt(stockUnits);

		} catch (Exception e) {
			errMsg = "Please insert number of units of machine.";
			request.setAttribute("errMsg", errMsg);
			request.getRequestDispatcher("adminCreateMachine.jsp").forward(request, response);
			return;
		}

		if (manufactName == null) {
			errMsg = "Please insert manufacturer name.";
			request.setAttribute("errMsg", errMsg);
			request.getRequestDispatcher("adminCreateMachine.jsp").forward(request, response);
			return;
		}

		if (manufactName.isEmpty() || manufactName.isBlank()) {
			errMsg = "Please insert manufacturer name.";
			request.setAttribute("errMsg", errMsg);
			request.getRequestDispatcher("adminCreateMachine.jsp").forward(request, response);
			return;
		}

		if (yearProduced == null) {
			errMsg = "Please insert year when machine is produced.";
			request.setAttribute("errMsg", errMsg);
			request.getRequestDispatcher("adminCreateMachine.jsp").forward(request, response);
			return;
		}

		if (yearProduced.isEmpty() || yearProduced.isBlank()) {
			errMsg = "Please insert year when machine is produced.";
			request.setAttribute("errMsg", errMsg);
			request.getRequestDispatcher("adminCreateMachine.jsp").forward(request, response);
			return;
		}

		try {
			yearProducedValue = Integer.parseInt(yearProduced);

		} catch (Exception e) {
			errMsg = "Please insert year number when machine is produced.";
			request.setAttribute("errMsg", errMsg);
			request.getRequestDispatcher("adminCreateMachine.jsp").forward(request, response);
			return;
		}

		if (imgName == null) {
			errMsg = "Please insert machine image name.";
			request.setAttribute("errMsg", errMsg);
			request.getRequestDispatcher("adminCreateMachine.jsp").forward(request, response);
			return;
		}

		if (imgName.isEmpty() || imgName.isBlank()) {
			errMsg = "Please insert machine image name.";
			request.setAttribute("errMsg", errMsg);
			request.getRequestDispatcher("adminCreateMachine.jsp").forward(request, response);
			return;
		} else {
			String img = "img/";
			imgName = img + imgName;
		}

		// izbor po tipu akcije

		if (action.equalsIgnoreCase("seeMachinesList")) {
			ml = dm.selectMachines();
			request.setAttribute("machineList", ml);
			request.getRequestDispatcher("adminMachineList.jsp").forward(request, response);
			return;

		}

		if (action.equalsIgnoreCase("createMachine")) {

			m = new Machine(mt, machineModel, stockUnitsValue, manufactName, yearProducedValue, imgName);

			dm.insertMachine(m);
			ml = dm.selectMachines();
			request.setAttribute("machineList", ml);
			request.getRequestDispatcher("adminMachineList.jsp").forward(request, response);
			return;

		}

	}// kraj post

} // kraj servleta
