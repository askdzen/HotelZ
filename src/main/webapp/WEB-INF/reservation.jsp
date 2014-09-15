<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<fmt:bundle basename="i18n.message" >
<html>
<head>
    <title><fmt:message key="reservation.title"/> </title>
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
        <script>
            $(document).ready(function(){
                $(".close").click(function(){
                    $("#demo-alert").alert();
                });
            });
        </script>

        <div id="logo">
        <div id="Demo-BS" ${hidden} style="padding:30px;" align="center">
            <div class="alert alert-success" id="demo-alert">
                <a href="#" class="close" data-dismiss="alert">×</a>
                <p> <fmt:message key="reservation.congratulate"/> </p>
                <strong><fmt:message key="reservation.gotomain"/> </strong>
            </div>
        </div>
        </div>

        <!-- Modal Contents -->
        <div id="DemoModal2" class="modal fade ">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close"
                                data-dismiss="modal" aria-hidden="true">×</button>

                        <h4 class="modal-title">Browser Update</h4>
                    </div>

                    <div class="modal-body">
                        <p><fmt:message key="reservation.gotoaccount"/> </p>

                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal" onclick='location.href="/welcome"'>ОК</button>

                    </div>

                </div>
            </div>
        </div>

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
        <h3 align="center"><fmt:message key="reservation.picked"/> </h3>

                <p><fmt:message key="booking.datefrom"/> "${dateFrom}"</p>

                <p><fmt:message key="booking.dateto"/> "${dateTo}""</p>

                <p> <fmt:message key="room.type"/> "${type}"</p>

                <p><fmt:message key="room.bed"/> "${singledouble}"</p>

                <p><fmt:message key="customer.prepayment"/> "${((prepayment*50)/100)}"</p>

                <p><fmt:message key="room.id"/> "${randomRoom.id}"</p>


    </div>
    <div class="accordion" id="accordion2" align="center">
        <div class="accordion-group">
            <div class="accordion-heading">
                <h3 align="center"><fmt:message key="reservation.bookingprocedure"/> </h3>
                <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne">
                   <fmt:message key="reservation.guestinformation"/>
                </a>
            </div>
            <div id="collapseOne" class="accordion-body collapse">
                <div class="accordion-inner">
                    <div class="form-group">
                        <label for="inputFirstName" class="col-sm-2 control-label"><fmt:message key="customer.name"/> </label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputFirstName" name="inputFirstName"
                                   placeholder="First name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputLastName" class="col-sm-2 control-label"><fmt:message key="customer.lastname"/> </label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputLastName" name="inputLastName"
                                   placeholder="Last name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputCity" class="col-sm-2 control-label"><fmt:message key="customer.city"/> </label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputCity" name="inputCity"
                                   placeholder="City">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputRegion" class="col-sm-2 control-label"><fmt:message key="customer.region"/> </label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputRegion" name="inputRegion"
                                   placeholder="Region">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputCountry" class="col-sm-2 control-label"><fmt:message key="customer.country"/> </label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputCountry" name="inputCountry"
                                   placeholder="Country">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassport" class="col-sm-2 control-label"><fmt:message key="customer.passport"/> </label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputPassport" name="inputPassport"
                                   placeholder="Passport No">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPhone" class="col-sm-2 control-label"><fmt:message key="customer.phone"/> </label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputPhone" name="inputPhone"
                                   placeholder="Phone No">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputEmail" class="col-sm-2 control-label"><fmt:message key="customer.email"/> </label>
                        <div class="col-sm-10">
                            <input type="email" class="form-control" id="inputEmail" name="inputEmail"
                                   placeholder="Email">
                        </div>
                    </div>


                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">

                            <button type="submit" class="btn" name="action" value="customerForm" > <fmt:message key="reservation.booking"/> </button>
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
</fmt:bundle>