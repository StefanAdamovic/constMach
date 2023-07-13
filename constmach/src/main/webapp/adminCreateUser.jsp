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



		<h2>Creating User</h2>
		<div class="grid-container">

			<div class="grid-item">
				<form action="AdminActions" method="post">

					<label for="user-type">User Type:</label> <select name="user-type">
						<option value="worker">Worker</option>
						<option value="admin">Admin</option>
					</select> <br> <br> <label for="email">Email:</label> <input
						class="email" type="text" id="email" name="email"> <br>
					<br> <label for="password">Password:</label> <input
						class="password" type="text" id="password" name="password">

					<br> <br> <label for="firstName">First Name:</label> <input
						class="firstName" type="text" id="firstName" name="firstName">

					<br> <br> <label for="lastName">Last Name:</label> <input
						class="lastName" type="text" id="lastName" name="lastName">

					<br> <br> <label for="yearBorn">Year Born:</label> <input
						class="yearBorn" type="text" id="yearBorn" name="yearBorn">

					<input type="hidden" name="action" value="createUser"> <br>
					<br> ${errMsg }
					<button type="submit">Create User</button>
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