<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<div >


    <h3><fmt:message key="customer.createmessage"/> </h3>

    <form method="post" class="form-horizontal" role="form">

        <div class="form-group">
            <label for="inputFirstName" class="col-sm-2 control-label"><fmt:message key="customer.name"/> </label>

            <div class="col-sm-10">
                <input type="text" class="form-control" id="inputFirstName" name="inputFirstNamec" value=""
                       placeholder="First name" >
            </div>
        </div>
        <div class="form-group">
            <label for="inputLastName" class="col-sm-2 control-label">fmt:message key="customer.lastname"/></label>

            <div class="col-sm-10">
                <input type="text" class="form-control" id="inputLastName" name="inputLastNamec" value=""
                       placeholder="Last name">
            </div>
        </div>
        <div class="form-group">
            <label for="inputCity" class="col-sm-2 control-label"><fmt:message key="customer.city"/></label>

            <div class="col-sm-10">
                <input type="text" class="form-control" id="inputCity" name="inputCityc" value=""
                       placeholder="City">
            </div>
        </div>
        <div class="form-group">
            <label for="inputRegion" class="col-sm-2 control-label"><fmt:message key="customer.region"/></label>

            <div class="col-sm-10">
                <input type="text" class="form-control" id="inputRegion" name="inputRegionc" value=""
                       placeholder="Region">
            </div>
        </div>
        <div class="form-group">
            <label for="inputCountry" class="col-sm-2 control-label"><fmt:message key="customer.country"/></label>

            <div class="col-sm-10">
                <input type="text" class="form-control" id="inputCountry" name="inputCountryc" value=""
                       placeholder="Country">
            </div>
        </div>
        <div class="form-group">
            <label for="inputPassport" class="col-sm-2 control-label"><fmt:message key="customer.passport"/></label>

            <div class="col-sm-10">
                <input type="text" class="form-control" id="inputPassport" name="inputPassportc" value=""
                       placeholder="Passport No">
            </div>
        </div>
        <div class="form-group">
            <label for="inputPhone" class="col-sm-2 control-label"><fmt:message key="customer.phone"/> </label>

            <div class="col-sm-10">
                <input type="text" class="form-control" id="inputPhone" name="inputPhonec" value=""
                       placeholder="Phone No">
            </div>
        </div>
        <div class="form-group">
            <label for="inputEmail" class="col-sm-2 control-label"><fmt:message key="customer.email"/></label>

            <div class="col-sm-10">
                <input type="email" class="form-control" id="inputEmail" name="inputEmailc" value=""
                       placeholder="Email">
            </div>
        </div>
        <div class="form-group">
            <label for="inputPrepayment" class="col-sm-2 control-label"><fmt:message key="customer.prepayment"/></label>

            <div class="col-sm-10">
                <input type="number" class="form-control" id="inputPrepayment" name="inputPrepaymentc" value=""
                       placeholder="inputPrepayment">
            </div>
        </div>
        <div class="form-group">
            <label for="inputBookId" class="col-sm-2 control-label"><fmt:message key="customer.bookid"/> </label>

            <div class="col-sm-10">
                <input type="number" class="form-control" id="inputBookId" name="inputBookIdc" value=""
                       placeholder="inputBookId">
            </div>
        </div>
        <div class="form-group">
            <label for="userId" class="col-sm-2 control-label"><fmt:message key="customer.userid"/> </label>

            <div class="col-sm-10">
                <input type="number" class="form-control" id="userId" name="userIdc" value=""
                       placeholder="inputUserId">
            </div>
        </div>



        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">

                <button type="submit" class="btn" > <fmt:message key="create"/> </button>
            </div>
        </div>
    </form>


</div>
</body>
</html>
</fmt:bundle>