<%@page import="java.time.Year"%>
<%@page import="dto.DtoRentMachine"%>
<%@page import="java.util.List"%>
<%@page import="models.machine.Machine"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.user.User"%>
<%@page import="models.user.UserType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
User user = (User) request.getSession().getAttribute("user");
List<Machine> machineList = (List<Machine>) request.getAttribute("machineList");

if (user == null) {
	request.getSession().invalidate();
	response.sendRedirect("login.jsp");
	return;
}

if (user.getType().equals(UserType.ADMIN)) {
%>



<!DOCTYPE html>
<html>

<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<link rel="stylesheet" href="styles.css">


</head>

<body>
	<div class="container">
		<div class="navbar">
			<div class="navbar-links">
				<a class="active" href="adminHome.jsp">Admin Panel</a>
			</div>

			<a href="pages?page=logout" class="logout">Log out</a>
		</div>



		<h2>Machine List</h2>
		<div class="grid-container">
			<%
			if (machineList != null) {
				for (Machine pom : machineList) {
			%>
			<div class="grid-item">
				<img src="<%=pom.getImgPath()%>" alt="Machine 1">
				<div class="machine-type"><%=pom.getType()%></div>
				<h3><%=pom.getModel()%></h3>
				<p><%=pom.getManufacturerName()%></p>
				<p>Pieces in Storage:<%=pom.getStockUnits()%></p>
				<p>Year of Prodcution:<%=pom.getYearProduced()%></p>

				<input type="hidden" name="machineId" value="<%=pom.getId()%>">

				<p>${errMsg }</p>
				<form action="MachineInfoUpdate" method="post">
					<button type="submit">Edit Machine</button>
					<input type="hidden" name="machineId" value="<%=pom.getId()%>">
					<input type="hidden" name="action" value="editMachine">
				</form>
				<br>
				<form action="AdminMachineActions" method="post">
					<button type="submit">Delete Machine</button>
					<input type="hidden" name="machineId" value="<%=pom.getId()%>">
					<input type="hidden" name="action" value="deleteMachine">
				</form>
				
				<br>
								<form action="MachineUnitsUpdate" method="post">
					
					<input type="hidden" name="machineId" value="<%=pom.getId()%>">
					<input type="number" name="units" min="1">
					<input type="hidden" name="action" value="addUnits">
					<button type="submit">Add</button>
				</form>
				
				<br><br>
								<form action="MachineUnitsUpdate" method="post">
					
					<input type="hidden" name="machineId" value="<%=pom.getId()%>">
					<input type="number" name="units" min="1" max="<%=pom.getStockUnits()%>">
					<input type="hidden" name="action" value="removeUnits">
					<button type="submit">Subtract</button>
				</form>

			</div>


		
		<%
		}
		}
		%>
		</div>
	</div>



</body>

</html>
<%
} else {
request.getSession().invalidate();
response.sendRedirect("login.jsp");
}
%>