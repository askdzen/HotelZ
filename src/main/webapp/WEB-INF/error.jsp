<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Error page</title>
</head>
<t:genericpage>

    <jsp:attribute name="header"/>
    <jsp:attribute name="accordion"/>
    <jsp:attribute name="carousel"/>
    <jsp:attribute name="footer"/>
    <jsp:body>
<div>
    <div id="error">
        <p id="err1">Error ${statuscode}</p>
        <p id="err2" >Message ${message}</p>
        <p id="err3">${requesturi}</p>
        <p id="err4"> ${servletname}</p>
        <p id="err5">${throwable}</p>
    </div>


</div>

</jsp:body>
</t:genericpage>
</html>
