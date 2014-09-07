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
        <script>
            $(document).ready(function(){
                $(".close").click(function(){
                    $("#demo-alert").alert();
                });
            });
        </script>

        <div id="Demo-BS" ${hidden} style="padding:30px;" align="center">
            <div class="alert alert-success" id="demo-alert">
                <a href="#" class="close" data-dismiss="alert">×</a>
                <strong>
                    <p>${badusername} </p>
                    <a href="#DemoModal2" data-toggle="modal">для получения совета нажмите на ссылку</a>  </strong>
            </div>
                </div>


        <!-- Modal Contents -->
        <div id="DemoModal2" class="modal fade ">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close"
                                data-dismiss="modal" aria-hidden="true">×</button>

                        <h4 class="modal-title">Browser Update</h4>
                    </div>

                    <div class="modal-body">
                        <p>${context}</p>

                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">ОК</button>

                    </div>

                </div>
            </div>
        </div>
    </jsp:body>
</t:genericpage>

</html>


