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
	<%@include file="../jspf/part_menu.jspf"%>
	

		<h3>List of all spare parts removed from remote systems:</h3>
		
		<label for="myInput">Search the table:</label>
		<input type="text" id="myInput" onkeyup="searchTable()" 
			placeholder="Type item to be found..." title="Type in searched phrase">
		
		
		  <table class="table table-dark table-hover text-center">
    		<thead>
      		  <tr>
        	    <th onclick="sortTable(0)">Name</th>
        		<th onclick="sortTable(1)">Manufacturer</th>
        		<th onclick="sortTable(2)">Part number</th>
        		<th onclick="sortTable(3)">Serial number</th>
        		<th onclick="sortTable(4)">Location</th>
        		<th onclick="sortTable(5)">Status</th>
        		<th onclick="sortTable(6)">Storage/system location</th>
      		  </tr>
    		</thead>
    		<tbody id="myTable">
		<c:forEach items="${spareParts}" var="sparePart">
			  <tr>
				<td><c:out value="${sparePart.partCatalog.name}"/></td>
				<td><c:out value="${sparePart.partCatalog.manufacturer.name}"/></td>
				<td><c:out value="${sparePart.partCatalog.partNumber}"/></td>
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
	</div>
<%@include file="../jspf/foot_config.jspf"%>
</body>
</html>