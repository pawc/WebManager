function validate(){
	
	var firstName = document.getElementsByName("firstName")[0].value.trim();
	var lastName = document.getElementsByName("lastName")[0].value.trim();
	var password = document.getElementsByName("password")[0].value.trim();
	var birthDate = document.getElementsByName("birthDate")[0].value;
	var employedSince = document.getElementsByName("employedSince")[0].value;
	var department = document.getElementsByName("department")[0].value;
	console.log(password);
	
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