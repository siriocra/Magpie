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
	<%--This is system information
	<b>Code: </b> ${code}
	<br>
	<b>Access token: </b> ${accessToken}
	<br>
	<b>Expires in: </b> ${expiresIn}
	<br>
	<b>User ID: </b> ${userId}
	<br>--%>
	<table border=1 align="center" width="70%">
		<caption><b>Friends</b></caption>
		<tr>
  				<th>Photo</th>
  				<th>Name</th>
  				<th>Contacts</th>
				</tr>
		<c:forEach var="friend" items="${friends}">
		<tr>
			<td>
			<img src=${friend.photo50URL }>
			<br>
			<i>
				<c:if test = "${friend.online eq 'online'}"> online </c:if>
			<%--c:out value="${friend.online}">No info</c:out--%>
			</i>
			</td>
			<td>
			<a href="http://vk.com/${friend.screenName}">
			${friend.firstName }
			${friend.lastName }
			</a>
			</td>
			<td>
			${friend.mobilePhone }
			<br>
			${friend.homePhone }
			</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>