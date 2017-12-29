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

		<h3>Edit the following shipment:</h3>
		<table class="table table-dark table-hover text-center">
    		<thead>
      		  <tr>
        	    <th>Id</th>
        		<th>Origin</th>
        		<th>Destination</th>
        		<th>Part</th>
        		<th>Part number</th>
        		<th>Serial number</th>
        		<th>Shipped</th>
        		<th>Arrived</th>
        		<th>Tracking info</th>
      		  </tr>
    		</thead>
    		<tbody>
			  <tr>
				<td><c:out value="${shipment.id}"/></td>
				<td><c:out value="${shipment.origin.name}"/></td>
				<td><c:out value="${shipment.destination.name}"/></td>
				<td><c:out value="${shipment.sparePart.name}"/></td>
				<td><c:out value="${shipment.sparePart.partNumber}"/></td>
				<td><c:out value="${shipment.sparePart.serialNumber}"/></td>
				<td><c:out value="${shipment.dayDate}"/></td>
				<td><c:out value="${shipment.dateArrived}"/></td>
				<td><c:out value="${shipment.trackingInfo}"/></td>
			  </tr>
			</tbody>
		  </table>
		<form:form method="post" modelAttribute="shipment">
		<div class="form-group">Destination: 
		  <form:select type="text" path="destination" cssClass="form-control">
		  <form:options items="${localLocations}" itemValue="id" itemLabel="name" />
		</form:select> <form:errors path="destination"/> </div>
		
		<div class="form-group">Date shipped: <form:input class="form-control" path="dateShipped"/>
		<form:errors path="dateShipped" /></div>
		
		<div class="form-group">Tracking info: <form:input path="trackingInfo" cssClass="form-control"/>
		<form:errors path="trackingInfo" /></div>
		<input type="submit" class="btn btn-primary" value"Edit shipment">
		</form:form>
	<%@include file="../jspf/foot_config.jspf"%>
</body>
</html>