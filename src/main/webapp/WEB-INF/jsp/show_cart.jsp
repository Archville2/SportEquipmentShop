<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Cart</title>
<link href="<c:url value="css/mix.css" />" rel="stylesheet">

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="localization.locale" var="loc" />
<fmt:message bundle="${loc}" key="locale.main_button" var="main" />
<fmt:message bundle="${loc}" key="locale.logo_map" var="logo_map" />
<fmt:message bundle="${loc}" key="locale.logo_about" var="logo_about" />
</head>
<body>

<iframe width="0" height="0" name="dummyframe" id="dummyframe" style="visibility:hidden"></iframe>

<jsp:include page="shop_logo.jsp" />
	
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

	<table border="0" width="100%">
    <tr>
        <td width="100%" valign="top">
			<c:forEach var="field" items="${cart}">
				<table border="2" width="95%" bordercolor="D0D0D0">
					<tr align="center">
						<td rowspan="2" width="20%"><img src="img/${field.img}"></td>
						<td width="20%">Тип: ${field.type}</td>
						<td width="20%">Название: ${field.name}</td>
						<td width="20%">Производитель: ${field.manufacturer}</td>
						<td width="20%">Цена за 30 дней: ${field.price} руб.</td>
					</tr>
					<tr align="center">
						<td colspan="3" width="80%">${field.description}</td>
						<td>
							<form action="Controller" target="dummyframe" method="post">
							<input type="hidden" name="command" value="rent_item" />
							<input type="hidden" name="equipment_id" value="${field.id}" />
							<input type="hidden" name="client_id" value="${user.id}" />
							срок проката (дней)
							<select name="days" size="1">
								<option value="30">30</option>
								<option value="60">60</option>
								<option value="90">90</option>
							</select><br>
							<input type="submit" name="cart" value="Оформить" /> 
							</form>
							<form action="Controller" method="post">
							<input type="hidden" name="command" value="del_from_cart" />
							<input type="hidden" name="equipment_id" value="${field.id}" />
							<input type="hidden" name="client_id" value="${user.id}" />
							<input type="submit" name="cart" value="X" />
							</form>
						</td>
					</tr>
				</table>
				<br>
			</c:forEach>
			</td>
			
		<jsp:include page="right_menu.jsp">
        <jsp:param name="linked_page" value="show_cart" />
        </jsp:include>
        
    </tr>
    
</table>

</body>
</html>