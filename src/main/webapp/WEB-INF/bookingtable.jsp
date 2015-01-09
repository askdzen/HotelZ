<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${fmtlocale}"/>
<fmt:bundle basename="i18n.message">

<html>
<head>
    <title><fmt:message key="booking.title"/> </title>

    <script type='text/javascript' src='<c:url value="webjars/jquery/2.0.3/jquery.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="webjars/bootstrap/3.0.0/js/bootstrap.min.js"/>'></script>
    <link href="webjars/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="../static/style.css" rel="stylesheet" media="screen">
</head>
<body>
<div>
    <form method="get">
        <select id="search" class="form-control" name="column" >
            <option name="column" ${ID} value="ID">ID</option>
            <option name="column" ${DATE_FRO} value="DATE_FRO"><fmt:message key="booking.datefrom"/> </option>
            <option name="column" ${DATE_TO} value="DATE_TO"><fmt:message key="booking.dateto"/> </option>
            <option name="column" ${NO_OF_DAY} value="NO_OF_DAY"><fmt:message key="booking.numberofdays"/> </option>
            <option name="column" ${ROOM_NO} value="ROOM_NO"><fmt:message key="booking.roomno"/> </option>
            <option name="column" ${USER_ID} value="USER_ID"><fmt:message key="booking.userId"/> </option>
            <option name="column" ${CONFIRM} value="CONFIRM">C<fmt:message key="booking.confirmation"/> </option>

        </select>
        <input  id="search1" type="text" name="value" value="${value}" >
        <button id="changeBtn3" type="submit" class="btn" ><fmt:message key="find"/> </button>
        <input hidden="hidden" value="${column}">
        <br><br>
        <a href="/HotelReservation-1.0-SNAPSHOT/bookingtable" ><fmt:message key="tableupdate"/> </a>
    </form>
</div>
<div id="roomAdminGeneral">

    <table class="table table-bordered table-hover table-condensed">
        <caption><fmt:message key="booking.tablename"/> </caption>
        <thead>
        <tr>
            <th><fmt:message key="booking.datefrom"/></th>
            <th><fmt:message key="booking.dateto"/> </th>
            <th><fmt:message key="booking.numberofdays"/></th>
            <th><fmt:message key="booking.roomno"/></th>
            <th><fmt:message key="booking.userId"/> </th>
            <th><fmt:message key="booking.confirmation"/> </th>
            <th><fmt:message key="booking.confirmationbutton"/> </th>
            <th><fmt:message key="editbutton"/> </th>
            <th><fmt:message key="deletebutton"/> </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="bt">

            <tr>
                <td>${bt.dateFrom}</td>
                <td>${bt.dateTo}</td>
                <td>${bt.dayCount}</td>
                <td>${bt.roomNo}</td>
                <td>${bt.userId}</td>
                <td>${bt.confirm}</td>

                <td>
                    <form id="unproc" method="post">
                        <button type="submit" name="unprocessed" value="${bt.id}"><fmt:message key="booking.unproccessed"/> </button>
                    </form>
                    <form method="post" id="conf">
                        <button type="submit" name="confirm" value="${bt.id}"><fmt:message key="booking.aprroved"/> </button>
                    </form>
                    <form method="post" id="unconf">
                        <button type="submit" name="unconfirm" value="${bt.id}"><fmt:message key="booking.notapproved"/> </button>
                    </form>
                </td>
                <td>
                    <form method="get">
                        <button type="submit" name="update" value="${bt.id}"><fmt:message key="edit"/> </button>
                    </form>
                </td>
                <td>
                    <form method="post">
                        <button type="submit" name="delete" value="${bt.id}"><fmt:message key="isdelete"/> </button>
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
</fmt:bundle>