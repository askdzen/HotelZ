<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@attribute name="paginationtag" fragment="true" %>
<fmt:bundle basename="i18n.message" prefix="pagination.">

<div id="paginationtag">
    <jsp:invoke fragment="paginationtag"/>


    <form class="form-inline">
        <label for="rows1"> <fmt:message key="rowCount"/> </label>
        <input type="text" name="rows" value="${rowsCount}" id="rows1"/>

    </form>

    <form method="get">
        <button  type="submit" class="btn" name="create" ${disabled}><fmt:message key="addItem"/> </button>
    </form>

    <button  onClick='location.href="/${gotohome}"' type="submit" class="btn"><fmt:message key="goHome"/> </button>

    <div>
        <ul id="paginat" class="nav nav-tabs">
            <li>
                <form id="back" class="form-inline">

                    <div>
                        <input type="text" name="page" value="${pageNumber-1}" hidden="hidden"/>

                        <input type="text" name="rows" value="${rowsCount}" hidden="hidden"/>
                        <input type="text" name="column" value="${column}" hidden="hidden"/>
                        <input type="text" name="value" value="${value}" hidden="hidden"/>
                    </div>

                    <button id="changeBtn2"  type="submit"
                            class="btn" ${backdisabled}><fmt:message key="back"/> </button>

                </form>
            </li>
            <li>
                <form id="pagination" class="form-inline">
                    <ul class="pagination">
                        <c:forEach items="${paginationlist}" var="pl">

                            <li><a href="${pagename}?page=${pl.intValue()}&rows=${rowsCount}&column=${column}&value=${value}"
                                   name="page">${pl.intValue()}</a></li>
                            <input type="text" name="column" value="${column}" hidden="hidden"/>
                            <input type="text" name="value" value="${value}" hidden="hidden"/>
                        </c:forEach>
                    </ul>
                </form>
            </li>
            <li>
                <form id="next" class="form-inline">
                    <div>

                        <input type="text" name="page" value="${pageNumber+1}" hidden="hidden"/>
                        <input type="text" name="rows" value="${rowsCount}" hidden="hidden"/>
                        <input type="text" name="column" value="${column}" hidden="hidden"/>
                        <input type="text" name="value" value="${value}" hidden="hidden"/>
                    </div>

                    <button id="changeBtn3" type="submit" class="btn"  ${nextdisabled}><fmt:message key="next"/> </button>

                </form>
            </li>
        </ul>
    </div>
</div>
</fmt:bundle>