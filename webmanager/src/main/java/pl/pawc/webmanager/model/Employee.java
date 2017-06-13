package pl.pawc.webmanager.model;

import java.sql.Date;

public class Employee{
	
	private int id;
	private String login;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private Date employedSince;
	private boolean stillEmployed;
	private Department department;
	private String superior;
	
	public Employee(){
	}

	public Employee(int id, String login, String firstName, String lastName, Date birthDate, Date employedSince,
			boolean stillEmployed, Department department, String superior){
		super();
		this.id = id;
		this.login = login;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.employedSince = employedSince;
		this.stillEmployed = stillEmployed;
		this.department = department;
		this.superior = superior;
	}

	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}

	public String getLogin(){
		return login;
	}

	public void setLogin(String login){
		this.login = login;
	}
	
	public String getFirstName(){
		return firstName;
	}
	
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	
	public String getLastName(){
		return lastName;
	}
	
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	
	public Date getBirthDate(){
		return birthDate;
	}
	
	public void setBirthDate(Date birthDate){
		this.birthDate = birthDate;
	}
	
	public Date getEmployedSince(){
		return employedSince;
	}
	
	public void setEmployedSince(Date employedSince){
		this.employedSince = employedSince;
	}
	
	public boolean isStillEmployed(){
		return stillEmployed;
	}
	
	public void setStillEmployed(boolean isStillEmployed){
		this.stillEmployed = isStillEmployed;
	}
	
	public Department getDepartment(){
		return department;
	}
	
	public void setDepartment(Department department){
		this.department = department;
	}

	public String getSuperior(){
		return superior;
	}

	public void setSuperior(String superior){
		this.superior = superior;
	}
	
}