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

		<h3 class="text-center">Are you sure you want to cancel the following shipment 
								and return part to global stock:</h3>
		  <table class="table table-dark table-hover text-center">
    		<thead>
      		  <tr>
        	    <th>Id</th>
        		<th>Origin</th>
        		<th>Destination</th>
        		<th>Part</th>
        		<th>Tracking info</th>
      		  </tr>
    		</thead>
    		<tbody>
			  <tr>
				<td><c:out value="${shipment.id}"/></td>
				<td><c:out value="${shipment.origin.name}"/></td>
				<td><c:out value="${shipment.destination.name}"/></td>
				<td><c:out value="${shipment.sparePart.name}"/></td>
				<td><c:out value="${shipment.trackingInfo}"/></td>
			  </tr>
			</tbody>
		  </table>
		  <div class="text-center">
		  	<form:form method="post" modelAttribute="sparePart">
			<input type="submit" value="Yes" class="btn btn-primary btn-success btn-lg"/> 
			<input type="button" value="No" onclick="history.back()" 
						class="btn btn-primary btn-danger btn-lg">
			</form:form>
		  </div>
		  
	</div>
<%@include file="../jspf/foot_config.jspf"%>
</body>
</html>