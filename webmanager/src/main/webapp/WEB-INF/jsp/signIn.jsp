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
		<form class="login-form" name="signIn" method="POST" action="signInAction.html" onsubmit="return validate(login.value, password.value)">
			<input type="text" placeholder="username" name="login"/>
			<input type="password" placeholder="password" name="password"/>
			<button>login</button>
			<p class="message"><b>${info}</b></p>
	  	</form>
	  	<p class="message">Not registered? <a href="signUp">Create an account</a></p>
	</div>
</div>

</body>
</html>