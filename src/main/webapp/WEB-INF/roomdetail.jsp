<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${fmtlocale}"/>
<fmt:bundle basename="i18n.message">
<html>
<head>
    <title><fmt:message key="room.title"/> </title>
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
        <option name="column" ${ROOM_NO} value="ROOM_NO"><fmt:message key="room.no"/> </option>
        <option name="column" ${ROOM_TYPE} value="ROOM_TYPE"><fmt:message key="room.type"/> </option>
        <option name="column" ${ROOM_BED} value="ROOM_BED"><fmt:message key="room.bed"/> </option>
        <option name="column" ${ROOM_RATE} value="ROOM_RATE"><fmt:message key="room.rate"/> </option>
    </select>
        <input  id="search1" type="text" name="value" value="${value}" >
        <button id="changeBtn3" type="submit" class="btn" ><fmt:message key="find"/> </button>
        <input hidden="hidden" value="${column}">
        <br><br>
        <a href="/HotelReservation-1.0-SNAPSHOT/roomdetail" ><fmt:message key="tableupdate"/> </a>
    </form>
</div>
<div id="roomAdminGeneral">


    <table class="table table-bordered table-hover table-condensed">
        <caption><fmt:message key="room.list"/> </caption>
        <thead>
        <tr>
            <th>ID</th>
            <th><fmt:message key="room.no"/> </th>
            <th><fmt:message key="room.type"/> </th>
            <th><fmt:message key="room.bed"/> </th>
            <th><fmt:message key="room.rate"/> </th>
            <th><fmt:message key="editbutton"/> </th>
            <th><fmt:message key="deletebutton"/> </th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${list}" var="roomI">

            <tr>
                <td>${roomI.id}</td>
                <td>${roomI.roomNo}</td>
                <td>${roomI.roomType}</td>
                <td>${roomI.roomBed}</td>
                <td>${roomI.roomRate}</td>
                <td>
                    <form method="get">
                        <button type="submit" name="update" value="${roomI.id}"><fmt:message key="edit"/> </button>
                    </form>
                </td>
                <td>
                    <form method="post">
                        <button type="submit" name="delete" value="${roomI.id}"><fmt:message key="isdelete"/> </button>
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