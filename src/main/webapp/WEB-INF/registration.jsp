<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<html>
<head>

    <title>Registration page</title>
</head>
<t:genericpage>

    <jsp:attribute name="header"/>
    <jsp:attribute name="accordion"/>
    <jsp:attribute name="carousel"/>
    <jsp:attribute name="footer"/>
    <jsp:body>
        <div align="center">
        <form id="registration" class="form-horizontal" role="form" method="post">


        <div class="form-group">
        <label for="inputUsername" class="col-sm-2 control-label">Введите логин:</label>

        <div class="col-sm-10">
        <input type="text" class="form-control" id="inputUsername" name="inputUsername"
        placeholder="Login "> <label for="inputUsername"> ${badusername}</label>

        </div>
        </div>
        <div class="form-group">
        <label for="inputPassword" class="col-sm-2 control-label">Введите пароль:</label>

        <div class="col-sm-10">
        <input type="password" class="form-control" id="inputPassword" name="inputPassword"
        placeholder="Password">
        </div>
        </div>
        <div class="form-group">
        <label for="inputConfirmPassword" class="col-sm-2 control-label">Подтвердите пароль:</label>

        <div class="col-sm-10">
        <input type="password" class="form-control" id="inputConfirmPassword" name="inputConfirmPassword"
        placeholder="Password "> <label for="inputPassword">  ${badparol} </label>
        </div>
        </div>

        <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">

        <button type="submit" class="btn"> Sign Up </button>
        </div>
        </div>
        </form>
        </div>
    </jsp:body>
</t:genericpage>

</html>


