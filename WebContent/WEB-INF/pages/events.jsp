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
	<center>
	<table border="2" width="80%" cellpadding="20">
		<tr>
    		<th>Photo</th>
    		<th>Name</th>
    		<th>Time</th>
  		</tr>
		<c:forEach var="event" items="${events}">
		<tr>
			<td>
				<img src="${event.photo }">
			</td>
			<td>
				<a href="http://vk.com/${event.screen_name }">
					${event.name }
				</a>
			</td>
			<td>
				${event.start_date }
			</td>
		</tr>
		</c:forEach>
	</table>
	</center>
</body>
</html>