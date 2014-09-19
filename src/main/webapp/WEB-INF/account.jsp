<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${fmtlocale}"/>
<fmt:bundle basename="i18n.message">

<html>
<head>
       <title><fmt:message key="account.title"/> </title>
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
            <h3><fmt:message key="welcomelayout.welcome"/> , ${user.username} <fmt:message key="account.welcome"/> </h3>
            <div id="accountbooking">
            <table class="table table-bordered table-hover table-condensed">
                <caption><fmt:message key="account.orders"/> ${notbookinglist}</caption>
                <thead>
                <tr>
                    <th>ID</th>
                    <th><fmt:message key="booking.datefrom"/> </th>
                    <th><fmt:message key="booking.dateto"/> </th>
                    <th><fmt:message key="booking.numberofdays"/> </th>
                    <th><fmt:message key="room.id"/> </th>
                    <th><fmt:message key="booking.userId"/> </th>
                    <th><fmt:message key="booking.confirmation"/> </th>
                    <th><fmt:message key="account.details"/> </th>

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
                               data-toggle="modal"><fmt:message key="account.details"/> </a></td>

                    </tr>
                </c:forEach>
                <tr>
                    <t:pagination>
                        <jsp:attribute name="paginationtag"/>
                    </t:pagination>
                    <div>
                        <!-- Launch Modal -->
                        <a href="#DemoModal2" class="btn btn-lg btn-primary"
                           data-toggle="modal"><fmt:message key="account.editusername"/> </a>

                        <!-- Modal Contents -->
                        <div id="DemoModal2" class="modal fade ">

                            <div  class="modal-dialog modal-lg">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close"
                                                data-dismiss="modal" aria-hidden="true">×</button>

                                        <h3 class="modal-title"><fmt:message key="account.userdata"/> : ${notuserlist}</h3>
                                    </div>

                                    <div class="modal-body">
                                        <form method="post" class="form-horizontal" role="form">
                                        <c:forEach items="${userlist}" var="user">
                                            <div class="form-group">
                                            <label  for="username" class="col-sm-2 control-label"><fmt:message key="user.username"/> </label>
                                                <div class="col-sm-10">
                                            <input type="text" class="form-control" id="username" name="username" value="${user.username}"
                                                   placeholder="username" >
                                                </div>
                                                <br><br>
                                    </div>
                                            <div class="form-group">
                                            <label  for="password" class="col-sm-2 control-label"><fmt:message key="user.password"/> </label>
                                                <div class="col-sm-10">
                                            <input type="text" class="form-control"  id="password" name="password" value="${user.password}"
                                                   placeholder="password">
                                            </div>
                                    </div>
                                            <br><br><br><br>
                                            <div class="form-group">

                                                <p> <button type="submit" name="updateid" value="${user.id}"><fmt:message key="save"/> </button></p>
                                            </div>


                                    </div>

                                        <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="account.notnow"/> </button>

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
                            <fmt:message key="account.detailshide"/> :
                        </a>
                    </div>
                    <div id="collapseOne" class="accordion-body collapse in">
                        <div class="accordion-inner">

                            <hr>
                            <c:forEach items="${customerlist}" var="cd">
                                <div class="form-group">
                                    <label  for="inputFirstName" class="col-sm-2 control-label"><fmt:message key="customer.name"/> </label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="inputFirstName" name="inputFirstName" value="${cd.firstName}"
                                               placeholder="First name" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputLastName" class="col-sm-2 control-label"><fmt:message key="customer.lastname"/> </label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="inputLastName" name="inputLastName" value="${cd.lastName}"
                                               placeholder="Last name">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputCity" class="col-sm-2 control-label"><fmt:message key="customer.city"/> </label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="inputCity" name="inputCity" value="${cd.city}"
                                               placeholder="City">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputRegion" class="col-sm-2 control-label"><fmt:message key="customer.region"/> </label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="inputRegion" name="inputRegion" value="${cd.region}"
                                               placeholder="Region">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputCountry" class="col-sm-2 control-label"><fmt:message key="customer.country"/> </label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="inputCountry" name="inputCountry" value="${cd.country}"
                                               placeholder="Country">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputPassport" class="col-sm-2 control-label"><fmt:message key="customer.passport"/> </label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="inputPassport" name="inputPassport" value="${cd.passport}"
                                               placeholder="Passport No">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputPhone" class="col-sm-2 control-label"><fmt:message key="customer.phone"/> </label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="inputPhone" name="inputPhone" value="${cd.phone}"
                                               placeholder="Phone No">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputEmail" class="col-sm-2 control-label"><fmt:message key="customer.email"/> </label>

                                    <div class="col-sm-10">
                                        <input type="email" class="form-control" id="inputEmail" name="inputEmail" value="${cd.email}"
                                               placeholder="Email">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputPrepayment" class="col-sm-2 control-label"><fmt:message key="customer.prepayment"/> </label>

                                    <div class="col-sm-10">
                                        <input type="number" class="form-control" id="inputPrepayment" name="inputPrepayment" value="${cd.prepayment}"
                                               placeholder="inputPrepayment">

                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputBookId" class="col-sm-2 control-label"><fmt:message key="customer.bookid"/> </label>

                                    <div class="col-sm-10">
                                        <input type="number" class="form-control" id="inputBookId" name="inputBookId" value="${cd.bookId}"
                                               placeholder="inputBookId">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="userId" class="col-sm-2 control-label"><fmt:message key="customer.userid"/> </label>

                                    <div class="col-sm-10">
                                        <input type="number" class="form-control" id="userId" name="userId" value="${cd.userId}"
                                               placeholder="inputBookId">
                                    </div>
                                </div>
                                <div class="form-group" hidden="hidden">
                                    <label for="customerId" class="col-sm-2 control-label">ID</label>

                                    <div class="col-sm-10">
                                        <input type="number" class="form-control" id="customerId" name="customerId" value="${cd.id}"
                                               placeholder="customerId">
                                    </div>
                                </div>

                            </c:forEach>

                                <h3><fmt:message key="account.roomsdata"/> : ${notroomlist}</h3>

                                <c:forEach items="${roomlist}" var="rd">

                        </div>
                        <div class="form-group">
                            <label for="roomno" class="col-sm-2 control-label"><fmt:message key="room.no"/> </label>

                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="roomno" name="roomno" value="${rd.roomNo}"
                                       placeholder="">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="roomtype" class="col-sm-2 control-label"><fmt:message key="room.type"/> </label>

                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="roomtype" name="roomtype" value="${rd.roomType}"
                                       placeholder="">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="bedtype" class="col-sm-2 control-label"><fmt:message key="room.bed"/> </label>

                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="bedtype" name="bedtype" value="${rd.roomBed}"
                                       placeholder="">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="tarif" class="col-sm-2 control-label"><fmt:message key="room.rate"/> </label>

                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="tarif" name="tarif" value="${rd.roomRate}"
                                       placeholder="">
                            </div>
                        </div>

                        <div class="form-group" hidden="hidden">
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
</fmt:bundle>