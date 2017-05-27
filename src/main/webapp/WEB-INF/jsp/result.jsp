<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@ page isELIgnored="false" %>
</head>

<body>
<a href="index.jsp">back</a></br>
	<table border="1">
	<tr>
		<td>id</td>
		<td>login</td>
		<td>first name</td>
		<td>last name</td>
		<td>birth date</td>
		<td>employed since</td>
		<td>is still employed</td>
		<td>department</td>
		<td>superior</td>
	</tr>
	<c:forEach items="${employees}" var="employee">
	<tr>
		<td>${employee.id}</td>
		<td>${employee.login}</td>
		<td>${employee.firstName}</td>
		<td>${employee.lastName}</td>
		<td>${employee.birthDate}</td>
		<td>${employee.employedSince}</td>
		<td>${employee.isStillEmployed}</td>
		<td>${employee.department}</td>
		<td>${employee.superior}</td>
	</tr>
	</c:forEach>
	</table>
</body>

</html>