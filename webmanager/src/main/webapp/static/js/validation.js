function validate(firstName, lastName, password, birthDate, employedSince, department){
    if(firstName == ""){
	alert("First name is missing");
	return false;
    }
    if(lastName == ""){
	alert("Last name is missing");
	return false;
    }
    if(password.length < 4){
	alert("Password too short");
	return false;
    }
    if(!birthDate){
	alert("Birth date is missing");
	return false;
    }
    if(!employedSince){
	alert("Employment date is missing");
	return false;
    }
    if(department == -1){
	alert("Choose a department");
	return false;
    }
    else{
	return true;
    }
}