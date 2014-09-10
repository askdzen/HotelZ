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

                <li  class="active"> Выберите дату заезда:
                    <input id="calendar" type="date" name="calendar" value="2014-08-11"
                           max="2014-10-09" min="2014-08-11">
                </li>
                <li  class="active"> Выберите дату выезда:
                    <input id="calendar2" type="date" name="calendar2" value="2014-08-15"
                           max="2014-10-09" min="2012-08-11">
                </li>
                <li>

                    <select class="form-control" name="roomtype">
                        <option name="roomtype" value="Standart">Standart</option>
                        <option name="roomtype" value="Econom">Econom</option>
                        <option name="roomtype" value="Deluxe">Deluxe</option>

                    </select>
                </li>
                <li>

                    <select class="form-control" name="bedNo">
                        <option name="bedNo" value="Single">Single</option>
                        <option name="bedNo" value="Double">Double</option>
                        <option name="bedNo" value="Studio">Studio</option>

                    </select>

                </li>
            </ul>

            <button id="changeBtn" type="submit" class="b1" >Check Room</button>
            <label id="message">${nullrooms}</label>
        </form>

    </jsp:body>
</t:genericpage>

</html>
