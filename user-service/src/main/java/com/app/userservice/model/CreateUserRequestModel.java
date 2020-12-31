package com.app.userservice.model;

public class CreateUserRequestModel {

	/*
	 * @NotNull(message = "FirstName can not be null")
	 * 
	 * @Size(min = 2, message = "firstname must not be less than two characters")
	 */
	private String firstName;
	
	/*
	 * @NotNull(message = "LastName can not be null")
	 * 
	 * @Size(min = 2, message = "lastname must not be less than two characters")
	 */
	private String lastName;
	
	/*
	 * @NotNull(message = "password can not be null")
	 * 
	 * @Size(min=4,max=16,message="password must be between 4 to 16 characters long"
	 * )
	 */
	private String password;
	
	/*
	 * @NotNull(message = "Email can not be null")
	 * 
	 * @Email
	 */
	private String email;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

}
