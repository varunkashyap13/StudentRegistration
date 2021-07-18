<%--@author: Varun Kashyap--%>
<%--FileName: index.jsp--%>
<%--Date: 06/28/2021--%>
<%--Description: MVC View for assignment6 User Registration --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="me.varunkashyap.Bean"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Registration</title>
<style>
<%@includefile="resources/css/style.css"%>
</style>
</head>
<body>
<%--Import java bean--%>
	<%
	Bean javaBean = (Bean) session.getAttribute("form");
	%>
	<%--welcome page--%>
	<%
	if (javaBean == null) {
	%>
	<h1>Welcome to the Student Registration Site</h1>
	<div class="center">
		<h1>Login</h1>
		<form action="RegistrationController" method="post">
			<div class="txt_field">
				<input type="text" onkeypress="return event.charCode != 32"
					name="loginId" minlength="8" maxlength="16" required> <span></span>
				<label>User Id</label>
			</div>
			<br>
			<div class="txt_field">
				<input type="password" onkeypress="return event.charCode != 32"
					name="loginPassword" minlength="8" maxlength="16" required>
				<span></span> <label>Password</label>
			</div>
			<br>
			<div class="login_reset">
				<input type="hidden" name="formName" value="login"> <input
					type="submit" value="Login"> <input type="reset"
					name="formName" value="Reset">
			</div>
		</form>
		<div class="register_text">
			<h3>New users, please register first:</h3>
			<form action="RegistrationController" method="post">
				<div class="register_button">
					<input type="hidden" name="formName" value="register"> <input
						type="submit" value="Register">
				</div>
			</form>
		</div>
	</div>
	<%--user has not tried to login yet--%>
	<%} else {%>
	<%if (javaBean.getLoginAttempts() == null) {%>
	<%--form A has not been attempted--%>
	<%
	if (javaBean.isFormAComplete() == null) {
	%>
	<%
	if (javaBean.isLoginCorrect() == null) {
	%>
	<%--welcome page--%>
	<h1>Welcome to the Student Registration Site</h1>
	<div class="center">
		<h1>Login</h1>
		<form action="RegistrationController" method="post">
			<div class="txt_field">
				<input type="text" onkeypress="return event.charCode != 32"
					name="loginId" minlength="8" maxlength="16" required> <span></span>
				<label>User Id</label>
			</div>
			<br>
			<div class="txt_field">
				<input type="password" onkeypress="return event.charCode != 32"
					name="loginPassword" minlength="8" maxlength="16" required>
				<span></span> <label>Password</label>
			</div>
			<br>
			<div class="login_reset">
				<input type="hidden" name="formName" value="login"> <input
					type="submit" value="Login"> <input type="reset"
					name="formName" value="Reset">
			</div>
		</form>
		<div class="register_text">
			<h3>New users, please register first:</h3>
			<form action="RegistrationController" method="post">
				<div class="register_button">
					<input type="hidden" name="formName" value="register"> <input
						type="submit" value="Register">
				</div>
			</form>
		</div>
	</div>
	<%} else {%>
	<%--login is correct. display logged in welcome --%>
	<%
	if (javaBean.isLoginCorrect() == true) {
	%>
	<h1>
		Welcome to the site,
		<%=javaBean.getFirstName()%>
		<%=javaBean.getLastName()%></h1>
	<div class="action">
		<h1>Select your action:</h1>
		<div class="form">
			<form action="RegistrationController" method="post">
				<div class="radio_field">
					<input type="radio" id="register" name="registerAction"
						value="registerToCourse" required> <label for="register">Register
						to course</label><br>
				</div>
				<div class="radio_field">
					<input type="radio" id="logout" name="registerAction"
						value="logout"> <label for="logout">Logout</label><br>
				</div>
		</div>
		<br>
		<div class="submit">
			<input type="hidden" name="formName" value="ToBeDecided"> <input
				type="submit" value="Submit">
		</div>
		</form>
	</div>
	<%} else {%>
	<%--login incorrect --%>
	<h1>Wrong UserId and/or password.</h1>
	<div class="wrong_login">
		<h1>Please try to login again or register:</h1>
		<form action="RegistrationController" method="post">
			<div class="wrong_form">
				<div class="radio_field">
					<input type="radio" id="login" name="wrongLogin" value="login"
						required> <label for="login">Login </label><br>
				</div>
				<div class="radio_field">
					<input type="radio" id="register" name="wrongLogin"
						value="register"> <label for="register">Register
						to SRS</label><br>
				</div>
				<br>
			</div>
			<div class="submit">
				<input type="hidden" name="formName" value="wrongLogin"> <input
					type="submit" value="Submit">
			</div>
		</form>
	</div>
	<%
	}
	%>
	<%
	}
	%>
	<%} else {%>
	<%--show error messages from form A --%>
	<%
	if (javaBean.isFormAComplete() == false) {
	%>
	<%
	if (javaBean.getErrorMessage() != null
	        || !javaBean.getErrorMessage().equals("")) {
	%>
	<h1>The following errors were found:</h1>
	<div class="center_error">
		<h1 class="error"><%=javaBean.getErrorMessage()%></h1>
		<h3>Click below to restart User Registration Form A:</h3>
		<form action="RegistrationController" method="post">
			<input type="hidden" name="formName" value="indexA"> <input
				type="submit" value="Restart Form A">
		</form>
	</div>
	<%} else {%>
	<h1>Click below to begin User Registration:</h1>
	<div class="center_error">
		<form action="RegistrationController" method="post">
			<input type="hidden" name="formName" value="indexA"> <input
				type="submit" value="Begin User Registration">
		</form>
	</div>
	<%
	}
	%>
	<%} else {%>
	<%--form A is not complete yet--%>
	<%
	if (javaBean.isFormBComplete() == null) {
	%>
	<h1>Form A is complete. Please continue on to Form B:</h1>
	<div class="center_error">

		<form action="RegistrationController" method="post">
			<input type="hidden" name="formName" value="indexB"> <input
				type="submit" value="Continue to Form B">
		</form>
	</div>
	<%} else {%>
	<%--form A is complete, but form B isnt--%>
	<%if (javaBean.isFormBComplete() == false) {%>
	<%
	if (javaBean.getErrorMessage() != null
	        || !javaBean.getErrorMessage().equals("")) {
	%>
	<%--displays form B error messages--%>
	<h1>The following errors were found:</h1>
	<div class="center_error">
		<h1 class="error"><%=javaBean.getErrorMessage()%></h1>
		<h3>Click below to restart User Registration Form B:</h3>
		<form action="RegistrationController" method="post">
			<input type="hidden" name="formName" value="indexB"> <input
				type="submit" value="Restart Form B">
		</form>
	</div>
	<%
	} else {
	%>
	<div class="center_error">
		<h3>Click below to restart User Registration FormB:</h3>
		<form action="RegistrationController" method="post">
			<input type="hidden" name="formName" value="indexB"> <input
				type="submit" value="Restart Form B">
		</form>
	</div>
	<%
	}
	%>
	<%} else {%>
	<%if (javaBean.isUserAddedToDB() != null) {%>
	<%if (javaBean.isUserAddedToDB() == true) {%>
	<%--success register message--%>
	<h1>Congrats!</h1>
	<div class="center_congrats">
		<h1>You are registered!</h1>
		<div class="text">
			<p>
				Dear <span><%=javaBean.getFirstName()%></span>, you have been
				registered under UserId: <span><%=javaBean.getUserId()%></span>
			</p>
			<p>
				A confirmation email will be sent to: <span><%=javaBean.getEmail()%></span>
			</p>
			<p>Click below to go to the sign-in page:</p>
		</div>
		<form action="RegistrationController" method="post">
			<input type="hidden" name="formName" value="signIn"> <input
				type="submit" value="Sign-in">
		</form>
	</div>
	<%} else {%>
	<%--userId already exists--%>
	<h1>Sorry, this user is already registered.</h1>
	<div class="center">
		<h1>If you already have an account, please log in:</h1>
		<form action="RegistrationController" method="post">
			<div class="txt_field">
				<input type="text" onkeypress="return event.charCode != 32"
					name="loginId" minlength="8" maxlength="16" required> <span></span>
				<label>User Id</label>
			</div>
			<br>
			<div class="txt_field">
				<input type="password" onkeypress="return event.charCode != 32"
					name="loginPassword" minlength="8" maxlength="16" required>
				<span></span> <label>Password</label>
			</div>
			<br>
			<div class="login_reset">
				<input type="hidden" name="formName" value="login"> <input
					type="submit" value="Login"> <input type="reset"
					name="formName" value="Reset">
			</div>
		</form>
		<div class="register_text">
			<h3>New users, please register first:</h3>
			<form action="RegistrationController" method="post">
				<div class="register_button">
					<input type="hidden" name="formName" value="register"> <input
						type="submit" value="Register">
				</div>
			</form>
		</div>
	</div>
	<%
	}
	%>
	<%} else {%>
	<%--unsuccessful registration attempt--%>
	<h3>Sorry, unable to register you.</h3>
	<br>
	<h3>If you already have an account, please log in:</h3>
	<form action="RegistrationController" method="post">
		UserId: <input type="text" onkeypress="return event.charCode != 32"
			name="loginId" minlength="8" maxlength="16" required> <br>
		Password: <input type="password"
			onkeypress="return event.charCode != 32" name="loginPassword"
			minlength="8" maxlength="16" required> <br> <input
			type="hidden" name="formName" value="login"> <input
			type="submit" value="Login"> <input type="reset"
			name="formName" value="Reset">
	</form>
	<br>
	<h3>For new users, please register first:</h3>
	<form action="RegistrationController" method="post">
		<input type="hidden" name="formName" value="register"> <input
			type="submit" value="Register">
	</form>
	<%
	}
	%>
	<%
	}
	%>
	<%
	}
	%>
	<%
	}
	%>
	<%
	}
	%>
	<%
	} else {
	%>
	<%
	if (javaBean.getLoginAttempts() > 2) {
	%>
	<%--login error page--%>
	<h1>Unable to login</h1>
	<div class="center_max">
		<h1>Sorry, you have exceeded the maximum number of login attempts
			allowed.</h1>
		<h3>Please contact administration for additional login
			assistance. Thank you.</h3>
	</div>
	<%} else {%>
	<%
	if (javaBean.isFormAComplete() == null) {
	%>
	<%
	if (javaBean.isLoginCorrect() == null) {
	%>
	<%--welcome page--%>
	<h1>Welcome to the Student Registration Site</h1>
	<div class="center">
		<h1>Login</h1>
		<form action="RegistrationController" method="post">
			<div class="txt_field">
				<input type="text" onkeypress="return event.charCode != 32"
					name="loginId" minlength="8" maxlength="16" required> <span></span>
				<label>User Id</label>
			</div>
			<br>
			<div class="txt_field">
				<input type="password" onkeypress="return event.charCode != 32"
					name="loginPassword" minlength="8" maxlength="16" required>
				<span></span> <label>Password</label>
			</div>
			<br>
			<div class="login_reset">
				<input type="hidden" name="formName" value="login"> <input
					type="submit" value="Login"> <input type="reset"
					name="formName" value="Reset">
			</div>
		</form>
		<div class="register_text">
			<h3>New users, please register first:</h3>
			<form action="RegistrationController" method="post">
				<div class="register_button">
					<input type="hidden" name="formName" value="register"> <input
						type="submit" value="Register">
				</div>
			</form>
		</div>
	</div>
	<%} else {%>
	<%--login success page --%>
	<%
	if (javaBean.isLoginCorrect() == true) {
	%>
	<h1>
		Welcome to the site,
		<%=javaBean.getFirstName()%>
		<%=javaBean.getLastName()%></h1>
	<div class="dialogue">
		<h1>Select your action:</h1>
		<div class="form">
			<form action="RegistrationController" method="post">
				<div class="radio_field">
					<input type="radio" id="register" name="registerAction"
						value="registerToCourse" required> <label for="register">Register
						to course</label><br>
				</div>
				<div class="radio_field">
					<input type="radio" id="logout" name="registerAction"
						value="logout"> <label for="logout">Logout</label><br>
				</div>
		</div>
		<br>
		<div class="submit">
			<input type="hidden" name="formName" value="ToBeDecided"> <input
				type="submit" value="Submit">
		</div>
		</form>
	</div>
	<%} else {%>
	<%--unsuccessful login page --%>
	<h1>Wrong UserId and/or password.</h1>
	<div class="wrong_login">
		<h1>Please try to login again or register.</h1>
		<form action="RegistrationController" method="post">
			<div class="wrong_form">
				<div class="radio_field">
					<input type="radio" id="login" name="wrongLogin" value="login"
						required> <label for="login">Login </label><br>
				</div>
				<div class="radio_field">
					<input type="radio" id="register" name="wrongLogin"
						value="register"> <label for="register">Register
						to SRS</label><br>
				</div>
				<br>
			</div>
			<div class="submit">
				<input type="hidden" name="formName" value="wrongLogin"> <input
					type="submit" value="Submit">
			</div>
		</form>
	</div>
	<%
	}
	%>
	<%
	}
	%>
	<%} else {%>
	<%
	if (javaBean.isFormAComplete() == false) {
	%>
	<%--form A error page--%>
	<%
	if (javaBean.getErrorMessage() != null
	        || !javaBean.getErrorMessage().equals("")) {
	%>
	<h1>The following errors were found:</h1>
	<div class="center_error">
		<h1 class="error"><%=javaBean.getErrorMessage()%></h1>
		<h3>Click below to restart User Registration Form A:</h3>
		<form action="RegistrationController" method="post">
			<input type="hidden" name="formName" value="indexA"> <input
				type="submit" value="Restart Form A">
		</form>
	</div>
	<%} else {%>
	<div class="center_error">
		<h3>Click below to begin User Registration:</h3>
		<form action="RegistrationController" method="post">
			<input type="hidden" name="formName" value="indexA"> <input
				type="submit" value="Begin User Registration">
		</form>
	</div>
	<%
	}
	%>
	<%} else {%>
	<%
	if (javaBean.isFormBComplete() == null) {
	%>
	<%--Form A complete, but form B isnt--%>
	<div class="center_error">
		<h3>Form A is complete. Please continue on to Form B:</h3>
		<form action="RegistrationController" method="post">
			<input type="hidden" name="formName" value="indexB"> <input
				type="submit" value="Continue to Form B">
		</form>
	</div>
	<%} else {%>
	<%if (javaBean.isFormBComplete() == false) {%>
	<%
	if (javaBean.getErrorMessage() != null
	        || !javaBean.getErrorMessage().equals("")) {
	%>
	<%--form B error page--%>
	<h1>The following errors were found:</h1>
	<div class="center_error">
		<h1 class="error"><%=javaBean.getErrorMessage()%></h1>
		<h3>Click below to restart User Registration Form B:</h3>
		<form action="RegistrationController" method="post">
			<input type="hidden" name="formName" value="indexB"> <input
				type="submit" value="Restart Form B">
		</form>
	</div>
	<%
	} else {
	%>
	<div class="center_error">
		<h3>Click below to restart User Registration FormB:</h3>
		<form action="RegistrationController" method="post">
			<input type="hidden" name="formName" value="indexB"> <input
				type="submit" value="Restart Form B">
		</form>
	</div>
	<%
	}
	%>
	<%} else {%>
	<%if (javaBean.isUserAddedToDB() != null) {%>
	<%if (javaBean.isUserAddedToDB() == true) {%>
	<%--registration success page--%>
	<h1>Congrats!</h1>
	<div class="center_congrats">
		<h1>You are registered!</h1>
		<div class="text">
			<p>
				Dear <span><%=javaBean.getFirstName()%></span>, you have been
				registered under UserId: <span><%=javaBean.getUserId()%></span>
			</p>
			<p>
				A confirmation email will be sent to: <span><%=javaBean.getEmail()%></span>
			</p>
			<p>Click below to go to the sign-in page:</p>
		</div>
		<form action="RegistrationController" method="post">
			<input type="hidden" name="formName" value="signIn"> <input
				type="submit" value="Sign-in">
		</form>
	</div>
	<%} else {%>
	<%--registration failed. userId already exists--%>
	<h1>Sorry, this user is already registered.</h1>
	<div class="center">
		<h1>If you already have an account, please log in:</h1>
		<form action="RegistrationController" method="post">
			<div class="txt_field">
				<input type="text" onkeypress="return event.charCode != 32"
					name="loginId" minlength="8" maxlength="16" required> <span></span>
				<label>User Id</label>
			</div>
			<br>
			<div class="txt_field">
				<input type="password" onkeypress="return event.charCode != 32"
					name="loginPassword" minlength="8" maxlength="16" required>
				<span></span> <label>Password</label>
			</div>
			<br>
			<div class="login_reset">
				<input type="hidden" name="formName" value="login"> <input
					type="submit" value="Login"> <input type="reset"
					name="formName" value="Reset">
			</div>
		</form>
		<div class="register_text">
			<h3>New users, please register first:</h3>
			<form action="RegistrationController" method="post">
				<div class="register_button">
					<input type="hidden" name="formName" value="register"> <input
						type="submit" value="Register">
				</div>
			</form>
		</div>
	</div>
	<%
	}
	%>
	<%} else {%>
	<%--registration failed--%>
	<h1>Sorry, unable to register you.</h1>
	<div class="center">
		<h1>If you already have an account, please log in:</h1>
		<form action="RegistrationController" method="post">
			<div class="txt_field">
				<input type="text" onkeypress="return event.charCode != 32"
					name="loginId" minlength="8" maxlength="16" required> <span></span>
				<label>User Id</label>
			</div>
			<br>
			<div class="txt_field">
				<input type="password" onkeypress="return event.charCode != 32"
					name="loginPassword" minlength="8" maxlength="16" required>
				<span></span> <label>Password</label>
			</div>
			<br>
			<div class="login_reset">
				<input type="hidden" name="formName" value="login"> <input
					type="submit" value="Login"> <input type="reset"
					name="formName" value="Reset">
			</div>
		</form>
		<div class="register_text">
			<h3>New users, please register first:</h3>
			<form action="RegistrationController" method="post">
				<div class="register_button">
					<input type="hidden" name="formName" value="register"> <input
						type="submit" value="Register">
				</div>
			</form>
		</div>
	</div>
	<%
	}
	%>
	<%
	}
	%>
	<%
	}
	%>
	<%
	}
	%>
	<%
	}
	%>
	<%
	}
	%>
	<%
	}
	%>

	<%
	}
	%>

</body>
</html>