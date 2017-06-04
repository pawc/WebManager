function validate(login, password){
    if(login == ""){
		alert("login is missing");
		return false;
    }
    if(password == ""){
		alert("password is missing");
		return false;
    }
    else{
    	return true;
    }
}