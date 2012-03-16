<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User info</title>
</head>
<body>
	<b>Code: </b> ${code}
	<br>
	<b>Access token: </b> ${accessToken}
	<br>
	<b>Expires in: </b> ${expiresIn}
	<br>
	<b>User ID: </b> ${userId}
	<br>
	<br>
	<img src=${userInfo.photo50URL }>
	<br>
	<b>User ID: </b> <c:out value="${userInfo.userId}">No ID</c:out> 
	<br>
	<b>First name: </b> <c:out value="${userInfo.firstName}">No name</c:out> 
	<br>
	<b>Last name: </b> <c:out value="${userInfo.lastName}">No name</c:out>
	<br>
	<b>Sex: </b> <c:out value="${userInfo.userSex}">No sex</c:out>
	<br>
	<b>Birth date: </b> <c:out value="${userInfo.birthDate}">No date</c:out>
	<br>
	<b>Mobile phone: </b> <c:out value="${userInfo.mobilePhone }">No phone</c:out> 
	<br>
	<%-- ${userInfo }--%>
</body>
</html>