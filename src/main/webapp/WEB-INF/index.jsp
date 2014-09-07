<%@ page import="com.epam.ad.action.LoginAction" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>

</head>
<body>
 <%

     session.setAttribute("registrationGood","");
     response.sendRedirect(request.getContextPath()+"/login");

 %>
</body>
</html>
