<%@tag description="StatusBar template" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@attribute name="welcomelayout" fragment="true" %>
<fmt:setLocale value="${fmtlocale}"/>
<fmt:bundle basename="i18n.message">
    <div id="welcomelayout">
        <jsp:invoke fragment="welcomelayout"/>
        <%--<div id="statusbarcontent">--%>
            <p id="welcome"> <fmt:message key="welcomelayout.welcome"/> , ${user.username} ! </p>
            <a id="account" href="/HotelReservation-1.0-SNAPSHOT/exitaccount"><fmt:message key="welcomelayout.gotomain"/> </a>
        </div>
    <%--</div>--%>
</fmt:bundle>