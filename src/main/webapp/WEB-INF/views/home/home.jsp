<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Homepage</title>
<%@include file="../jspf/head_config.jspf"%>
<link rel="stylesheet" href="resources/css/style.css">
</head>
<body>
<%@include file="../jspf/main_menu.jspf"%>
	<div class="container">
	<c:if test="${empty sessionScope.user}">
	<h1>Welcome to spare part manager software, please log in or if you have an account. If you
	are a new user, please register.</h1>

		<h3><a href="${pageContext.request.contextPath}/login" class="btn btn-primary">Login (existing user)</a></h3>
		
		<h3><a href="${pageContext.request.contextPath}/register" class="btn btn-primary">Register (new user)</a></h3>
	</c:if>
	<c:if test="${not empty sessionScope.user}">
	<h1>Welcome to spare part manager software ${sessionScope.user.username}</h1>
	<h3><a href="${pageContext.request.contextPath}/logout" class="btn btn-primary">Logout</a></h3>
	
	</c:if>
	</div>
<%@include file="../jspf/foot_config.jspf"%>
</body>
</html>