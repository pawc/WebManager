function validate(){
	
	var firstName = document.getElementsByName("firstName")[0].value.trim();
	var lastName = document.getElementsByName("lastName")[0].value.trim();
	var password = document.getElementsByName("password")[0].value.trim();
	var birthDate = document.getElementsByName("birthDate")[0].value;
	var employedSince = document.getElementsByName("employedSince")[0].value;
	var department = document.getElementsByName("department")[0].value;
	console.log(department);
	console.log(department == -1);
	
    if(firstName == ""){
		document.getElementById("firstName").focus();
		return false;
    }
    if(lastName == ""){
    	document.getElementById("lastName").focus();
		return false;
    }
    if(password.length < 4){
    	alert("Password too short");
    	return false;
    }
    if(!birthDate){
    	document.getElementById("birthDate").focus();
		return false;
    }
    if(!employedSince){
    	document.getElementById("employedSince").focus();
		return false;
    }
    if(department == -1){
    	document.getElementById("department").focus();
		return false;
    }
    else{
    	return true;
    }
}