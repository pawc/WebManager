<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@ page isELIgnored="false" %>
	<link rel="stylesheet" type="text/css" href="/webmanager/static/css/mystyle.css">
</head>

<body>
<b>
<a href="form.html">add</a> 
<a href="account.html">account</a>
<a href="test.html">test</a>
</b>
	<form type="POST" action="delete.html">
	<table border="1" cellspacing="0" cellpadding="10">
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
