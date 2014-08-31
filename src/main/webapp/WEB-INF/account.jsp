<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<html>
<head>
       <title>Account page</title>
</head>
<t:genericpage>

    <jsp:attribute name="header">
<div id="statusbarcontent">
    <p id="welcome"> Welcome, ${user.username} ! </p>
            <a id="account" href="/welcome">Выйти</a>

</div>
    </jsp:attribute>
    <jsp:attribute name="accordion"/>
    <jsp:attribute name="carousel"/>
    <jsp:attribute name="footer"/>
    <jsp:body>

        <div>
            <h3>Добро пожаловать, ${user.username} в Ваш личный кабинет!</h3>
            <table class="table table-bordered table-hover table-condensed">
                <caption>Ваши заказы ${notbookinglist}</caption>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>DATE_FROM</th>
                    <th>DATE_TO</th>
                    <th>All days</th>
                    <th>Room ID</th>
                    <th>User_ID</th>
                    <th>Confirm</th>
                    <th>Побробности</th>

                </tr>
                </thead>
                <tbody>

                <c:forEach items="${bookinglist}" var="bl">
                    <tr>
                        <td>${bl.id}</td>
                        <td>${bl.dateFrom}</td>
                        <td>${bl.dateTo}</td>
                        <td>${bl.dayCount}</td>
                        <td>${bl.roomNo}</td>
                        <td>${bl.userId}</td>
                        <td>${bl.confirm}</td>
                        <td><a href="/account?bookid=${bl.id}&roomid=${bl.roomNo}">Подробности</a></td>

                    </tr>
                </c:forEach>
                </tbody>

            </table>
            <div class="accordion" id="accordion2" align="center">
                <div class="accordion-group">
                    <div class="accordion-heading">
                        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne">
                            Подробности заказа ${bookid}:
                        </a>
                    </div>
                    <div id="collapseOne" class="accordion-body ">
                        <div class="accordion-inner">
                            <table class="table table-bordered table-hover table-condensed">
                                <caption>Данные клиента: ${notcustomerlist}</caption>
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>NAME</th>
                                    <th>LAST_NAME</th>
                                    <th>CITY</th>
                                    <th>REGION</th>
                                    <th>COUNTRY</th>
                                    <th>PASSPORT</th>
                                    <th>PHONE</th>
                                    <th>EMAIL</th>
                                    <th>PREPAYMENT</th>
                                    <th>BOOK_ID</th>
                                    <th>USER_ID</th>
                                </tr>

                                </thead>
                                <tbody>

                                <c:forEach items="${customerlist}" var="cd">
                                    <tr>
                                        <td>${cd.id}</td>
                                        <td>${cd.firstName}</td>
                                        <td>${cd.lastName}</td>
                                        <td>${cd.city}</td>
                                        <td>${cd.region}</td>
                                        <td>${cd.country}</td>
                                        <td>${cd.passport}</td>
                                        <td>${cd.phone}</td>
                                        <td>${cd.email}</td>
                                        <td>${cd.prepayment}</td>
                                        <td>${cd.bookId}</td>
                                        <td>${cd.userId}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <hr>
                            <table class="table table-bordered table-hover table-condensed">
                                <caption>Данные забронированного номера/комнаты: ${notroomlist}</caption>
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Тип</th>
                                    <th>Стоимость</th>
                                    <th>Количество спальных мест</th>
                                    <th>Номер</th>

                                </tr>

                                </thead>
                                <tbody>

                                <c:forEach items="${roomlist}" var="rd">
                                    <tr>
                                        <td>${rd.id}</td>
                                        <td>${rd.roomType}</td>
                                        <td>${rd.roomRate}</td>
                                        <td>${rd.roomBed}</td>
                                        <td>${rd.roomNo}</td>


                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>

                        </div>
                    </div>
                </div>
            </div>

        </div>

    </jsp:body>
</t:genericpage>

</html>
