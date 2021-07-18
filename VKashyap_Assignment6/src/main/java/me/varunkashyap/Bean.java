/**
 * @author: Varun Kashyap
 * FileName: Bean.java
 * Date: 06/28/2021
 * Description: MVC Model bean for assignment2 User Registration
 */

package me.varunkashyap;

public class Bean {
	
	public Bean() {
		
	}
	
	private String userId;
	private String password;
	private String firstName;
	private String lastName;
	private String errorMessage;
	private Integer ss1;
	private Integer ss2;
	private Integer ss3;
	private Integer ssCombined;
	private Integer loginAttempts;
	private String email;
	private String address;
	private String city;
	private String state;
	private String fullAddress;
	private Integer postalCode;
	private Boolean formAComplete;
	private Boolean formBComplete;
	private Boolean loginId;
	private Boolean loginPassword;
	private Boolean loginCorrect;
	private Boolean userAddedToDB;
	
	
	
	public Boolean isUserAddedToDB() {
		return userAddedToDB;
	}

	public void setUserAddedToDB(Boolean userAddedToDB) {
		this.userAddedToDB = userAddedToDB;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public Integer getSsCombined() {
		return ssCombined;
	}

	public void setSsCombined(Integer ssCombined) {
		this.ssCombined = ssCombined;
	}

	public Integer getLoginAttempts() {
		return loginAttempts;
	}

	public void setLoginAttempts(Integer loginAttempts) {
		this.loginAttempts = loginAttempts;
	}

	public Boolean isLoginCorrect() {
		return loginCorrect;
	}

	public void setLoginCorrect(Boolean loginCorrect) {
		this.loginCorrect = loginCorrect;
	}

	public Boolean isLoginId() {
		return loginId;
	}

	public void setLoginId(Boolean loginId) {
		this.loginId = loginId;
	}

	public Boolean isLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(Boolean loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Boolean isFormBComplete() {
		return formBComplete;
	}

	public void setFormBComplete(Boolean formBComplete) {
		this.formBComplete = formBComplete;
	}

	public Boolean isFormAComplete() {
		return formAComplete;
	}
	
	public void setFormAComplete(Boolean formAComplete) {
		this.formAComplete = formAComplete;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
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
	public Integer getSs1() {
		return ss1;
	}
	public void setSs1(Integer ss1) {
		this.ss1 = ss1;
	}
	public Integer getSs2() {
		return ss2;
	}
	public void setSs2(Integer ss2) {
		this.ss2 = ss2;
	}
	public Integer getSs3() {
		return ss3;
	}
	public void setSs3(Integer ss3) {
		this.ss3 = ss3;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(Integer postalCode) {
		this.postalCode = postalCode;
	}
}
