<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="${bundlelang}">
<html>
<head>
    <title><fmt:message key="customer.title"/> </title>
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
            <option name="column" ${NAME} value="NAME"><fmt:message key="customer.name"/> </option>
            <option name="column" ${LAST_NAME} value="LAST_NAME"><fmt:message key="customer.lastname"/> </option>
            <option name="column" ${CITY} value="CITY"><fmt:message key="customer.city"/> </option>
            <option name="column" ${REGION} value="REGION"><fmt:message key="customer.region"/> </option>
            <option name="column" ${COUNTRY} value="COUNTRY"><fmt:message key="customer.country"/> </option>
            <option name="column" ${PASSPORT} value="PASSPORT"><fmt:message key="customer.passport"/> </option>
            <option name="column" ${PHONE} value="PHONE"><fmt:message key="customer.phone"/> </option>
            <option name="column" ${EMAIL} value="EMAIL"><fmt:message key="customer.email"/> </option>
            <option name="column" ${PREPAYMENT} value="PREPAYMENT"><fmt:message key="customer.prepayment"/> </option>
            <option name="column" ${BOOK_ID} value="BOOK_ID"><fmt:message key="customer.bookid"/> </option>
            <option name="column" ${USER_ID} value="USER_ID"><fmt:message key="customer.userid"/> </option>

        </select>
        <input  id="search1" type="text" name="value" value="${value}" >
        <button id="changeBtn3" type="submit" class="btn" > <fmt:message key="find"/> </button>
        <input hidden="hidden" value="${column}">
        <br><br>
        <a href="/customerdetail" ><fmt:message key="tableupdate"/> </a>
    </form>
</div>
<div id="roomAdminGeneral">

<table class="table table-bordered table-hover table-condensed">
    <caption><fmt:message key="customer.tablecaption"/> </caption>
    <thead>
    <tr>
        <th>ID</th>
        <th><fmt:message key="customer.name"/></th>
        <th><fmt:message key="customer.lastname"/></th>
        <th><fmt:message key="customer.city"/></th>
        <th><fmt:message key="customer.region"/></th>
        <th><fmt:message key="customer.country"/></th>
        <th><fmt:message key="customer.passport"/></th>
        <th><fmt:message key="customer.phone"/></th>
        <th><fmt:message key="customer.email"/></th>
        <th><fmt:message key="customer.prepayment"/></th>
        <th><fmt:message key="customer.bookid"/></th>
        <th><fmt:message key="customer.userid"/></th>
        <th><fmt:message key="editbutton"/> </th>
        <th><fmt:message key="deletebutton"/> </th>
    </tr>
    </thead>
    <tbody>

    <c:forEach items="${list}" var="cust">

        <tr>
            <td>${cust.id}</td>
            <td>${cust.firstName}</td>
            <td>${cust.lastName}</td>
            <td>${cust.city}</td>
            <td>${cust.region}</td>
            <td>${cust.country}</td>
            <td>${cust.passport}</td>
            <td>${cust.phone}</td>
            <td>${cust.email}</td>
            <td>${cust.prepayment}</td>
            <td>${cust.bookId}</td>
            <td>${cust.userId}</td>
            <td>
                <form method="get">
                    <button type="submit" name="update" value="${cust.id}"><fmt:message key="edit"/> </button>
                </form>
            </td>
            <td>
                <form method="post">
                    <button type="submit" name="delete" value="${cust.id}"><fmt:message key="isdelete"/> </button>
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