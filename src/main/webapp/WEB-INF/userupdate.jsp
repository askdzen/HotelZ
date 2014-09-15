<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:bundle basename="i18n.message">
<html>
<head>
    <title><fmt:message key="user.title"/> </title>

    <script type='text/javascript' src='<c:url value="webjars/jquery/2.0.3/jquery.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="webjars/bootstrap/3.0.0/js/bootstrap.min.js"/>'></script>
    <link href="webjars/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="../static/style.css" rel="stylesheet" media="screen">
</head>
<body>
<h1><fmt:message key="user.changemessage"/> </h1>
<form method="post" class="form-horizontal" role="form">

    <div class="form-group">
        <label for="username" class="col-sm-2 control-label"><fmt:message key="user.username"/> </label>

        <div class="col-sm-10">
            <input type="text" class="form-control" id="username" name="username" value="${username}"
                   placeholder="">
        </div>
    </div>
    <div class="form-group">
        <label for="password" class="col-sm-2 control-label"><fmt:message key="user.password"/> </label>

        <div class="col-sm-10">
            <input type="text" class="form-control" id="password" name="password" value="${password}"
                   placeholder="">
        </div>
    </div>
    <div class="form-group">
        <label for="role" class="col-sm-2 control-label"><fmt:message key="user.role"/> </label>

        <div class="col-sm-10">
            <input type="text" class="form-control" id="role" name="role" value="${role}"
                   placeholder="">
        </div>
    </div>

    <div class="form-group">
        <label for="userid" class="col-sm-2 control-label">Id</label>

        <div class="col-sm-10">
            <input type="text" class="form-control" id="userid" name="userid" value="${userid}"
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