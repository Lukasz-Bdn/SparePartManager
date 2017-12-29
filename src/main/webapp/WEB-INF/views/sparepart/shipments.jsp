<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<%@include file="../jspf/part_menu.jspf"%>
	

		<h3>List of all shipments:</h3>
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
		<c:forEach items="${shipmentsToLocation}" var="shipment">
			  <tr>
				<td><c:out value="${shipment.id}"/></td>
				<td><c:out value="${shipment.origin.name}"/></td>
				<td><c:out value="${shipment.destination.name}"/></td>
				<td><c:out value="${shipment.sparePart.name}"/></td>
				<td><c:out value="${shipment.sparePart.partNumber}"/></td>
				<td><c:out value="${shipment.sparePart.serialNumber}"/></td>
				<td><c:out value="${shipment.simpleShippedDate}"/></td>
				<td><c:out value="${shipment.simpleArrivedDate}"/></td>
				<td><c:out value="${shipment.trackingInfo}"/></td>
				<td>
				  <div class="dropdown">
    				<button type="button" class="btn btn-primary dropdown-toggle btn-secondary" data-toggle="dropdown">
      				Actions</button>
    				<div class="dropdown-menu">
      				<a class="dropdown-item" href="${shipment.id}/shipments/edit">Edit</a>
      				<a class="dropdown-item" href="${shipment.id}/shipments/cancel">Cancel</a>
      				<a class="dropdown-item" href="${shipment.id}/shipments/arrivedToLocation">Mark as arrived</a>
    				</div>
  				 </div>
				</td>
			  </tr>
			</c:forEach>  
			</tbody>
		  </table>
		  
	</div>
<%@include file="../jspf/foot_config.jspf"%>
</body>
</html>