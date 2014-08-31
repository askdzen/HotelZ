

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<html>
<head>

    <title>Customer page</title>
</head>
<t:genericpage>

    <jsp:attribute name="header">
<div id="statusbarcontent">
    <p id="welcome"> Welcome, ${user.username} ! </p>
    <a id="account" href="/welcome">Вернуться на главную</a>

</div>
    </jsp:attribute>
    <jsp:attribute name="accordion"/>
    <jsp:attribute name="carousel"/>
    <jsp:attribute name="footer"/>
    <jsp:body>


<form  method="post" class="form-horizontal" role="form">
    <div class="form-group" hidden="true">

        <div class="col-sm-10" hidden="hidden">
            <input type="date" class="form-control" name="inDateFrom" id="inputDateFrom" value="${dateFrom}"
                   placeholder="Date From">
            <input type="date" class="form-control" name="inDateTo" id="inputDateTo" value="${dateTo}"
                   placeholder="Date To">
            <input type="text" class="form-control" id="inputType"name="inType" value="${type}"
                   placeholder="Room Type">
            <input type="text" class="form-control"name="inBedNo" id="inputBedNo" value="${singledouble}"
                   placeholder="Date From">
            <input type="number" class="form-control" name="inputPrepayment" id="inputPrepayment" value="${prepayment}"
                   placeholder="Prepayment">
            <input type="number" class="form-control" id="inputRoomId" name="inRoomId" value="${randomRoom.id}"
                   placeholder="RoomId">
        </div>
    </div>
    <div align="center" class="form-group">
        <h3 align="center">Мы подобрали для Вас номер!</h3>

                <p>Date From "${dateFrom}"</p>

                <p>Date To "${dateTo}""</p>

                <p>Room Type "${type}"</p>

                <p>Bed Count "${singledouble}"</p>

                <p>Prepayment "${((prepayment*50)/100)}"</p>

                <p>RoomId "${randomRoom.id}"</p>


    </div>
    <div class="accordion" id="accordion2" align="center">
        <div class="accordion-group">
            <div class="accordion-heading">
                <h3 align="center">Для перехода к процедуре бронирования, нажмите на ссылку</h3>
                <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne">
                   Ввести данные для регистрации:
                </a>
            </div>
            <div id="collapseOne" class="accordion-body collapse">
                <div class="accordion-inner">
                    <div class="form-group">
                        <label for="inputFirstName" class="col-sm-2 control-label">First name</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputFirstName" name="inputFirstName"
                                   placeholder="First name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputLastName" class="col-sm-2 control-label">Last name</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputLastName" name="inputLastName"
                                   placeholder="Last name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputCity" class="col-sm-2 control-label">City</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputCity" name="inputCity"
                                   placeholder="City">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputRegion" class="col-sm-2 control-label">Region</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputRegion" name="inputRegion"
                                   placeholder="Region">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputCountry" class="col-sm-2 control-label">Country</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputCountry" name="inputCountry"
                                   placeholder="Country">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassport" class="col-sm-2 control-label">Passport No</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputPassport" name="inputPassport"
                                   placeholder="Passport No">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPhone" class="col-sm-2 control-label">Phone No</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputPhone" name="inputPhone"
                                   placeholder="Phone No">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputEmail" class="col-sm-2 control-label">Email</label>
                        <div class="col-sm-10">
                            <input type="email" class="form-control" id="inputEmail" name="inputEmail"
                                   placeholder="Email">
                        </div>
                    </div>


                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">

                            <button type="submit" class="btn" name="action" value="customerForm" > Confirm </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</form>
    </jsp:body>
</t:genericpage>

</html>
