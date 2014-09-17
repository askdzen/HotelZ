<%@tag description="Accordion template" pageEncoding="UTF-8" %>
<%@attribute name="accordion" fragment="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="../../static/mistral.ttf" rel="stylesheet" type="text/css" />
<link href="webjars/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="../static/style.css" rel="stylesheet" media="screen">

<fmt:bundle basename="${bundlelang}" prefix="accordion.">


    <div class="accordion" id="accordion2" align="center">

        <div class="accordion-group">
            <div class="accordion-heading">
                <a id="accordion" class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne">
                    <fmt:message key="roomTypes"/>
                </a>
            </div>
            <div id="collapseOne" class="accordion-body collapse">
                <div class="accordion-inner">
                   <fmt:message key="roomDescription"/>
                </div>
            </div>
        </div>
        <div class="accordion-group">
            <div class="accordion-heading">
                <a id="accordion1" class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseTwo">
                    <fmt:message key="hotelServices"/>
                </a>
            </div>
            <div id="collapseTwo" class="accordion-body collapse">
                <div class="accordion-inner">
                   <fmt:message key="hotelServicesList"/>
                </div>
            </div>
        </div>
    </div>
</fmt:bundle>