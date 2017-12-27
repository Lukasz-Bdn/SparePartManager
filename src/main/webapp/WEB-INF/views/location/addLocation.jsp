<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="../jspf/head_config.jspf"%>
</head>
<body>
<%@include file="../jspf/main_menu.jspf"%>
	<div class="container">

		<h3>Add new location:</h3>
		<form:form method="post" modelAttribute="location">
		<div class="form-group">Name: <form:input path="name" cssClass="form-control"/>
		<form:errors path="name" /></div>
		<div class="form-group">Description: <form:input path="description" cssClass="form-control"/>
		<form:errors path="description" /></div>
		<div class="form-group">Address: <form:input path="address" cssClass="form-control"/>
		<form:errors path="address" /><br /></div>
		Location type:
		Global:	<form:radiobutton path="global" value="true"/>
		Remote:	<form:radiobutton path="global" value="false"/>
		<input type="submit" class="btn btn-primary">
		</form:form><br /> 
	</div>
	<%@include file="../jspf/foot_config.jspf"%>
</body>
</html>