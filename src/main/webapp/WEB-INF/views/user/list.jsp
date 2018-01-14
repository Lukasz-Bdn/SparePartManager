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
		<h3>List of all manufacturers:</h3>
		
		<label for="myInput">Search the table:</label>
		<input type="text" id="myInput" onkeyup="searchTable()" 
			placeholder="Type item to be found..." title="Type in searched phrase">
		
		  <table class="table table-dark table-hover text-center">
    		<thead>
      		  <tr>
        	    <th onclick="sortTable(0)">Username</th>
        		<th onclick="sortTable(1)">Email</th>
        		<th onclick="sortTable(2)">Enabled</th>
        		<th onclick="sortTable(3)">Role</th>
        		<th onclick="sortTable(4)">Location</th>
      		  </tr>
    		</thead>
    		<tbody id="myTable">
		<c:forEach items="${userList}" var="user">
			  <tr>
				<td><c:out value="${user.username}"/></td>
				<td><c:out value="${user.email}"/></td>
				<td><c:out value="${user.enabled}"/></td>
				<td><c:out value="${user.userRole}"/></td>
				<td><c:out value="${user.location.name}"/></td>				
				<td>
				  <div class="dropdown">
    				<button type="button" class="btn btn-primary dropdown-toggle btn-secondary" data-toggle="dropdown">
      				Actions</button>
    				<div class="dropdown-menu">
      				<a class="dropdown-item" href="${user.id}/edit">Edit</a>
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