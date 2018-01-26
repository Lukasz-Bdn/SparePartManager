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
		<h3>Add new spare part:</h3>
		<form:form method="post" modelAttribute="sparePart">

		<div class="form-group">Part from Catalog: <form:select type="text" path="partCatalog" cssClass="form-control">
		  <form:options items="${availablePartCatalogs}" itemValue="id" itemLabel="itemDescription" />
		</form:select> <form:errors path="partCatalog"/> </div>

		<div class="form-group">Serial number: <form:input path="serialNumber" cssClass="form-control"/>
		<form:errors path="serialNumber" /></div>
		<div class="form-group">Location (new items can be added only to global locations): 
		  <form:select type="text" path="currentLocation" cssClass="form-control">
		  <form:options items="${globalLocations}" itemValue="id" itemLabel="name" />
		</form:select> <form:errors path="currentLocation"/> </div>
		<div class="form-group">Current storage location: <form:input path="currentStorageLocation" 
		cssClass="form-control"/>
		<form:errors path="currentStorageLocation" /><br /></div>
		<br/>
		<input type="submit" class="btn btn-primary">
		
		</form:form><br /> 
	</div>
	<%@include file="../jspf/foot_config.jspf"%>
</body>
</html>