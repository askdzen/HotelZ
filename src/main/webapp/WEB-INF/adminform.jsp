<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<fmt:bundle basename="${bundlelang}">
<html>
<head>
    <title><fmt:message key="adminform.title"/> </title>

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

    <button  onClick='location.href="/bookingtable"'   type="submit" class="adminbtn"><fmt:message key="adminform.gotoboking"/> </button>
    <hr>
    <button  onClick='location.href="/customerdetail"'  type="submit" class="adminbtn"><fmt:message key="adminform.gotocustomer"/> </button>
    <hr>
    <button  onClick='location.href="/roomdetail"' type="submit" class="adminbtn"><fmt:message key="adminform.gotoroomedit"/> </button>
    <hr>
    <button  onClick='location.href="/userdetail"' type="submit" class="adminbtn"><fmt:message key="adminform.gotouseredit"/> </button>
    <hr>
</jsp:body>
</t:genericpage>
</html>
</fmt:bundle>