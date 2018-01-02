<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
      <c:if test="${sessionScope.user.userRole eq 'ROLE_ADMIN'}">

		<h3 class="text-center">Are you sure you want to remove the following part from remote system:</h3>
		  <table class="table table-dark table-hover text-center">
    		<thead>
      		  <tr>
				<th>Name</th>
        		<th>Manufacturer</th>
        		<th>Part number</th>
        		<th>Serial number</th>
        		<th>Location</th>
        		<th>Status</th>
        		<th>Storage/system location</th>      		  </tr>
    		</thead>
    		<tbody>
			  <tr>
				<td><c:out value="${sparePart.name}"/></td>
				<td><c:out value="${sparePart.manufacturer.name}"/></td>
				<td><c:out value="${sparePart.partNumber}"/></td>
				<td><c:out value="${sparePart.serialNumber}"/></td>
				<td><c:out value="${sparePart.currentLocation.name}"/></td>
				<td><c:out value="${sparePart.currentStatus}"/></td>
				<td><c:out value="${sparePart.currentStorageLocation}"/></td>
			  </tr>
			</tbody>
		  </table>
		  <div class="text-center">
		    <h3>Please indicate the storage location for part after removing it from system</h3>
		  
		  	<form:form method="post" modelAttribute="sparePart">
		  	<div class="form-group"><form:input path="currentStorageLocation" cssClass="form-control"/>
			<form:errors path="currentStorageLocation" /></div>
			<input type="submit" value="Confirm" class="btn btn-primary btn-success btn-lg"/> 
			<input type="button" value="Cancel" onclick="history.back()" 
						class="btn btn-primary btn-danger btn-lg">
			</form:form>
		  </div>
	  </c:if>
	</div>
<%@include file="../jspf/foot_config.jspf"%>
</body>
</html>