<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@ page isELIgnored="false" %>
	<link rel="stylesheet" type="text/css" href="/webmanager/static/css/form.css">
	<style>
		tr:hover{background-color:#f5f5f5}
		table{width: 100%;}
	</style>
</head>

<body>

<ul>
	<li><a href="home.html">home</a></li>
	<li><a href="form.html">add</a></li>
	<li><a href="logout.html">logout</a></li>
	<li style="float:right">${login}</li>
</ul>

	<form type="POST" action="deleteAction.html">
	<table>
	<tr>
		<th>login</th>
		<th>first name</th>
		<th>last name</th>
		<th>department</th>
		<th><input type="submit" value="delete selected"/></th>
	</tr>
	<c:forEach items="${employees}" var="employee">
	<tr>
		<td><a href="user.html?login=${employee.login}">${employee.login}</a></td>
		<td>${employee.firstName}</td>
		<td>${employee.lastName}</td>
		<td>${employee.department}</td>
		<td><input type="checkbox" name="${employee.login}"/></td>
	</tr>
	</c:forEach>
	</table>
	</form>
</body>

</html>
