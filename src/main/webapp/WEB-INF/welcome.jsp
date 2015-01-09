<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<script type='text/javascript' src='<c:url value="/webjars/jquery/2.0.3/jquery.min.js"/>'></script>
<script type='text/javascript' src='<c:url value="/webjars/bootstrap/3.0.0/js/bootstrap.min.js"/>'></script>
<script type='text/javascript' src='<c:url value="/webjars/bootstrap/3.0.0/css/bootstrap.min.css"/>'></script>
<link href='<c:url value="/webjars/bootstrap/3.0.0/css/bootstrap.min.css"/> ' rel="stylesheet" media="screen">
<link href='<c:url value="/static/style.css" />' rel="stylesheet" media="screen">
<fmt:setLocale value="${fmtlocale}"/>
<fmt:bundle basename="i18n.message">
    <html>
    <head>

        <title><fmt:message key="welcome.title"/></title>
    </head>

    <t:genericpage>
    <jsp:attribute name="header">

<div id="statusbarcontent">

    <p id="welcome"><fmt:message key="welcomelayout.welcome"/> , ${user.username} !</p>
    <a id="account" href="/HotelReservation-1.0-SNAPSHOT/account"><fmt:message key="welcome.signin"/> </a>

    <%--<br/>--%>


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

                    <li class="active"><fmt:message key="welcome.datefrom"/>
                        <input id="calendar" type="date" name="calendar"  >
                    </li>
                    <li class="active"><fmt:message key="welcome.dateto"/>
                        <input id="calendar2" type="date" name="calendar2" >
                    </li>
                    <li>

                        <select class="form-control" name="roomtype">
                            <option name="roomtype" value="Standart"><fmt:message key="welcome.standard"/></option>
                            <option name="roomtype" value="Econom"><fmt:message key="welcome.econome"/></option>
                            <option name="roomtype" value="Deluxe"><fmt:message key="welcome.deluxe"/></option>

                        </select>
                    </li>
                    <li>

                        <select class="form-control" name="roombed">
                            <option name="roombed" value="Single"><fmt:message key="welcome.single"/></option>
                            <option name="roombed" value="Double"><fmt:message key="welcome.double"/></option>
                            <option name="roombed" value="Studio"><fmt:message key="welcome.studio"/></option>

                        </select>

                    </li>
                </ul>

                <button id="changeBtn" type="submit" class="b1"><fmt:message key="welcome.findroom"/></button>
                <label id="message">${nullrooms}</label>
            </form>
            <div id="presentation">
                <fmt:message key="login.presentation"/>
            </div>
        </jsp:body>
    </t:genericpage>

    </html>
</fmt:bundle>