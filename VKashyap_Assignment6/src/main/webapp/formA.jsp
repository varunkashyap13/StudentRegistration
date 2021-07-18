<%--@author: Varun Kashyap--%>
<%--FileName: formA.jsp--%>
<%--Date: 06/28/2021--%>
<%--Description: MVC View for assignment6 User Registration formA --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Registration</title>
<style>
<%@ include file="resources/css/style.css"%>
</style>
</head>
<body>
	<h1>Registration</h1>
	<div class="center_A">
	<h1>Form A</h1>
	<form action="RegistrationController" method="post">
	<div class="column_left">
	<div class="txt_field">
		<input type="text" name="userId" onkeypress="return event.charCode != 32" minlength="8" maxlength="16"required>
		<span></span>
		<label>UserId</label>
		</div>
		<div class="txt_field">
		<input type="password" onkeypress="return event.charCode != 32" name="password1" minlength="8" maxlength="16" required>
		<span></span>
		<label>Password</label>
		</div>
		<div class="txt_field">
		<input type="password" onkeypress="return event.charCode != 32" name="password2" minlength="8" maxlength="16" required>
		<span></span>
		<label>Password(repeat)</label>
		</div>
		<div class="txt_field">
		<input type="text" name="firstName" required>
		<span></span>
		<label>First Name</label>
		</div>
		<div class="txt_field">
		<input type="text" name="lastName" required>
		<span></span>
		<label>Last Name</label>
		</div>
		</div>
		<div class="column_right">
		<div class="ss">
		<div class="txt_field">
		<input type="password" name="ss1" size="3" minlength="3" maxlength="3" required>
		<span></span>
		<label>ss1: ###</label>
		</div>
		<div class="txt_field">
		<input type="password" name="ss2" size="2" minlength="2" maxlength="2" required>
		<span></span>
		<label>ss2: ##</label>
		</div>
		<div class="txt_field">
		<input type="password" name="ss3" size="4" minlength="4" maxlength="4" required>
		<span></span>
		<label>ss3: ####</label>
		</div>
		</div>
		<div class="txt_field">
		<input type="email" name="email" required>
		<span></span>
		<label>Email</label>
		</div>
		<div class="submit_A">
		<input type="hidden" name="formName" value="formA">
		<input type="submit" value="continue">
		</div>
		</div>
		</div>
	</form>
	</div>
</body>
</html>