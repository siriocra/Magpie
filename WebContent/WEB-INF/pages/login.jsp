<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Trying to connect</title>
</head>
<body>
	<h1>${tryHello}</h1>
	<script language="javascript">
		function showWindow() {

			var url = "https://oauth.vk.com/authorize?"
					+ "client_id=2837023&"
					+ "&scope=friends,groups,offline"
					+ "&redirect_uri=http://keers.jelastic.servint.net:8080/Magpie/auth&"
					+ "&display=page";

			window.open(url, 'ReasonWindow', 'height=500 width=700');
		}
	</script>
	<a href="#" onClick="showWindow()">Login</a>
	<br />
</body>
</html>
