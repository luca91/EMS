//Code inspired by http://danielniko.wordpress.com/2012/04/17/simple-crud-using-jsp-servlet-and-mysql/

package com.ems.model;

/**
* User is the JavaBean representing the record of the table User
* 
* @author Luca Barazzuol
*/
public class User {

	private int id;
	private String fname;
	private String lname;
	private String date_of_birth;
	private String password;
	private String email;
	private String role;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
    public String toString() {
        return "User [id=" + id + ", fname=" + fname
                + ", lname=" + lname + ", password=" + password + ", email="
                + email + ", " + "role=" + role +"]";
    } 
	
	
}
