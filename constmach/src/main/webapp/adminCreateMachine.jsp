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



		<h2>Creating Machine</h2>
		<div class="grid-container">

			<div class="grid-item">
				<form action="AdminMachineActions" method="post">

					<label for="machine-type">Machine Type:</label> <select
						name="machine-type">
						<option value="TYPE1">TYPE 1</option>
						<option value="TYPE2">TYPE 2</option>
						<option value="TYPE3">TYPE 3</option>
					</select> <br> <br> <label for="machineModel">Model:</label> <input
						class="machineModel" type="text" id="machineModel"
						name="machineModel"> <br> <br> <label
						for="units">Units:</label> <input class="units" type="number"
						id="units" name="units"> <br> <br> <label for="manufactName">Manufacturer Name:</label> <select
						name="manufactName">
						<option value="MANUFACTURER 1">MANUFACTURER 1</option>
						<option value="MANUFACTURER 2">MANUFACTURER 2</option>
						<option value="MANUFACTURER 3">MANUFACTURER 3</option>
					</select> <br> <br> <label
						for="yearProduced">Year Produced:</label> <input
						class="yearProduced" type="text" id="yearProduced"
						name="yearProduced"><br> <br> <label
						for="imgName">Image name(name.extName) :</label> <input
						class="imgName" type="text" id="imgName"
						name="imgName"> <input type="hidden" name="action"
						value="createMachine"> <br> <br> ${errMsg }
					<button type="submit">Create Machine</button>
				</form>


			</div>


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