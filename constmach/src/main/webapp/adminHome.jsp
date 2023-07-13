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

		<div class="welcome-info">
			Welcome
			<%=user.getFirstName()%>
			<br>
		</div>

		<h2>User Controls</h2>
		<div class="grid-container">

			<div class="grid-item">
				<a href="adminCreateUser.jsp">
					<button type="submit">Create User</button>
				</a>
			</div>

			<div class="grid-item">
				<a href="AdminActions?action=seeAllUsers">
					<button type="submit">See All Users</button>
				</a>
			</div>

		</div>

		<br>

		<h2>Machine Controls</h2>
		<div class="grid-container">

			<div class="grid-item">
				<a href="adminCreateMachine.jsp">
					<button type="submit">Create Machine</button>
				</a>
			</div>

			<div class="grid-item">
				<a href="AdminMachineActions?action=seeMachinesList">
					<button type="submit">See All Machines</button>
				</a>
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