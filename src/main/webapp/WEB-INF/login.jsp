<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<html>

<head>
    <title>Login page</title>
</head>
<t:statusbar>
    <jsp:attribute name="statusbar">
<div id="statusbarcontent">
    <a href="/registration"  >registration</a>
</div>
    </jsp:attribute>

</t:statusbar>
<t:genericpage>

    <jsp:attribute name="header">

    </jsp:attribute>

    <jsp:attribute name="accordion">
<t:accordion>
    <jsp:attribute name="accordion">

    </jsp:attribute>
</t:accordion>
    </jsp:attribute>
    <jsp:attribute name="carousel">
        <t:carousel>
            <jsp:attribute name="carousel"/>
        </t:carousel>
    </jsp:attribute>

    <jsp:attribute name="footer">

    </jsp:attribute>
    <jsp:body>

    </jsp:body>
</t:genericpage>

</html>


