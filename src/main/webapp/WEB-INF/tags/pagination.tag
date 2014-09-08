<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@attribute name="paginationtag" fragment="true" %>

<div id="paginationtag">
    <jsp:invoke fragment="paginationtag"/>

<br><br>
    <form class="form-inline">
        <label for="rows1"> Введите количество строк на странице:</label>
        <input type="text" name="rows" value="${rowsCount}" id="rows1"/>

    </form>

    <form method="get">
        <button type="submit" class="btn" name="create">Добавить запись</button>
    </form>

    <button onClick='location.href="/adminform"' type="submit" class="btn">Перейти на
        главную страницу
    </button>
    <div>
        <ul id="change" class="nav nav-tabs">
            <li>
                <form id="back" class="form-inline">

                    <div>
                        <input type="text" name="page" value="${pageNumber-1}" hidden="hidden"/>

                        <input type="text" name="rows" value="${rowsCount}" hidden="hidden"/>
                        <input type="text" name="column" value="${column}" hidden="hidden"/>
                        <input type="text" name="value" value="${value}" hidden="hidden"/>
                    </div>

                    <button id="changeBtn2"  type="submit"
                            class="btn" ${backdisabled}>Back
                    </button>

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

                    <button id="changeBtn" type="submit" class="btn"  ${nextdisabled}>Next</button>

                </form>
            </li>
        </ul>
    </div>
</div>
