<%--@author: Varun Kashyap--%>
<%--FileName: Status.jsp--%>
<%--Date: 06/28/2021--%>
<%--Description: MVC View for assignment6 User Registration Status report page --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import = "me.varunkashyap.CoursesSupportBean" %>
    <%@page import = "java.util.List" %>
    <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SRS</title>
<style>
	<%@ include file="resources/css/style.css"%>
</style>
</head>
<body>
<h1>Registration Status Report</h1>
<%List<String> statusList = (List<String>)session.getAttribute("statusList"); %>
<%String statusMessage = (String)session.getAttribute("statusMessage"); %>
<% if(statusList == null) {%>
<div class="center_admin">
<h1>Check any specific course(s) and click on Submit:</h1>
<div class="form_field">
<form action="RegistrationController" method="post">
<jsp:useBean id="CoursesSupportBean" class="me.varunkashyap.CoursesSupportBean" scope="session">
</jsp:useBean>
<%List<String> list = CoursesSupportBean.getCourseList();%>
    <%for(String course : list){%>
    	<div class="check_field">
        <input type="checkbox" name="courseCheckbox" id="<%=course%>" value="<%=course%>">
		<span></span>
		<label for="<%=course%>"><%=course%></label>
		</div>
		<br>
    <%} %>
    </div>
    <div class="submit">
	<input type="hidden" name="formName" value="statusReport">
	<input type="submit" value="Submit">
	</div>
</form>
<div class="logout">
	<p>or <span><a href="logout.jsp">Logout</a></span> here.</p>
</div>
</div>
<%} else {%>
	<div class="center_admin_result">
	<h1><%=statusMessage %></h1>
	<div class="status_result">
	<%for(String course : statusList){%>
        <p><%=course%></p>
        <br>
    <%} %> 
    </div>
    <div class="submit">
    <form action="RegistrationController" method="post">
	<input type="hidden" name="formName" value="backToStatus">
	<input type="submit" value="Back">
	</form>
	</div>
	</div>
<%} %>
</body>
</html>