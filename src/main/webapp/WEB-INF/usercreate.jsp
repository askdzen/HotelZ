<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Room Create</title>

    <script type='text/javascript' src='<c:url value="webjars/jquery/2.0.3/jquery.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="webjars/bootstrap/3.0.0/js/bootstrap.min.js"/>'></script>

    <link href="../static/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body>
<h3>Добавление пользователя</h3>
<form method="post" class="form-horizontal" role="form">

    <div class="form-group">
        <label for="login" class="col-sm-2 control-label">Username</label>

        <div class="col-sm-10">
            <input type="text" class="form-control" id="login" name="login" value=""
                   placeholder="">
        </div>
    </div>
    <div class="form-group">
        <label for="pass" class="col-sm-2 control-label">Password</label>

        <div class="col-sm-10">
            <input type="text" class="form-control" id="pass" name="pass" value=""
                   placeholder="">
        </div>
    </div>
    <div class="form-group">
        <label for="roles" class="col-sm-2 control-label">Role</label>

        <div class="col-sm-10">
            <input type="text" class="form-control" id="roles" name="roles" value=""
                   placeholder="">
        </div>
    </div>



    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">

            <button type="submit" class="btn"> Create </button>
        </div>
    </div>
</form>
</body>
</html>


