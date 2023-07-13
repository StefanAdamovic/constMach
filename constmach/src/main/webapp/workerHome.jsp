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

String errMsg = (String) request.getAttribute("errMsg");
Integer errId = (Integer) request.getAttribute("errId");

List<DtoRentMachine> user_history = (List<DtoRentMachine>) request.getAttribute("user_history");
if (user == null) {
	request.getSession().invalidate();
	response.sendRedirect("login.jsp");
	return;
}

if (user.getType().equals(UserType.WORKER)) {
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
				<a class="active" href="pages?page=home">Home</a> <a
					href="pages?page=rent">Rent</a>
			</div>

			<a href="pages?page=logout" class="logout">Log out</a>
		</div>

		<div class="welcome-info">
			Welcome
			<%=user.getFirstName()%>
			<%=user.getLastName()%>, <br>Your Rent History:
		</div>


		<div class="grid-container">
			<%
			if (user_history != null) {
				for (DtoRentMachine pom : user_history) {
			%>
			<div class="grid-item">
				<img src=<%=pom.getImgPath()%> alt="Machine 1">
				<div class="machine-type"><%=pom.getType()%></div>
				<h3><%=pom.getModel()%></h3>
				<p><%=pom.getManufacturerName()%></p>
				<p>
					Units Rented:
					<%=pom.getUnitsRented()%></p>
				<p>
					Start Date:
					<%=pom.getStartDate()%></p>
				<%
				if (pom.getEndDate() != null) {
				%>
				<p>
					End Date:
					<%=pom.getEndDate()%></p>
				<p>
					<%
					}
					%>
					Status:
					<%=pom.getRentStatus()%></p>
				<%
				String status = pom.getRentStatus();
				if (status.equalsIgnoreCase("Renting")) {
				%>
				<form action="rent" method="post">
					<input type="hidden" name="rentId" value="<%=pom.getId()%>">
					<input type="hidden" name="machineId"
						value="<%=pom.getMachineId()%>">

					<button type="submit">End Rent</button>
					<input type="hidden" name="rentStatus" value="completed">
<input class="rentPieces" type="hidden" id="rentPieces"
						name="rentPieces" value="<%=pom.getUnitsRented()%>">
				</form>
				<%
				} else if (status.equalsIgnoreCase("completed")) {
				%>
				<form action="rent" method="post">
					 <input
						type="hidden" name="rentId" value="<%=pom.getId()%>">
					<%
					if (errId != null) {
						if (errId.intValue() == pom.getId()) {
					%>
					<p>${errMsg }</p>
					<%
					}
					}
					%>

					<button type="submit">Rent Again</button>
					<input type="hidden" name="rentStatus" value="renting"> <input
						type="hidden" name="rentAgain" value="rentAgain"> <input
						type="hidden" name="machineId" value="<%=pom.getMachineId()%>">
					<input type="hidden" name="rentPieces"
						value="<%=pom.getUnitsRented()%>">



				</form>
				<%
				}
				%>
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