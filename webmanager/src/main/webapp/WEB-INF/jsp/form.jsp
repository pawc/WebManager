<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script type="text/javascript" src="/webmanager/static/js/validation.js">
    </script>
    <%@ page isELIgnored="false" %>
	<link rel="stylesheet" type="text/css" href="/webmanager/static/css/form.css">
</head>
<body>
<ul>
	<li><a href="home.html">home</a></li>
	<li><a href="result.html">list</a></li>
</ul>
<form name="insertEmployee" method="POST" action="formAction.html" onsubmit="return validate(firstName.value, lastName.value, birthDate.value, employedSince.value, department.value)">
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
    			<c:forEach items="${logins}" var="login">
					<option value="${login}">${login}</option>
				</c:forEach>
    		</select>
    	</td>
    </tr>
    <tr>
    	<td>
    		<input type="submit" value="Submit">
    	</td>
    </tr>
</table>
</form>

</body>
</html>
