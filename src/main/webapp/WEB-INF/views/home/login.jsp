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

		<h3>Login:</h3>
		<form:form method="post" modelAttribute="loginData">
		<div class="form-group">Username: <form:input path="username" cssClass="form-control"/>
		<form:errors path="username" /></div>
		<div class="form-group">Password: <form:password path="password" cssClass="form-control"/>
		<form:errors path="password" /></div>
		<input type="submit" class="btn btn-primary">
		</form:form><br /> 
		</div>

	</div>
	<%@include file="../jspf/foot_config.jspf"%>
</body>
</html>