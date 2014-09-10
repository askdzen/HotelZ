<%@tag description="StatusBar template" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@attribute name="welcomelayout" fragment="true" %>
<fmt:bundle basename="i18n.message">
    <div id="welcomelayout">
        <jsp:invoke fragment="welcomelayout"/>
        <%--<div id="statusbarcontent">--%>
            <p id="welcome"> Welcome, ${user.username} ! </p>
            <a id="account" href="/welcome">Вернуться на главную страницу</a>
        </div>
    <%--</div>--%>
</fmt:bundle>