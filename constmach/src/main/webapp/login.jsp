<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="login.css">
</head>
<body>	<div class="container">
		<h2>Login</h2>
		<form action="Login" method="post">
		<input type="text" placeholder="Email" name="email">
		<p>${errMsgEmail }</p>
		 <input type="password"
			placeholder="Password" name="password">
			<p>${errMsgPassword }</p>
		<input type="submit" value="Log in" class="hover-color-change">
		</form>
	</div>
</body>
</html>
