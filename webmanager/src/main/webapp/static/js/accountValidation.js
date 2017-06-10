function validate(login, password){
    if(login.trim() == ""){
		alert("login is missing");
		return false;
    }
    if(password.trim() == ""){
		alert("password is missing");
		return false;
    }
    else{
    	return true;
    }
}