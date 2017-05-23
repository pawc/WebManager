package pl.pawc.workflow.model;

import java.util.Date;

public class Employee{
	
	private int id;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private Date employedSince;
	private boolean isStillEmployed;
	private Department department;
	
	public Employee(){
	}

	public Employee(int id, String firstName, String lastName, Date birthDate, Date employedSince,
			boolean isStillEmployed, Department department){
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.employedSince = employedSince;
		this.isStillEmployed = isStillEmployed;
		this.department = department;
	}

	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
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
		return isStillEmployed;
	}
	
	public void setStillEmployed(boolean isStillEmployed){
		this.isStillEmployed = isStillEmployed;
	}
	
	public Department getDepartment(){
		return department;
	}
	
	public void setDepartment(Department department){
		this.department = department;
	}
	
}