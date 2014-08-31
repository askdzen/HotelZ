

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<html>
<head>
    <style>

    </style>
    <script type='text/javascript' src='<c:url value="webjars/jquery/2.0.3/jquery.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="webjars/bootstrap/3.0.0/js/bootstrap.min.js"/>'></script>

    <link href="../static/bootstrap.min.css" rel="stylesheet" media="screen">
    <title>Welcome page</title>
</head>
<body>
<div id="statusbar">
    <div id="statusbarcontent">
        <form action="#search" method="post" id="searchform">
            <p>
                Welcome, ${user.username} !
                <br/>
                ${registrationGood}
                <a id="account" href="/account">Вход в личный кабинет</a>
            </p>
            <%--<input type="hidden" name="user" value="${user}">--%>
        </form>

    </div>
</div>
<div id="header">
    <div id="logo">
        <h1><a href="#home" title="Home Page">Сказочный отель на берегу моря <p id="yes">${yes}</p></a></h1>

    </div>
    <div id="logo1">

        <h2>Зурбаган</h2>
    </div>

</div>

</body>
</html>
