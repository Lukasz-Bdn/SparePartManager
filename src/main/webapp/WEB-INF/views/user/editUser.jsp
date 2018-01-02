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
      <c:if test="${sessionScope.user.userRole eq 'ROLE_ADMIN'}">
		<h3>Edit user:</h3>
		<form:form method="post" modelAttribute="user">
		<div class="form-group">Username: <form:input path="username" cssClass="form-control"/>
		<form:errors path="username" /></div>
		<div class="form-group">Email: <form:input path="email" cssClass="form-control"/>
		<form:errors path="email" /></div>
		Account type:
		Enabled: <form:radiobutton path="enabled" value="true"/>
		Blocked: <form:radiobutton path="enabled" value="false"/>
		<div class="form-group">Location: 
		  <form:select type="text" path="location" cssClass="form-control">
		  <form:options items="${locations}" itemValue="id" itemLabel="name" />
		</form:select> <form:errors path="location"/> </div>
		Account type:
		Admin user: <form:radiobutton path="userRole" value="ROLE_ADMIN"/>
		Normal user: <form:radiobutton path="userRole" value="ROLE_USER"/>
		<input type="submit" class="btn btn-primary">
		</form:form><br /> 
	  </c:if>
	</div>
	<%@include file="../jspf/foot_config.jspf"%>
</body>
</html>