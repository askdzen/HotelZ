<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="accordion" fragment="true" %>
<%@attribute name="carousel" fragment="true" %>
<%@attribute name="footer" fragment="true" %>

<html>
<body>

<div id="header">
    <jsp:invoke fragment="header"/>
    <script type='text/javascript' src='<c:url value="webjars/jquery/2.0.3/jquery.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="webjars/bootstrap/3.0.0/js/bootstrap.min.js"/>'></script>

    <link href="../../static/bootstrap.min.css" rel="stylesheet" media="screen">

        <div id="logo">
            <h1><a href="/" title="Home Page">Сказочный отель на берегу моря <p id="yes"></p></a></h1>

        </div>
        <div id="logo1">

            <h2>Солнечный Зурбаган</h2>
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
        Copyright © 2014 EPAM Studio<br /> All Rights Reserved
    </p>
</div>
</body>
</html>