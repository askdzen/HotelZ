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
<h3><fmt:message key="user.createmessage"/> </h3>
<form method="post" class="form-horizontal" role="form">

    <div class="form-group">
        <label for="login" class="col-sm-2 control-label"><fmt:message key="user.username"/> </label>

        <div class="col-sm-10">
            <input type="text" class="form-control" id="login" name="login" value=""
                   placeholder="">
        </div>
    </div>
    <div class="form-group">
        <label for="pass" class="col-sm-2 control-label"><fmt:message key="user.password"/> </label>

        <div class="col-sm-10">
            <input type="text" class="form-control" id="pass" name="pass" value=""
                   placeholder="">
        </div>
    </div>
    <div class="form-group">
        <label for="roles" class="col-sm-2 control-label"><fmt:message key="user.role"/> </label>

        <div class="col-sm-10">
            <input type="text" class="form-control" id="roles" name="roles" value=""
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
