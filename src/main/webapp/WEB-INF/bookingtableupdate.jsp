<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title></title>

    <script type='text/javascript' src='<c:url value="webjars/jquery/2.0.3/jquery.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="webjars/bootstrap/3.0.0/js/bootstrap.min.js"/>'></script>

    <link href="../static/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body>
<h1>Редактирование записи</h1>
<form method="post" class="form-horizontal" role="form">

    <div class="form-group">
        <label for="datefrom" class="col-sm-2 control-label">Date From</label>

        <div class="col-sm-10">
            <input type="text" class="form-control" id="datefrom" name="datefrom" value="${datefrom}"
                   placeholder="">
        </div>
    </div>
    <div class="form-group">
        <label for="dateto" class="col-sm-2 control-label">Date To</label>

        <div class="col-sm-10">
            <input type="text" class="form-control" id="dateto" name="dateto" value="${dateto}"
                   placeholder="${dateto}">
        </div>
    </div>
    <div class="form-group">
        <label for="daycount" class="col-sm-2 control-label">Day Count</label>

        <div class="col-sm-10">
            <input type="text" class="form-control" id="daycount" name="daycount" value="${daycount}"
                   placeholder="${daycount}">
        </div>
    </div>
    <div class="form-group">
        <label for="roomno" class="col-sm-2 control-label">Room No</label>

        <div class="col-sm-10">
            <input type="text" class="form-control" id="roomno" name="roomno" value="${roomno}"
                   placeholder="">
        </div>
    </div>
    <div class="form-group">
        <label for="userid" class="col-sm-2 control-label">User ID</label>

        <div class="col-sm-10">
            <input type="text" class="form-control" id="userid" name="userid" value="${userid}"
                   placeholder="">
        </div>
    </div>
    <div class="form-group">
        <label for="confirmUpdate" class="col-sm-2 control-label">Confirm</label>

        <div class="col-sm-10">
            <input type="text" class="form-control" id="confirmUpdate" name="confirmupdate"
                   value="${confirmupdate}"
                   placeholder="">
        </div>
    </div>
    <div class="form-group">
        <label for="btid" class="col-sm-2 control-label">Record Id</label>

        <div class="col-sm-10">
            <input type="text" class="form-control" id="btid" name="btid" value="${btid}"
                   placeholder="">
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">

            <button type="submit" class="btn"> Confirm</button>
        </div>
    </div>
</form>
</body>
</html>
