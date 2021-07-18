<%--@author: Varun Kashyap--%>
<%--FileName: logout.jsp--%>
<%--Date: 06/28/2021--%>
<%--Description: logout page for assignment6 User Registration --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
session.invalidate();
response.sendRedirect("index.jsp");
%>
</body>
</html>