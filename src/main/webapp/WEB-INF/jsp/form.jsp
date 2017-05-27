<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script type="text/javascript" src="/workflow/static/js/validation.js">
    </script>
    <%@ page isELIgnored="false" %>
</head>
<a href="index.jsp">back</a></br>
${rowsAffected}
<body>
<form name="insertEmployee" method="POST" action="form.html" onsubmit="return validate(firstName.value, lastName.value, birthDate.value, employedSince.value, department.value)">
<table cellspacing="10" cellpadding="0" border="0">
    <tr>
        <td>First Name</td>
	<td><input type="text" name="firstName"></td>	
    </tr>
    <tr>
        <td>Last Name</td>
	<td><input type="text" name="lastName"></td>
    </tr>
    <tr>
        <td>Birth Date</td>
	<td><input type="date" name="birthDate" size="10"></td>
    </tr>
    <tr>
        <td>Employed since</td>
	<td><input type="date" name="employedSince" size="10"></td>
    </tr>
    <tr>
	<td>Department</td>
	<td>
	<select name="department">
	<option value="-1" selected>-</option>
	<option value="1">FINANCIAL</option>
	<option value="2">MANAGEMENT</option>
	<option value="3">ACCOUNTING</option>
	<option value="4">IT</option>
	<option value="5">CS</option>
	</select>
	</td>
    </tr>
    <tr><td><input type="submit" value="Submit"></td></tr>
</table>
</form>

</body>
</html>