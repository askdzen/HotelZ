<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<html>
<head>
    <title>User Edit</title>
    <script type='text/javascript' src='<c:url value="webjars/jquery/2.0.3/jquery.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="webjars/bootstrap/3.0.0/js/bootstrap.min.js"/>'></script>

    <link href="../static/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body>
<div>
    <form method="get">
        <select id="search" class="form-control" name="column" >
            <option name="column" ${ID} value="ID">ID</option>
            <option name="column" ${LOGIN} value="LOGIN">LOGIN</option>
            <option name="column" ${PASSWORD} value="PASSWORD">PASSWORD</option>
            <option name="column" ${ROLE} value="ROLE">ROLE</option>


        </select>
        <input  id="search1" type="text" name="value" value="${value}" >
        <button id="changeBtn3" type="submit" class="btn" >Найти</button>
        <input hidden="hidden" value="${column}">
        <br><br>
        <a href="/userdetail" >Обновить таблицу</a>
    </form>
</div>
<div id="roomAdminGeneral">


    <table class="table table-bordered table-hover table-condensed">
        <caption>List of users</caption>
        <thead>
        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Password</th>
            <th>Role</th>
            <th>Update Button</th>
            <th>Delete Button</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${list}" var="user">

            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.password}</td>
                <td>${user.role}</td>


                <td>
                    <form method="get">
                        <button type="submit" name="update" value="${user.id}">Edit</button>
                    </form>
                </td>
                <td>
                    <form method="post">
                        <button type="submit" name="delete" value="${user.id}">Delete</button>
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
