<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@ page isELIgnored="false" %>
</head>
<body>
<a href="result.html">back</a> <a href="edit.html?login=${employee.login}"> edit</a><br>
<table cellspacing="0" cellpadding="10" border="1">
	<tr>
		<td>login</td>
		<td><b>${employee.login}</b></td>
	</tr>
	<tr>
		<td>department</td>
		<td><b>${employee.department}</b></td>
	</tr>
	<tr>
		<td>first name</td>
		<td><b>${employee.firstName}</b></td>
	</tr>
	<tr>
		<td>last name</td>
		<td><b>${employee.lastName}</b></td>
	</tr>
	<tr>
		<td>birth date</td>
		<td><b>${employee.birthDate}</b></td>
	</tr>
	<tr>
		<td>employed since</td>
		<td><b>${employee.employedSince}</b></td>
	</tr>
	<tr>
		<td>still employed</td>
		<td>${employee.isStillEmployed}</td>
	</tr>
	<tr>
		<td>superior</td>
		<td><b>${employee.superior}</b></td>
	</tr>
	
</table>
</body>
</html>