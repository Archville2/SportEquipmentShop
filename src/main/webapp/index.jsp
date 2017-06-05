<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sport Equipment Rent Shop</title>
<link href="<c:url value="css/mix.css" />" rel="stylesheet">

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="localization.locale" var="loc" />
<fmt:message bundle="${loc}" key="locale.registration" var="reg_button" />
<fmt:message bundle="${loc}" key="locale.login" var="login_button" />
<fmt:message bundle="${loc}" key="locale.login_text" var="login_text" />
<fmt:message bundle="${loc}" key="locale.password_text"	var="password_text" />
<fmt:message bundle="${loc}" key="locale.logo_map" var="logo_map" />
<fmt:message bundle="${loc}" key="locale.logo_about" var="logo_about" />
<fmt:message bundle="${loc}" key="locale.main_button" var="main" />

</head>

<c:if test="${sessionScope.user != null}">
	<c:redirect url="Controller?command=show_main_page"/>
</c:if>

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
				<input class="old" type="submit" value="${main}" style="width: 150Px">
			</td>
			<td width="33%"><p>${logo_map}</p></td>
			<td width="33%"><p>${logo_about}</p></td>
		</tr>
	</table>
	<br>

	<table border="0" width="100%">
	<tr>
	<td width="100%" align="center">
		<c:if test = "${message == 'login_incorrect'}">
			<p>пользователи с таким логином и паролем не найдены</p>
		</c:if>
	</td>
	<td>
	<form action="Controller" method="post">
		<table border="0" bgcolor="d0d0d0" width="100%">
			<tr align="center">
				<td width="100%"><input type="text" name="login" value="" size="20"><br>${login_text}</td>
			</tr>
			<tr align="center">
				<td width="100%"><input type="password" name="password" value="" size="20"><br>${password_text}</td>
			</tr>
		</table>
	

		<table border="0" bgcolor="d0d0d0" width="100%">
			<tr align="center">
				<td>
					<input type="hidden" name="command" value="login_client" /> 
					<input class="new" type="submit" value="${login_button}" style="width: 120Px">
				</td>
			</tr>
		</table>	
	</form>

	<form action="Controller" method="post">
		<table border="0" bgcolor="d0d0d0" width="100%">
			<tr align="center">
				<td align="center">
				<input type="hidden" name="command"	value="show_reg_page" /> 
				<input class="new" type="submit" value="${reg_button}" style="width: 120Px"></td>
			</tr>
		</table>
	</form>
	</td>
	</tr>
</table>

</body>
</html>