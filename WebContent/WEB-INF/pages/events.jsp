<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>All your events</title>
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
	<p>
		<c:forEach var="event" items="${events.events}">
		${event.name } <br>
		</c:forEach>
	</p>
</body>
</html>