<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<%@ page isELIgnored="false" %>
    <script type="text/javascript" src="/webmanager/static/js/accountValidation.js">    
    </script>
	<link rel="stylesheet" type="text/css" href="/webmanager/static/css/login.css">
</head>
<body>

<div class="login-page">
	<div class="form">
		<form class="login-form" ame="signIn" method="POST" action="signIn.html" onsubmit="return validate(login.value, password.value)">
			<input type="text" placeholder="username" name="login"/>
			<input type="password" placeholder="password" name="password"/>
			<button>login</button>
			<p class="message"><b>${info}</b></p>
		  </form>
	</div>
</div>

</body>
</html>