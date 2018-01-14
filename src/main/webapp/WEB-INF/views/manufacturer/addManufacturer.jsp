<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="../jspf/includes.jspf"%>
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
		<form:form method="post" modelAttribute="manufacturer">
		<div class="form-group">Name: <form:input path="name" cssClass="form-control"/>
		<form:errors path="name" /></div>
		<div class="form-group">Return address: <form:input path="returnAddress" cssClass="form-control"/>
		<form:errors path="returnAddress" /></div>
		<div class="form-group">Additional info: <form:input path="additionalInfo" cssClass="form-control"/>
		<form:errors path="additionalInfo" /><br /></div>
		<input type="submit" class="btn btn-primary" value="Create new manufacturer">
		</form:form><br /> 
	</div>
	<%@include file="../jspf/foot_config.jspf"%>
</body>
</html>