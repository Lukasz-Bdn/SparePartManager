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

		<h3>List of all locations:</h3>
		
		<label for="myInput">Search the table:</label>
		<input type="text" id="myInput" onkeyup="searchTable()" 
			placeholder="Type item to be found..." title="Type in searched phrase">
		
		  <table class="table table-dark table-hover text-center">
    		<thead>
      		  <tr>
        	    <th onclick="sortTable(0)">Name</th>
        		<th onclick="sortTable(1)">Description</th>
        		<th onclick="sortTable(2)">Address</th>
        		<th onclick="sortTable(3)">Global</th>
        		<th>Actions</th>
      		  </tr>
    		</thead>
    		<tbody id="myTable">
		<c:forEach items="${availableLocations}" var="location">
			  <tr>
				<td><c:out value="${location.name}"/></td>
				<td><c:out value="${location.description}"/></td>
				<td><c:out value="${location.address}"/></td>
				<td><c:out value="${location.isGlobal}"/></td>
				    
				<td>
				  <div class="dropdown">
    				<button type="button" class="btn btn-primary dropdown-toggle btn-secondary" data-toggle="dropdown">
      				Actions</button>
    				<div class="dropdown-menu">
      				<a class="dropdown-item" href="${location.id}/edit">Edit</a>
      				<a class="dropdown-item" href="${location.id}/delete">Delete</a>
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