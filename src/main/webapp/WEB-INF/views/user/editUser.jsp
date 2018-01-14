<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="../jspf/includes.jspf"%>
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
		<h3>Edit user:</h3>
		<form:form method="post" modelAttribute="user">
			<div class="form-group">
				Username:
				<form:input path="username" cssClass="form-control" />
				<form:errors path="username" />
			</div>
			<div class="form-group">
				Email:
				<form:input path="email" cssClass="form-control" />
				<form:errors path="email" />
			</div>
			<div class="form-group">
				Location:
				<form:select type="text" path="location" cssClass="form-control">
					<form:options items="${locations}" itemValue="id" itemLabel="name" />
				</form:select>
				<form:errors path="location" />
			</div>
			<br />
		<div>
		Account type:
		Enabled: <form:radiobutton path="enabled" value="true" />
		Blocked: <form:radiobutton path="enabled" value="false" /></div>
			<br />	
		<div>	
		Account role:
		Admin user: <form:radiobutton path="userRole" value="ROLE_ADMIN" />
		Normal user: <form:radiobutton path="userRole" value="ROLE_USER" /></div>
			<br />
			<input type="submit" class="btn btn-primary">
		</form:form>
		<br />
	</div>
	<%@include file="../jspf/foot_config.jspf"%>
</body>
</html>