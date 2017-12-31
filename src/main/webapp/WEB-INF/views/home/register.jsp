<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Homepage</title>
<%@include file="../jspf/head_config.jspf"%>
<link rel="stylesheet" href="resources/css/style.css">
</head>
<body>
	<%@include file="../jspf/main_menu.jspf"%>
	<div class="container">
		<h1>Spring Security Custom Login Form (Annotation)</h1>

		<div id="login-box">

			<h2>Please provide login and password for new user</h2>

			<c:if test="${not empty error}">
				<div class="error">${error}</div>
			</c:if>
			<c:if test="${not empty msg}">
				<div class="msg">${msg}</div>
			</c:if>

		<h3>Register new user:</h3>
		<form:form method="post" modelAttribute="user">
		<div class="form-group">Username: <form:input path="username" cssClass="form-control"/>
		<form:errors path="username" /></div>
		<div class="form-group">Email: <form:input path="email" cssClass="form-control"/>
		<form:errors path="email" /></div>
		
		<div class="form-group">Password: <form:password path="password" cssClass="form-control"/>
		<form:errors path="password" /></div>
		<input type="submit" class="btn btn-primary">
		</form:form><br /> 
		${user}
		</div>
	</div>
	<%@include file="../jspf/foot_config.jspf"%>
</body>
</html>