<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<html>
<head>

    <title>Welcome page</title>
</head>

<t:genericpage>

    <jsp:attribute name="header">
<div id="statusbarcontent">
   <p id="welcome"> Welcome, ${user.username} !</p>
       <a id="account" href="/account">Вход в личный кабинет</a>
    <br/>




</div>
    </jsp:attribute>

    <jsp:attribute name="accordion">
<t:accordion>
    <jsp:attribute name="accordion">

    </jsp:attribute>
</t:accordion>
    </jsp:attribute>
    <jsp:attribute name="carousel">
        <t:carousel>
            <jsp:attribute name="carousel"/>
        </t:carousel>
    </jsp:attribute>

    <jsp:attribute name="footer">

    </jsp:attribute>
    <jsp:body>

        <form class="form-inline" method="post">

            <ul id="change" class="nav nav-tabs">

                <li class="active">  Выберите дату заезда:
                    <input type="date" name="calendar" value="2014-08-11"
                           max="2014-10-09" min="2014-08-11">
                </li>
                <li class="active">  Выберите дату выезда:
                    <input type="date" name="calendar2" value="2014-08-15"
                           max="2014-10-09" min="2012-08-11">
                </li>

                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        Выберите тип номера <span class="caret"></span>
                    </a>

                    <ul class="dropdown-menu" role="menu">
                        <li><label class="checkbox"><input type="checkbox" name="roomtype" value="AC"> AC</label></li>

                        <li><label class="checkbox"><input type="checkbox" name="roomtype" value="Non AC">NonAc</label></li>

                    </ul>
                </li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        Выберите кол-во мест <span class="caret"></span>
                    </a>

                    <ul class="dropdown-menu" role="menu">

                        <li><label class="checkbox"><input type="checkbox" name="bedNo" value="Single">Single</label></li>

                        <li><label class="checkbox"><input type="checkbox" name="bedNo" value="Double">Double</label></li>

                    </ul>
                </li>
            </ul>
            <label for="changeBtn">${nullrooms}</label>
            <button id="changeBtn" type="submit" class="btn">Check Room</button>




                <%--  <button type="submit" class="btn">Book Room</button>--%>
        </form>

    </jsp:body>
</t:genericpage>

</html>
