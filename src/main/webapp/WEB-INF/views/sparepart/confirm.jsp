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
	  <c:if test="${empty msg}">
		<h3 class="text-center">Are you sure you want to delete the following spare part:</h3>
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
				<td><c:out value="${sparePart.partCatalog.name}"/></td>
				<td><c:out value="${sparePart.partCatalog.manufacturer.name}"/></td>
				<td><c:out value="${sparePart.partCatalog.partNumber}"/></td>
				<td><c:out value="${sparePart.serialNumber}"/></td>
				<td><c:out value="${sparePart.currentLocation.name}"/></td>
				<td><c:out value="${sparePart.currentStatus}"/></td>
				<td><c:out value="${sparePart.currentStorageLocation}"/></td>
			  </tr>
			</tbody>
		  </table>
		  <div class="text-center">
		  	<form:form method="post" modelAttribute="sparePart">
			<input type="submit" value="Yes" class="btn btn-primary btn-success btn-lg"/> 
			<input type="button" value="No" onclick="history.back()" 
						class="btn btn-primary btn-danger btn-lg">
			</form:form>
			</c:if>
			<h2><c:out value="${msg}"></c:out></h2>
		  </div>
	</div>
<%@include file="../jspf/foot_config.jspf"%>
</body>
</html>