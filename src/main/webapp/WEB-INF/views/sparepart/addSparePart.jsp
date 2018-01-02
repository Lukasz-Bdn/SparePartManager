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
	
    <c:if test="${sessionScope.user.userRole eq 'ROLE_ADMIN'}">
		<h3>Add new spare part:</h3>
		<form:form method="post" modelAttribute="sparePart">
		<div class="form-group">Name: <form:input path="name" cssClass="form-control"/>
		<form:errors path="name" /></div>
		<div class="form-group">Manufacturer: <form:select type="text" path="manufacturer" cssClass="form-control">
		  <form:options items="${availableManufacturers}" itemValue="id" itemLabel="name" />
		</form:select> <form:errors path="manufacturer"/> </div>
		<div class="form-group">Part number: <form:input path="partNumber" cssClass="form-control"/>
		<form:errors path="partNumber" /></div>
		<div class="form-group">Serial number: <form:input path="serialNumber" cssClass="form-control"/>
		<form:errors path="serialNumber" /><br /></div>
		<div class="form-group">Location (new items can be added only to global locations): 
		  <form:select type="text" path="currentLocation" cssClass="form-control">
		  <form:options items="${globalLocations}" itemValue="id" itemLabel="name" />
		</form:select> <form:errors path="currentLocation"/> </div>
		<input type="submit" class="btn btn-primary">
		</form:form><br /> 
	</c:if>
	</div>
	<%@include file="../jspf/foot_config.jspf"%>
</body>
</html>