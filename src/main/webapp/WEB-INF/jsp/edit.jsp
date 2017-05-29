<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script type="text/javascript" src="/workflow/static/js/validation.js">
    </script>
    <%@ page isELIgnored="false" %>
</head>
<a href="result.html">list employees</a></br>
<body>
<form name="editEmployee" method="POST" action="editAction.html" onsubmit="return validate(firstName.value, lastName.value, birthDate.value, employedSince.value, department.value)">
<table cellspacing="10" cellpadding="0" border="0">

	<input type="hidden" name="firstName" value="${parameters[0].firstName}"> 
	<input type="hidden" name="lastName" value="${parameters[0].lastName}"> 
	
    <tr>
        <td>First Name</td>
	<td><input type="text" name="firstNameDiabled" value="${parameters[0].firstName}" disabled></td>	
    </tr>
    <tr>
        <td>Last Name</td>
	<td><input type="text" name="lastNameDisabled" value="${parameters[0].lastName}" disabled></td>
    </tr>
    <tr>
        <td>Birth Date</td>
	<td><input type="date" name="birthDate" size="10" value="${parameters[0].birthDate}"></td>
    </tr>
    <tr>
        <td>Employed since</td>
	<td><input type="date" name="employedSince" size="10" value="${parameters[0].employedSince}"></td>
    </tr>
    <tr>
        <td>is still employed</td>
	<td><input type="checkbox" name="stillEmployed" checked="${parameters[0].stillEmployed}"></td>
    </tr>
    <tr>
	<td>Department</td>
	<td>
	<select name="department">
		<option value="-1" selected>-</option>
		<option value="FINANCIAL">FINANCIAL</option>
		<option value="MANAGEMENT">MANAGEMENT</option>
		<option value="ACCOUNTING">ACCOUNTING</option>
		<option value="IT">IT</option>
		<option value="CS">CS</option>
	</select>
	</td>
    </tr>
        <tr>
	<td>Superior</td>
	<td>
	<select name="superior">
		<c:forEach items="${parameters[1]}" var="login">
			<option value="${login}">${login}</option>
		</c:forEach>
	</select>
	</td>
    </tr>
    <tr><td><input type="submit" value="Submit"></td></tr>
</table>
</form>

</body>
</html>