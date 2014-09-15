<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:bundle basename="i18n.message">
<html>
<head>
    <title><fmt:message key="room.title"/> </title>

    <script type='text/javascript' src='<c:url value="webjars/jquery/2.0.3/jquery.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="webjars/bootstrap/3.0.0/js/bootstrap.min.js"/>'></script>
    <link href="webjars/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="../static/style.css" rel="stylesheet" media="screen">
</head>
<body>
<h1><fmt:message key="room.changemessage"/> </h1>
<form method="post" class="form-horizontal" role="form">

    <div class="form-group">
        <label for="roomno" class="col-sm-2 control-label"><fmt:message key="room.no"/> </label>

        <div class="col-sm-10">
            <input type="text" class="form-control" id="roomno" name="roomno" value="${roomno}"
                   placeholder="">
        </div>
    </div>
    <div class="form-group">
        <label for="roomtype" class="col-sm-2 control-label"><fmt:message key="room.type"/> </label>

        <div class="col-sm-10">
            <input type="text" class="form-control" id="roomtype" name="roomtype" value="${roomtype}"
                   placeholder="">
        </div>
    </div>
    <div class="form-group">
        <label for="bedtype" class="col-sm-2 control-label"><fmt:message key="room.bed"/> </label>

        <div class="col-sm-10">
            <input type="text" class="form-control" id="bedtype" name="bedtype" value="${bedtype}"
                   placeholder="">
        </div>
    </div>
    <div class="form-group">
        <label for="tarif" class="col-sm-2 control-label"><fmt:message key="room.rate"/> </label>

        <div class="col-sm-10">
            <input type="text" class="form-control" id="tarif" name="tarif" value="${tarif}"
                   placeholder="">
        </div>
    </div>

    <div class="form-group">
        <label for="roomid" class="col-sm-2 control-label">Id</label>

        <div class="col-sm-10">
            <input type="text" class="form-control" id="roomid" name="roomid" value="${roomid}"
                   placeholder="">
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">

            <button type="submit" class="btn"> <fmt:message key="save"/> </button>
        </div>
    </div>
</form>
</body>
</html>
</fmt:bundle>