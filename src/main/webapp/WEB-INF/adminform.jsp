<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${fmtlocale}"/>
<fmt:bundle basename="i18n.message">
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

    <button  onClick='location.href="/HotelReservation-1.0-SNAPSHOT/bookingtable"'   type="submit" class="adminbtn"><fmt:message key="adminform.gotoboking"/> </button>
    <hr>
    <button  onClick='location.href="/HotelReservation-1.0-SNAPSHOT/customerdetail"'  type="submit" class="adminbtn"><fmt:message key="adminform.gotocustomer"/> </button>
    <hr>
    <button  onClick='location.href="/HotelReservation-1.0-SNAPSHOT/roomdetail"' type="submit" class="adminbtn"><fmt:message key="adminform.gotoroomedit"/> </button>
    <hr>
    <button  onClick='location.href="/HotelReservation-1.0-SNAPSHOT/userdetail"' type="submit" class="adminbtn"><fmt:message key="adminform.gotouseredit"/> </button>
    <hr>
</jsp:body>
</t:genericpage>
</html>
</fmt:bundle>