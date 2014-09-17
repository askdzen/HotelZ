<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:bundle basename="${bundlelang}">

<html>
<head>
    <title><fmt:message key="room.title"/> </title>

    <script type='text/javascript' src='<c:url value="webjars/jquery/2.0.3/jquery.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="webjars/bootstrap/3.0.0/js/bootstrap.min.js"/>'></script>
    <link href="webjars/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="../static/style.css" rel="stylesheet" media="screen">
</head>
<body>
<h3><fmt:message key="room.createmessage"/> </h3>
<form method="post" class="form-horizontal" role="form">

    <div class="form-group">
        <label for="roomnum" class="col-sm-2 control-label"><fmt:message key="room.no"/> </label>

        <div class="col-sm-10">
            <input type="text" class="form-control" id="roomnum" name="roomnum" value=""
                   placeholder="">
        </div>
    </div>
    <div class="form-group">
        <label for="type" class="col-sm-2 control-label"><fmt:message key="room.type"/> </label>

        <div class="col-sm-10">
            <input type="text" class="form-control" id="type" name="type" value=""
                   placeholder="">
        </div>
    </div>
    <div class="form-group">
        <label for="bed" class="col-sm-2 control-label"><fmt:message key="room.bed"/> </label>

        <div class="col-sm-10">
            <input type="text" class="form-control" id="bed" name="bed" value=""
                   placeholder="">
        </div>
    </div>
    <div class="form-group">
        <label for="rate" class="col-sm-2 control-label"><fmt:message key="room.rate"/> </label>

        <div class="col-sm-10">
            <input type="text" class="form-control" id="rate" name="rate" value=""
                   placeholder="">
        </div>
    </div>



    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">

            <button type="submit" class="btn"> <fmt:message key="create"/> </button>
        </div>
    </div>
</form>
</body>
</html>
</fmt:bundle>

