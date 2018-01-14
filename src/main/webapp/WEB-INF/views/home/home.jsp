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
	<h1>Welcome to Spare Part Manager <b><sec:authentication property="principal.username" />
	</b>.</h1> 
	<h2>Use top menu to navigate the website</h1>
	<h3><a href="${pageContext.request.contextPath}/logout" class="btn btn-primary">Logout</a></h3>
	</div>
<%@include file="../jspf/foot_config.jspf"%>
</body>
</html>