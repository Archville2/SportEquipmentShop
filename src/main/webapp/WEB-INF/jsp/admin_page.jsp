<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Admin page</title>
<link href="<c:url value="css/mix.css" />" rel="stylesheet">

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
</head>
<body>

<table border="0" width="100%">
		<tr align="right">
	     	<td><form action="Controller" method="post">
	     		<input type="hidden" name="command" value="change_language" />
	     		<input type="image" name="local" value="en" src="img/uk_flag.png" width="32" height="32"/>
	     		<input type="image" name="local" value="ru" src="img/ru_flag.png" width="32" height="32"/>
	     		</form>
	     	</td>
    	</tr>
 </table>
    
	<table border="0" width="100%">
		<tr align="center">
			<td></td>
			<td><img src="img/shop_logo.gif"></td>
			<td></td>
		</tr>
	</table>
	<br>
	<table border="0" bgcolor="f0f0f0" width="100%">
		<tr align="center">
			<td width="33%"><p>Список товаров</p></td>
			<td width="33%"><p>Карта проезда</p></td>
			<td width="33%"><p>Контактные данные</p></td>
		</tr>
	</table>
	
	<table border="0" width="100%">
    <tr>
        <td width="100%"><p align="center">&nbsp;</p>
&nbsp;        </td>
        <td align="center"><p align="center">Здравствуйте</p>
        <p align="center">Админ</p>
        <p align="center">&nbsp;</p>
        <p align="center">Меню:</p>
        <p align="center">&nbsp;</p>
        <form method="POST">
            <p><input type="submit" name="B1" value="Личный кабинет"></p>
        </form>
        <p align="center">&nbsp;</p>
        <form method="POST">
            <p><input type="submit" name="B2" value="Управление клиентами"></p>
        </form>
        <p align="center">&nbsp;</p>
        <form method="POST">
            <p><input type="submit" name="B3" value="Управление товарами"></p>
        </form>
        <p align="center">&nbsp;</p>
        <form method="POST">
            <p><input type="submit" name="B4" value="Выход"></p>
        </form>
        </td>
    </tr>
</table>
	

</body>
</html>