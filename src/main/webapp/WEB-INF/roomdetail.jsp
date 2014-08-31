<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%! String pageName = "Roomdetail";%>


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
        <caption>List of rooms available</caption>
        <thead>
        <tr>
            <th>Room No</th>
            <th>Room Type</th>
            <th>Bed Type</th>
            <th>Tarif</th>
        </tr>
        </thead>
        <tbody>


        <c:forEach items="${list}" var="roomI">

            <tr>
                <td>${roomI.roomNo}</td>
                <td>"${roomI.roomType}</td>
                <td>${roomI.roomBed}</td>
                <td>${roomI.roomRate}</td>
            </tr>
        </c:forEach>


        <hr>

        <form class="form-inline">
            <label for="rows1"> Введите количество строк на странице:</label>
            <input type="text" name="rows" value="${rowsCount}" id="rows1"/>

        </form>


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

                                    <li><a href="roomdetail?page=${pl.intValue()}&rows=${rowsCount}"
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

    <button onClick='location.href="/adminform"' type="submit" class="btn">Перейти на
        главную страницу
    </button>


    <div class="tabbable" tabs-below> <!-- Only required for left/right tabs -->
        <ul class="nav nav-tabs">
            <li class="active"><a href="#tab1" data-toggle="tab">Добавить комнату</a></li>
            <li><a href="#tab2" data-toggle="tab">Изменить характеристики комнаты</a></li>
            <li><a href="#tab3" data-toggle="tab">Удалить комнату</a></li>

        </ul>
        <div class="tab-content">
            <div class="tab-pane active" id="tab1">
                <p>


                </p>
            </div>
            <div class="tab-pane" id="tab2">
                <p>Привет, я 2-я секция.</p>
            </div>
            <div class="tab-pane" id="tab3">
                <p>Привет, я 3-я секция.</p>
            </div>
            <div class="tab-pane" id="tab4">
                <p>Привет, я 4-я секция.</p>
            </div>
        </div>
    </div>


</div>



</body>
</html>
