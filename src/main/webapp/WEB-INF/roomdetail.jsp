<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<html>
<head>
    <title>Room Edit</title>
    <script type='text/javascript' src='<c:url value="webjars/jquery/2.0.3/jquery.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="webjars/bootstrap/3.0.0/js/bootstrap.min.js"/>'></script>

    <link href="../static/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body>

<div id="roomAdminGeneral">


    <table class="table table-bordered table-hover table-condensed">
        <caption>List of rooms available</caption>
        <thead>
        <tr>
            <th>ID</th>
            <th>Room No</th>
            <th>Room Type</th>
            <th>Bed Type</th>
            <th>Tarif</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${list}" var="roomI">

            <tr>
                <td>${roomI.id}</td>
                <td>${roomI.roomNo}</td>
                <td>"${roomI.roomType}</td>
                <td>${roomI.roomBed}</td>
                <td>${roomI.roomRate}</td>
                <td>
                    <form method="get">
                        <button type="submit" name="update" value="${roomI.id}">Edit</button>
                    </form>
                </td>
                <td>
                    <form method="post">
                        <button type="submit" name="delete" value="${roomI.id}">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <t:pagination>
                <jsp:attribute name="paginationtag"/>
            </t:pagination>
        </tr>

        </tbody>
    </table>
</div>

</body>
</html>
