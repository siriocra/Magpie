<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome!</title>
</head>
<body>
	System information<br>
	<b>Code: </b> ${code}
	<br>
	<b>Access token: </b> ${accessToken}
	<br>
	<b>Expires in: </b> ${expiresIn}
	<br>
	<b>User ID: </b> ${userId}
	<br>
<h1>What do you want to do?</h1>
<br>
<a href="/Magpie/friends?code=${code }&access_token=${accessToken }&user_id=${userId }">View my friends' contacts</a>
<br>
<a href="/Magpie/events?code=${code }&access_token=${accessToken }&user_id=${userId }">View all my events</a>
</body>
</html>