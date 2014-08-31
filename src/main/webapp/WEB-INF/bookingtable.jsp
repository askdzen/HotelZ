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

        <hr>

        <form class="form-inline">
            <label for="rows1"> Введите количество строк на странице:</label>
            <input type="text" name="rows" value="${rowsCount}" id="rows1"/>

        </form>

        <form method="get">
            <button type="submit" class="btn" name="create">Добавить запись</button>
        </form>

        <button onClick='location.href="/adminform"' type="submit" class="btn">Перейти на
            главную страницу
        </button>

        <tr>
            <div>
                <ul id="change" class="nav nav-tabs">
                    <li>
                        <form id="back" class="form-inline">

                            <div>
                                <input type="text" name="page" value="${pageNumber-1}" hidden="hidden"/>

                                <input type="text" name="rows" value="${rowsCount}" hidden="hidden"/>
                            </div>

                            <button id="changeBtn2" name="action" value="bookingTableEdit" type="submit"
                                    class="btn" ${backdisabled}>Back
                            </button>

                        </form>
                    </li>
                    <li>
                        <form id="pagination" class="form-inline">
                            <ul class="pagination">
                                <c:forEach items="${paginationlist}" var="pl">

                                    <li><a href="bookingtable?page=${pl.intValue()}&rows=${rowsCount}"
                                           name="page">${pl.intValue()}</a></li>

                                </c:forEach>
                            </ul>
                        </form>
                    </li>
                    <li>
                        <form id="next" class="form-inline">
                            <div>

                                <input type="text" name="page" value="${pageNumber+1}" hidden="hidden"/>
                                <input type="text" name="rows" value="${rowsCount}" hidden="hidden"/>
                            </div>

                            <button id="changeBtn" type="submit" class="btn"  ${nextdisabled}>Next</button>

                        </form>
                    </li>
                </ul>
            </div>
        </tr>

        </tbody>
    </table>

    <hr>





</div>


</div>
</body>
</html>
