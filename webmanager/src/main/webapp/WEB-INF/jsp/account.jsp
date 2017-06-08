<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<%@ page isELIgnored="false" %>
    <script type="text/javascript" src="/webmanager/static/js/accountValidation.js">    
    </script>
	<link rel="stylesheet" type="text/css" href="/webmanager/static/css/form.css">
</head>
<body>
<b>
${info}
<hr>

<form name="signIn" method="POST" action="signIn.html" onsubmit="return validate(login.value, password.value)">
	<table cellspacing="10" cellpadding="0" border="0">
		<tr>
			<td>login</td>
			<td><input type="text" name="login"/></td>
		</tr>
		<tr>
			<td>password</td>
			<td><input type="password" name="password"/></td>
		</tr>
		<tr>
			<td><input type="submit" value="sign in"/></td>
		</tr>
	</table>
</form>

<hr>

<form name="signUp" method="POST" action="signUp.html" onsubmit="return validate(login.value, password.value)">
	<table cellspacing="10" cellpadding="0" border="0">
		<tr>
			<td>login: </td>
			<td><input type="text" name="login"/></td>
		</tr>
		<tr>
			<td>password: </td>
			<td><input type="password" name="password"/></td>
		</tr>
		<tr>
			<td><input type="submit" value="sign up"/></td>
		</tr>
	</table>
</form>

<hr>

</body>
</html>
