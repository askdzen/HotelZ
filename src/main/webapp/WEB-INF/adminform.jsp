<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Admin Main page</title>

</head>
<t:genericpage>
  <jsp:attribute name="header">
<div id="statusbarcontent">
    <p id="welcome"> Welcome, ${user.username} ! </p>
    <a id="account" href="/welcome">Выйти</a>

</div>
    </jsp:attribute>
<jsp:attribute name="accordion"/>
<jsp:attribute name="carousel"/>
<jsp:attribute name="footer"/>
<jsp:body>

    <button  onClick='location.href="/bookingtable"'   type="submit" class="adminbtn">Редактирование Журнала бронирования</button>
    <hr>
    <button  onClick='location.href="/customerdetail"'  type="submit" class="adminbtn">Редактирование данных клиентов</button>
    <hr>
    <button  onClick='location.href="/roomdetail"' type="submit" class="adminbtn">Редактирование параметров комнат</button>
    <hr>
    <button  onClick='location.href="/userdetail"' type="submit" class="adminbtn">Редактирование учетных записей пользователей</button>
    <hr>
</jsp:body>
</t:genericpage>
</html>
