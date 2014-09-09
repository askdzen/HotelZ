<%@tag description="StatusBar template" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@attribute name="statusbar" fragment="true" %>
<div id="statusbar">
    <jsp:invoke fragment="statusbar"/>
    <div id="statusbarcontent">

        <form  method="post" id="searchform">
            <p>
                Welcome, guest!

                <input type="submit" name="submit" value="Sign in" />

                <input type="password" name="password"  placeholder="Password"/>
                <input type="text" name="username"  placeholder="Login"/>




            </p>
        </form>

    </div>
</div>