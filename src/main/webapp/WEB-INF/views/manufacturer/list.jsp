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

		<h3>List of all manufacturers:</h3>
		  <table class="table table-dark table-hover text-center">
    		<thead>
      		  <tr>
        	    <th>Name</th>
        		<th>Return Address</th>
        		<th>Additional info</th>
      		  </tr>
    		</thead>
    		<tbody>
		<c:forEach items="${availableManufacturers}" var="manufacturer">
			  <tr>
				<td><c:out value="${manufacturer.name}"/></td>
				<td><c:out value="${manufacturer.returnAddress}"/></td>
				<td><c:out value="${manufacturer.additionalInfo}"/></td>
    			<c:if test="${sessionScope.user.userRole eq 'ROLE_ADMIN'}">
				<td>
				  <div class="dropdown">
    				<button type="button" class="btn btn-primary dropdown-toggle btn-secondary" data-toggle="dropdown">
      				Actions</button>
    				<div class="dropdown-menu">
      				<a class="dropdown-item" href="${manufacturer.id}/edit">Edit</a>
      				<a class="dropdown-item" href="${manufacturer.id}/delete">Delete</a>
    				</div>
  				 </div>
				</td>
				</c:if>
			  </tr>
			</c:forEach>  
			</tbody>
		  </table>
		  
	</div>
<%@include file="../jspf/foot_config.jspf"%>
</body>
</html>