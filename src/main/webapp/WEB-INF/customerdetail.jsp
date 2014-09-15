<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title></title>
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
            <option name="column" ${NAME} value="NAME">NAME</option>
            <option name="column" ${LAST_NAME} value="LAST_NAME">LAST NAME</option>
            <option name="column" ${CITY} value="CITY">CITY</option>
            <option name="column" ${REGION} value="REGION">REGION</option>
            <option name="column" ${COUNTRY} value="COUNTRY">COUNTRY</option>
            <option name="column" ${PASSPORT} value="PASSPORT">PASSPORT</option>
            <option name="column" ${PHONE} value="PHONE">PHONE</option>
            <option name="column" ${EMAIL} value="EMAIL">EMAIL</option>
            <option name="column" ${PREPAYMENT} value="PREPAYMENT">PREPAYMENT</option>
            <option name="column" ${BOOK_ID} value="BOOK_ID">BOOK_ID</option>
            <option name="column" ${USER_ID} value="USER_ID">USER_ID</option>

        </select>
        <input  id="search1" type="text" name="value" value="${value}" >
        <button id="changeBtn3" type="submit" class="btn" >Найти</button>
        <input hidden="hidden" value="${column}">
        <br><br>
        <a href="/customerdetail" >Обновить таблицу</a>
    </form>
</div>
<div id="roomAdminGeneral">

<table class="table table-bordered table-hover table-condensed">
    <caption>Customer Details</caption>
    <thead>
    <tr>
        <th>ID</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>City</th>
        <th>Region</th>
        <th>Country</th>
        <th>Passport</th>
        <th>Phone</th>
        <th>Email</th>
        <th>Prepayment</th>
        <th>Book Id</th>
        <th>User Id</th>
        <th>Update Button</th>
        <th>Delete Button</th>
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
                    <button type="submit" name="update" value="${cust.id}">Edit</button>
                </form>
            </td>
            <td>
                <form method="post">
                    <button type="submit" name="delete" value="${cust.id}">Delete</button>
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
