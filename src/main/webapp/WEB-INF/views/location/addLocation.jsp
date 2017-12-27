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
		Name: <form:input path="name" /><form:errors path="name" /><br />
		Description: <form:input path="description" /><form:errors path="description" /><br />
		Address: <form:input path="address" /><form:errors path="address" /><br />
		Location type:
		Global:	<form:radiobutton path="global" value="true"/>
		Remote:	<form:radiobutton path="global" value="false"/>
		<input type="submit">
		</form:form><br /> 
		${location}
	</div>
	<%@include file="../jspf/foot_config.jspf"%>
</body>
</html>