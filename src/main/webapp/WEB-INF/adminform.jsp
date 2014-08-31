
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%! String pageName ="Main";%>


<html>
<head>
    <title>Main page</title>
    <script type='text/javascript' src='<c:url value="webjars/jquery/2.0.3/jquery.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="webjars/bootstrap/3.0.0/js/bootstrap.min.js"/>'></script>

    <link href="../static/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body>
<p>admin form</p>
 <%--<%@include file="adminnav.jspf"%>--%>




    <button  onClick='location.href="/bookingtable"'   type="submit" class="btn">Редактирование Журнала бронирования</button>
    <hr>
    <button  onClick='location.href="/customerdetail"'  type="submit" class="btn">Редактирование данных клиентов</button>
    <hr>
    <button  onClick='location.href="/roomdetail"' type="submit" class="btn">Редактирование параметров комнат</button>
    <hr>



</body>
</html>
