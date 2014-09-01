<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<html>
<head>
    <title>Booking Edit</title>

    <script type='text/javascript' src='<c:url value="webjars/jquery/2.0.3/jquery.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="webjars/bootstrap/3.0.0/js/bootstrap.min.js"/>'></script>

    <link href="../static/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body>

<div id="roomAdminGeneral">

    <table class="table table-bordered table-hover table-condensed">
        <caption>BookingTable</caption>
        <thead>
        <tr>
            <th>Date from</th>
            <th>Date To</th>
            <th>Day count</th>
            <th>Room ID</th>
            <th>User_ID</th>
            <th>Confirm Status</th>
            <th>Confirm Buttons</th>
            <th>Update Button</th>
            <th>Delete Button</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="bt">

            <tr>
                <td>${bt.dateFrom}</td>
                <td>"${bt.dateTo}</td>
                <td>${bt.dayCount}</td>
                <td>${bt.roomNo}</td>
                <td>${bt.userId}</td>
                <td>${bt.confirm}</td>

                <td>
                    <form id="unproc" method="post">
                        <button type="submit" name="unprocessed" value="${bt.id}">Unprocessed</button>
                    </form>
                    <form method="post" id="conf">
                        <button type="submit" name="confirm" value="${bt.id}">Confirm</button>
                    </form>
                    <form method="post" id="unconf">
                        <button type="submit" name="unconfirm" value="${bt.id}">Un Confirm</button>
                    </form>
                </td>
                <td>
                    <form method="get">
                        <button type="submit" name="update" value="${bt.id}">Edit</button>
                    </form>
                </td>
                <td>
                    <form method="post">
                        <button type="submit" name="delete" value="${bt.id}">Delete</button>
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
