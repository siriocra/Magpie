<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome!</title>
</head>
<body>
	<%-- System information<br>
	<b>Code: </b> ${code}
	<br>
	<b>Access token: </b> ${accessToken}
	<br>
	<b>Expires in: </b> ${expiresIn}
	<br>
	<b>User ID: </b> ${userId}
	<br>--%>
<a href="/Magpie/about">About this application</a>
<br>
<table width="90%" align="center">
	<tr>
		<td valign="top">
			<img src=${userInfo.photo200URL }>
			<br>
			<i>
			<%-- c:out value="${userInfo.online}">No info</c:out--%>
				<c:if test = "${userInfo.online eq 'online'}"> online </c:if>
			</i>
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
		<td valign="top">
			<h1>What do you want to do?</h1>
			<br>
			<a href="/Magpie/showfriends?code=${code }&user_id=${userId }">View my friends' contacts</a>
			<br>
			<a href="/Magpie/events?code=${code }&access_token=${accessToken }&user_id=${userId }">View all my events</a>
			<br>
			<a href="/Magpie/refreshfriends?code=${code }&access_token=${accessToken }&user_id=${userId }">Refresh my friends' contacts</a>
		</td>
	</tr>
</table>
</body>
</html>