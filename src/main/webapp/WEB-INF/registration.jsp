<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<fmt:bundle basename="${bundlelang}">
<html>
<head>

    <title><fmt:message key="registration.title"/> </title>
</head>
<t:genericpage>

    <jsp:attribute name="header"/>
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
        <div id="logo1">
        <div id="Demo-BS" ${hidden} style="padding:30px;" align="center">
            <div class="alert alert-success" id="demo-alert">
                <a href="#" class="close" data-dismiss="alert">×</a>
                <p><fmt:message key="${registrationmessage}"/> </p>

            </div>
        </div>
        </div>
        <div align="center">
        <form id="registration" class="form-horizontal" role="form" method="post">


        <div class="form-group">
        <label for="inputUsername" class="col-sm-2 control-label"><fmt:message key="statusbar.login"/> </label>

        <div class="col-sm-10">
        <input type="text" class="form-control" id="inputUsername" name="inputUsername"
        placeholder="Login "> <label for="inputUsername" ></label>${badusername}

        </div>
        </div>
        <div class="form-group">
        <label for="inputPassword" class="col-sm-2 control-label"><fmt:message key="statusbar.password"/> </label>

        <div class="col-sm-10">
        <input type="password" class="form-control" id="inputPassword" name="inputPassword"
        placeholder="Password">
        </div>
        </div>
        <div class="form-group">
        <label for="inputConfirmPassword" class="col-sm-2 control-label"><fmt:message key="registration.confirmPassword"/> </label>

        <div class="col-sm-10">
        <input type="password" class="form-control" id="inputConfirmPassword" name="inputConfirmPassword"
        placeholder="Password "> <label for="inputPassword"></label> ${badparol}
        </div>
        </div>

        <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">

        <button type="submit" class="btn"> <fmt:message key="registration.signUp"/> </button>
        </div>
        </div>
        </form>
        </div>
    </jsp:body>
</t:genericpage>

</html>
</fmt:bundle>

