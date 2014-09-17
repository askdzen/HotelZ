<%@tag description="StatusBar template" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@attribute name="statusbar" fragment="true" %>
<fmt:bundle basename="${bundlelang}" prefix="statusbar.">
    <div id="statusbar">
        <jsp:invoke fragment="statusbar"/>
        <div id="statusbarcontent">

            <form method="post" id="searchform">
                <p>
                    <fmt:message key="welcome"/>
                    <button type="submit" class="b2" name="submit" value=""><fmt:message key="sign"/></button>

                    <input type="password" name="password" placeholder="<fmt:message key="password"/>"/>
                    <input type="text" name="username" placeholder="<fmt:message key="login"/>"/>



                </p>
            </form>

        </div>
    </div>
</fmt:bundle>