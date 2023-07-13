<%@page import="models.user.UserType"%>
<%@page import="models.machine.Machine"%>
<%@page import="java.util.List"%>
<%@page import="models.user.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%
User user = (User) request.getSession().getAttribute("user");

String errMsg = (String) request.getAttribute("errMsg");
Integer errId = (Integer) request.getAttribute("errId");

List<Machine> rent_list = (List<Machine>) request.getAttribute("rent_list");

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


		<div class="filter-buttons">
			<a href="#">All Types</a> <a href="#">Machine Type 1</a> <a href="#">Machine
				Type 2</a> <a href="#">Machine Type 3</a>
		</div>

		<div class="grid-container">
			<%
			if (rent_list != null) {
				for (Machine pom : rent_list) {
			%>
			<div class="grid-item">
				<form action="rent" method="post">
					<img src="<%=pom.getImgPath()%>" alt="Machine 1">
					<div class="machine-type"><%=pom.getType()%></div>
					<h3><%=pom.getModel()%></h3>
					<p><%=pom.getManufacturerName()%></p>
					<p>
						Pieces in Storage:
						<%=pom.getStockUnits()%></p>
					<label for="rent-input">Pieces to Rent:</label> <input
						class="rentPieces" type="number" id="rentPieces" name="rentPieces"
						min="1" max="<%=pom.getStockUnits()%>"
						<%if (pom.getStockUnits() == 0) {%> disabled
						placeholder="Out of stock" <%}%>>

					<%
					if (errId != null) {
						if (errId.intValue() == pom.getId()) {
					%>
					<p>${errMsg }</p>
					<%
					}
					}
					%>
					<button type="submit">Rent</button>

					<input type="hidden" name="machineId" value="<%=pom.getId()%>">
					<input type="hidden" name="rentStatus" value="renting"> <input
						type="hidden" name="rentAgain" value="false"> <input
						type="hidden" name="stockPieces" value="<%=pom.getStockUnits()%>">
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