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


@WebServlet("/MachineInfoUpdate")
public class MachineInfoUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MachineInfoUpdate() {
        super();

    }




	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String action = request.getParameter("action");
		String machineId = request.getParameter("machineId");
		String errMsg = "";
		DaoMachine dm = DaoMachine.getInstance();
		Machine m = null;
		ArrayList<Machine> ml = null;
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

			machineIdValue = Integer.parseInt(machineId);

		} catch (Exception e) {
			errMsg = "Please try inserting units again.";
			request.setAttribute("errMsg", errMsg);
			ml = dm.selectMachines();
			request.setAttribute("machineList", ml);
			request.getRequestDispatcher("adminMachineList.jsp").forward(request, response);
			return;
		}
		
		if (action.equalsIgnoreCase("editMachine")) {
			
			m  = dm.selectMachineById(machineIdValue);
			request.setAttribute("machineid", m.getId());
			request.setAttribute("machineImgPath", m.getImgPath());
			request.setAttribute("manufacturerName", m.getManufacturerName());
			request.setAttribute("machineModel", m.getModel());
			request.setAttribute("machineType", m.getType().toString());
			request.setAttribute("machineYearProduced", m.getYearProduced());
			request.getRequestDispatcher("adminEditMachine.jsp").forward(request, response);
			return;
		}
		
if (action.equalsIgnoreCase("updateMachine")) {
			
			
			
			String machineType = request.getParameter("machine-type");
			MachineType mt = null;
			String machineModel = request.getParameter("machineModel");
			String manufactName = request.getParameter("manufactName");
			String yearProduced = request.getParameter("yearProduced");
			int yearProducedValue = 0;

			String imgName = request.getParameter("imgName");
			
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
			}
			
			m  = dm.selectMachineById(machineIdValue);
			
			m.setImgPath(imgName);
			m.setManufacturerName(manufactName);
			m.setModel(machineModel);
			m.setType(mt);
			m.setYearProduced(yearProducedValue);
			
			dm.updateMachine(m);
			
			
			ml = dm.selectMachines();
			request.setAttribute("machineList", ml);
			request.getRequestDispatcher("adminMachineList.jsp").forward(request, response);
			return;
			
		}
	}

}
