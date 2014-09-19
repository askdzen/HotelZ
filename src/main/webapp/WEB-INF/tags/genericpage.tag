<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="accordion" fragment="true" %>
<%@attribute name="carousel" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<fmt:setLocale value="${fmtlocale}"/>
<fmt:bundle basename="i18n.message" prefix="genericpage.">

<body>

<div id="header">
    <jsp:invoke fragment="header"/>
    <script type='text/javascript' src='<c:url value="webjars/jquery/2.0.3/jquery.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="webjars/bootstrap/3.0.0/js/bootstrap.min.js"/>'></script>
    <link href="webjars/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="../static/style.css" rel="stylesheet" media="screen">
    <link href="../../static/mistral.ttf" rel="stylesheet" type="text/css" />
    <div id="language">
        <form method="get" action="<c:url value="/change-locale" />" >

            <select id="lang" class="form-control" name="locale">
                <option name="locale" ${en} value="en">English</option>
                <option name="locale" ${ru} value="ru">Русский</option>


            </select>
            <button id="langBtn" type="submit" class="btn"><fmt:message key="language"/></button>
            <input hidden="hidden" value="${locale}">
        </form>
    </div>
        <div id="logo">
            <h1><a href="/" title="Home Page"><fmt:message key="hotelNamePreffix"/> <p id="yes"></p></a></h1>

        </div>
        <div id="logo1">

            <h2><fmt:message key="hotelName"/> </h2>
        </div>


</div>
<div id="body">
    <jsp:doBody/>
</div>
<div id="accordion">
    <jsp:invoke fragment="accordion"/>
</div>
<div id="carousel">
    <jsp:invoke fragment="carousel"/>
</div>
<div id="footer">
    <jsp:invoke fragment="footer"/>
    <div id="contentbottomshadow"></div>
    <div id="footerimage"></div>
    <p id="footertext" align="center">
        Copyright © 2014 EPAM Studio<br /> <fmt:message key="footer"/>
    </p>
</div>
</body>

</fmt:bundle>
</html>