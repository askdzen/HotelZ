<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Booking Create</title>

    <script type='text/javascript' src='<c:url value="webjars/jquery/2.0.3/jquery.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="webjars/bootstrap/3.0.0/js/bootstrap.min.js"/>'></script>

    <link href="../static/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body>
<h3>Добавление записи о бронировании</h3>
<form method="post" class="form-horizontal" role="form">

    <div class="form-group">
        <label for="datefrom" class="col-sm-2 control-label">Date From</label>

        <div class="col-sm-10">
            <input type="text" class="form-control" id="datefrom" name="datefromc" value=""
                   placeholder="">
        </div>
    </div>
    <div class="form-group">
        <label for="dateto" class="col-sm-2 control-label">Date To</label>

        <div class="col-sm-10">
            <input type="text" class="form-control" id="dateto" name="datetoc" value=""
                   placeholder="">
        </div>
    </div>

    <div class="form-group">
        <label for="roomno" class="col-sm-2 control-label">Room No</label>

        <div class="col-sm-10">
            <input type="text" class="form-control" id="roomno" name="roomnoc" value=""
                   placeholder="">${roomidisempty}
        </div>
    </div>
    <div class="form-group">
        <label for="userid" class="col-sm-2 control-label">User ID</label>

        <div class="col-sm-10">
            <input type="text" class="form-control" id="userid" name="useridc" value=""
                   placeholder="">${useridisempty}
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

