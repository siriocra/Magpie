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
	<table width="100%">
	<tr>
		<td valign="top">
			<img src=${userInfo.photo100URL }>
			<br>
			<i><c:out value="${userInfo.online}">No info</c:out></i>
			<br>
			<a href="http://vk.com/${userInfo.screenName}">
			<c:out value="${userInfo.firstName}">No name</c:out>
			<c:out value="${userInfo.nickname}">No name</c:out>
			<c:out value="${userInfo.lastName}">No name</c:out>
			</a>
			<br>
			<b>Sex: </b> <c:out value="${userInfo.userSex}">No sex</c:out>
			<br>
			<b>Birth date: </b> <c:out value="${userInfo.birthDate}">No date</c:out>
			<br>
			<b>City: </b> <c:out value="${userInfo.city}">Not specified</c:out>
			<br>
			<b>Country: </b> <c:out value="${userInfo.country}">Not specified</c:out>
			<br>
			<b>Mobile phone: </b> <c:out value="${userInfo.mobilePhone }">No phone</c:out>
			<br> 
			<b>Home phone: </b> <c:out value="${userInfo.homePhone }">No phone</c:out>
		</td>
		<td>
			<table border=1 align="center" width="70%">
				<caption><b>Friends</b></caption>
				<c:forEach var="friend" items="${friends}">
				<tr>
					<td>
					<img src=${friend.photo50URL }>
					<i><c:out value="${friend.online}">No info</c:out></i>
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
		</td>
	</tr>
	</table>
</body>
</html>