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
List<User> userList = (List<User>) request.getAttribute("userList");

Year y = Year.now();
int currentYear = y.getValue();
int currentAge = 0;

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



		<h2>User List</h2>
		<div class="grid-container">
			<%
			if (userList != null) {
				for (User pom : userList) {
			%>
			<div class="grid-item">
				

				<h3><%=pom.getFirstName()%>
					<%=pom.getLastName()%></h3>
				<p>Account Type: <%=pom.getType()%></p>
				<p>Email: <%=pom.getEmail()%></p>
				
<%currentAge = currentYear - pom.getYearBorn(); 
if(pom.getType() == UserType.WORKER){
%>
				<p>Age:<%=currentAge%></p>
				<%} %>
				<br>
<form action="AdminActions" method="post">
<button type="submit" >Edit User</button>
<input type="hidden" name="userId" value="<%=pom.getId()%>">
<input type="hidden" name="action" value="editUser">
</form>
<br>
<form action="AdminActions" method="post">
<button type="submit" >Delete User</button>
<input type="hidden" name="userId" value="<%=pom.getId()%>">
<input type="hidden" name="action" value="deleteUser">
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