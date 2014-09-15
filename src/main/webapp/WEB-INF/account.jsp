<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<html>
<head>
       <title>Account page</title>
</head>
<t:welcomelayout>
    <jsp:attribute name="welcomelayout"/>
</t:welcomelayout>
<t:genericpage>

    <jsp:attribute name="header">

    </jsp:attribute>
    <jsp:attribute name="accordion"/>
    <jsp:attribute name="carousel"/>
    <jsp:attribute name="footer"/>
    <jsp:body>

        <div>
            <h3>Добро пожаловать, ${user.username} в Ваш личный кабинет!</h3>
            <div id="accountbooking">
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

                <c:forEach items="${list}" var="bl">
                    <tr>
                        <td>${bl.id}</td>
                        <td>${bl.dateFrom}</td>
                        <td>${bl.dateTo}</td>
                        <td>${bl.dayCount}</td>
                        <td>${bl.roomNo}</td>
                        <td>${bl.userId}</td>
                        <td>${bl.confirm}</td>
                        <td><a href="/account?bookid=${bl.id}&roomid=${bl.roomNo}&hidden=${hidden}" class="btn btn-lg btn-primary"
                               data-toggle="modal">Подробности</a></td>

                    </tr>
                </c:forEach>
                <tr>
                    <t:pagination>
                        <jsp:attribute name="paginationtag"/>
                    </t:pagination>
                    <div>
                        <!-- Launch Modal -->
                        <a href="#DemoModal2" class="btn btn-lg btn-primary"
                           data-toggle="modal">Изменить логин или пароль</a>

                        <!-- Modal Contents -->
                        <div id="DemoModal2" class="modal fade ">

                            <div  class="modal-dialog modal-lg">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close"
                                                data-dismiss="modal" aria-hidden="true">×</button>

                                        <h3 class="modal-title">Данные пользователя: ${notuserlist}</h3>
                                    </div>

                                    <div class="modal-body">
                                        <form method="post" class="form-horizontal" role="form">
                                        <c:forEach items="${userlist}" var="user">
                                            <div class="form-group">
                                            <label  for="username" class="col-sm-2 control-label">Измените логин</label>
                                                <div class="col-sm-10">
                                            <input type="text" class="form-control" id="username" name="username" value="${user.username}"
                                                   placeholder="username" >
                                                </div>
                                                <br><br>
                                    </div>
                                            <div class="form-group">
                                            <label  for="password" class="col-sm-2 control-label">Измените пароль</label>
                                                <div class="col-sm-10">
                                            <input type="text" class="form-control"  id="password" name="password" value="${user.password}"
                                                   placeholder="password">
                                            </div>
                                    </div>
                                            <br><br><br><br>
                                            <div class="form-group">

                                                <p> <button type="submit" name="updateid" value="${user.id}">Save</button></p>
                                            </div>


                                    </div>

                                        <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Not Now!</button>

                                        </div>
    </c:forEach>
    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </tr>
                </tbody>

            </table>
        </div>


            <div class="accordion" id="accountdetail" ${hidden} align="center">
                <div class="accordion-group">
                    <div class="accordion-heading">
                        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne">
                            Подробности заказа ${bookid}:
                        </a>
                    </div>
                    <div id="collapseOne" class="accordion-body ">
                        <div class="accordion-inner">

                            <hr>
                            <c:forEach items="${customerlist}" var="cd">
                                <div class="form-group">
                                    <label  for="inputFirstName" class="col-sm-2 control-label">First name</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="inputFirstName" name="inputFirstName" value="${cd.firstName}"
                                               placeholder="First name" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputLastName" class="col-sm-2 control-label">Last name</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="inputLastName" name="inputLastName" value="${cd.lastName}"
                                               placeholder="Last name">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputCity" class="col-sm-2 control-label">City</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="inputCity" name="inputCity" value="${cd.city}"
                                               placeholder="City">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputRegion" class="col-sm-2 control-label">Region</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="inputRegion" name="inputRegion" value="${cd.region}"
                                               placeholder="Region">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputCountry" class="col-sm-2 control-label">Country</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="inputCountry" name="inputCountry" value="${cd.country}"
                                               placeholder="Country">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputPassport" class="col-sm-2 control-label">Passport No</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="inputPassport" name="inputPassport" value="${cd.passport}"
                                               placeholder="Passport No">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputPhone" class="col-sm-2 control-label">Phone No</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="inputPhone" name="inputPhone" value="${cd.phone}"
                                               placeholder="Phone No">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputEmail" class="col-sm-2 control-label">Email</label>

                                    <div class="col-sm-10">
                                        <input type="email" class="form-control" id="inputEmail" name="inputEmail" value="${cd.email}"
                                               placeholder="Email">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputPrepayment" class="col-sm-2 control-label">Prepayment</label>

                                    <div class="col-sm-10">
                                        <input type="number" class="form-control" id="inputPrepayment" name="inputPrepayment" value="${cd.prepayment}"
                                               placeholder="inputPrepayment">

                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputBookId" class="col-sm-2 control-label">Book Id</label>

                                    <div class="col-sm-10">
                                        <input type="number" class="form-control" id="inputBookId" name="inputBookId" value="${cd.bookId}"
                                               placeholder="inputBookId">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="userId" class="col-sm-2 control-label">User Id</label>

                                    <div class="col-sm-10">
                                        <input type="number" class="form-control" id="userId" name="userId" value="${cd.userId}"
                                               placeholder="inputBookId">
                                    </div>
                                </div>
                                <div class="form-group" hidden="hidden">
                                    <label for="customerId" class="col-sm-2 control-label">Customer Id</label>

                                    <div class="col-sm-10">
                                        <input type="number" class="form-control" id="customerId" name="customerId" value="${cd.id}"
                                               placeholder="customerId">
                                    </div>
                                </div>

                            </c:forEach>

                                <h3>Данные забронированного номера/комнаты: ${notroomlist}</h3>

                                <c:forEach items="${roomlist}" var="rd">

                        </div>
                        <div class="form-group">
                            <label for="roomno" class="col-sm-2 control-label">Room No</label>

                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="roomno" name="roomno" value="${rd.roomNo}"
                                       placeholder="">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="roomtype" class="col-sm-2 control-label">Room Type</label>

                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="roomtype" name="roomtype" value="${rd.roomType}"
                                       placeholder="">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="bedtype" class="col-sm-2 control-label">Bed Type</label>

                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="bedtype" name="bedtype" value="${rd.roomBed}"
                                       placeholder="">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="tarif" class="col-sm-2 control-label">Tarif</label>

                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="tarif" name="tarif" value="${rd.roomRate}"
                                       placeholder="">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="roomid" class="col-sm-2 control-label">Id</label>

                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="roomid" name="roomid" value="${rd.id}"
                                       placeholder="">
                            </div>
                        </div>
                    </div>

    </c:forEach>
                </div>
            </div>

        </div>

    </jsp:body>
</t:genericpage>

</html>
