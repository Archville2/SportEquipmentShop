<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>error page</title>
<link href="<c:url value="css/mix.css" />" rel="stylesheet">

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="localization.locale" var="loc" />
<fmt:message bundle="${loc}" key="locale.main_button" var="main" />
<fmt:message bundle="${loc}" key="locale.logo_map" var="logo_map" />
<fmt:message bundle="${loc}" key="locale.logo_about" var="logo_about" />
</head>
<body>

	<table border="0" width="100%">
		<tr align="center">
			<td width="20%"></td>
			<td width="60%"><img src="img/shop_logo.gif"></td>
			<td width="20%" valign="top" align="right">
				<form action="Controller" method="post">
					<input type="hidden" name="command" value="change_language" /> 
					<input type="image" name="locale" value="en" src="img/uk_flag.png" width="32" height="32" /> 
					<input type="image" name="locale" value="ru" src="img/ru_flag.png" width="32" height="32" />
				</form>
			</td>
		</tr>
	</table>
	<br>
	<table border="0" bgcolor="f0f0f0" width="100%">
		<tr align="center">
			<td width="33%">
				<form action="Controller" method="post">
					<input type="hidden" name="command" value="show_main_page" /> 
					<input class="new" type="submit" value="${main}" style="width: 150Px">
				</form>
			</td>
			<td width="33%"><p>${logo_map}</p></td>
			<td width="33%"><p>${logo_about}</p></td>
		</tr>
	</table>

	<table border="0" bgcolor="ffffff" width="100%">
		<tr align="center">
			<td width="100%">
				<p>${message}</p>
			</td>
		</tr>
	</table>

</body>
</html>