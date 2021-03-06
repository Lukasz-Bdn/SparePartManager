<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="../jspf/includes.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Homepage</title>
<%@include file="../jspf/head_config.jspf"%>
</head>
<body>
<%@include file="../jspf/main_menu.jspf"%>
	<div class="container">

		<h3 class="text-center">Confirm the arrival of the following item:</h3>
		  <table class="table table-dark table-hover text-center">
    		<thead>
      		  <tr>
				<th>Name</th>
        		<th>Manufacturer</th>
        		<th>Part number</th>
        		<th>Serial number</th>
        		<th>Location</th>
        		<th>Status</th>
      		  </tr>
    		</thead>
    		<tbody>
			  <tr>
				<td><c:out value="${sparePart.partCatalog.name}"/></td>
				<td><c:out value="${sparePart.partCatalog.manufacturer.name}"/></td>
				<td><c:out value="${sparePart.partCatalog.partNumber}"/></td>
				<td><c:out value="${sparePart.serialNumber}"/></td>
				<td><c:out value="${sparePart.currentLocation.name}"/></td>
				<td><c:out value="${sparePart.currentStatus}"/></td>
			  </tr>
			</tbody>
		  </table>
		  <br/>
		  	<h3>Please indicate the current storage location for received item:</h3>
		  	<form:form method="post" modelAttribute="sparePart" class="form-inline">
			  <td>
			  	<div class="form-group">Name: <form:input path="currentStorageLocation" cssClass="form-control"/>
				<form:errors path="currentStorageLocation" /></div>
		  		<input type="submit" class="btn btn-primary" value="Submit">
			</form:form>

	</div>
<%@include file="../jspf/foot_config.jspf"%>
</body>
</html>