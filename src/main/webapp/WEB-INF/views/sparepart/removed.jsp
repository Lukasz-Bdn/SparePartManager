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
    <c:if test="${sessionScope.user.enabled}">
	<%@include file="../jspf/part_menu.jspf"%>
	

		<h3>List of all spare parts removed from remote systems:</h3>
		  <table class="table table-dark table-hover text-center">
    		<thead>
      		  <tr>
        	    <th>Name</th>
        		<th>Manufacturer</th>
        		<th>Part number</th>
        		<th>Serial number</th>
        		<th>Location</th>
        		<th>Status</th>
        		<th>Storage/system location</th>
      		  </tr>
    		</thead>
    		<tbody>
		<c:forEach items="${spareParts}" var="sparePart">
			  <tr>
				<td><c:out value="${sparePart.name}"/></td>
				<td><c:out value="${sparePart.manufacturer.name}"/></td>
				<td><c:out value="${sparePart.partNumber}"/></td>
				<td><c:out value="${sparePart.serialNumber}"/></td>
				<td><c:out value="${sparePart.currentLocation.name}"/></td>
				<td><c:out value="${sparePart.currentStatus}"/></td>
				<td><c:out value="${sparePart.currentStorageLocation}"/></td>
				<td>
				  <div class="dropdown">
    				<button type="button" class="btn btn-primary dropdown-toggle btn-secondary" data-toggle="dropdown">
      				Actions</button>
    				<div class="dropdown-menu">
      				<a class="dropdown-item" href="${sparePart.id}/tolocal">
      					Return to local stock</a>
      				<a class="dropdown-item" href="${sparePart.id}/toglobal">
      					Ship to global stock</a>
    				</div>
  				 </div>
				</td>
			  </tr>
			</c:forEach>  
			</tbody>
		  </table>
	  </c:if>
	</div>
<%@include file="../jspf/foot_config.jspf"%>
</body>
</html>