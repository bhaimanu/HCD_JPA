<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register your user</title>
<script type="text/javascript" src="./webjars/jquery/3.2.1/jquery.js"></script>
<script type="text/javascript" src="./javascript.js"></script>

</head>

<body>
	<form id="Register">
		<div id="RegisterUserId" style="display: none">
			User ID:<br> <input type="text" id="userId" name="userId">
			<br>
		</div>

		User name:<br> <input type="text" id="userName" name="userName">
		<br> Password:<br> <input type="password" id="pass"
			name="pass"> <br> Email:<br> <input type="text"
			id="email" name="email">

	</form>

	<br>
	<br>
	<button id="RegisterSaveUser">Save</button>

</body>
</html>