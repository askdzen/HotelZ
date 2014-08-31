<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title></title>
    <script type='text/javascript' src='<c:url value="webjars/jquery/2.0.3/jquery.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="webjars/bootstrap/3.0.0/js/bootstrap.min.js"/>'></script>

    <link href="../static/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body>

<div id="roomAdminGeneral">

<table class="table table-bordered table-hover table-condensed">
    <caption>Customer Details</caption>
    <thead>
    <tr>
        <th>ID</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>City</th>
        <th>Region</th>
        <th>Country</th>
        <th>Passport</th>
        <th>Phone</th>
        <th>Email</th>
        <th>Prepayment</th>
        <th>Book Id</th>
        <th>User Id</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach items="${list}" var="cust">

        <tr>
            <td>${cust.id}</td>
            <td>"${cust.firstName}</td>
            <td>${cust.lastName}</td>
            <td>${cust.city}</td>
            <td>${cust.region}</td>
            <td>"${cust.country}</td>
            <td>${cust.passport}</td>
            <td>${cust.phone}</td>
            <td>${cust.email}</td>
            <td>"${cust.prepayment}</td>
            <td>"${cust.bookId}</td>
            <td>"${cust.userId}</td>
        </tr>
    </c:forEach>

    <hr>
    <form class="form-inline">
        <label for="rows1"> Введите количество строк на странице:</label>
        <input type="text" name="rows" value="${rowsCount}" id="rows1"/>

    </form>


    <tr>
        <div>
            <ul id="change" class="nav nav-tabs">
                <li>
                    <form id="back" class="form-inline">

                        <div>
                            <input type="text" name="page" value="${pageNumber-1}" hidden="hidden"/>

                            <input type="text" name="rows" value="${rowsCount}" hidden="hidden"/>
                        </div>

                        <button id="changeBtn2" name="action" value="bookingTableEdit" type="submit"
                                class="btn" ${backdisabled}>Back
                        </button>

                    </form>
                </li>
                <li>
                    <form id="pagination" class="form-inline">
                        <ul class="pagination">
                            <c:forEach items="${paginationlist}" var="pl">

                                <li><a href="customerdetail?page=${pl.intValue()}&rows=${rowsCount}"
                                       name="page">${pl.intValue()}</a></li>

                            </c:forEach>
                        </ul>
                    </form>
                </li>
                <li>
                    <form id="next" class="form-inline">
                        <div>

                            <input type="text" name="page" value="${pageNumber+1}" hidden="hidden"/>
                            <input type="text" name="rows" value="${rowsCount}" hidden="hidden"/>
                        </div>

                        <button id="changeBtn" type="submit" class="btn"  ${nextdisabled}>Next</button>

                    </form>
                </li>
            </ul>
        </div>
    </tr>

    </tbody>
</table>
<hr>

<button onClick='location.href="/adminform"' type="submit" class="btn">Перейти на
    главную страницу
</button>


<div class="tabbable" tabs-below> <!-- Only required for left/right tabs -->
    <ul class="nav nav-tabs">
        <li class="active"><a href="#tab1" data-toggle="tab">Добавить клиента</a></li>
        <li><a href="#tab2" data-toggle="tab">Изменить данные клиента</a></li>
        <li><a href="#tab3" data-toggle="tab">Удалить клиента</a></li>

    </ul>
    <div class="tab-content">
        <div class="tab-pane active" id="tab1">
            <p>

            <h3>Пожалуйста введите данные: </h3>

            <form method="post" class="form-horizontal" role="form">

                <div class="form-group">
                    <label for="inputFirstName" class="col-sm-2 control-label">First name</label>

                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="inputFirstName" name="inputFirstName"
                               placeholder="First name">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputLastName" class="col-sm-2 control-label">Last name</label>

                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="inputLastName" name="inputLastName"
                               placeholder="Last name">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputCity" class="col-sm-2 control-label">City</label>

                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="inputCity" name="inputCity"
                               placeholder="City">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputRegion" class="col-sm-2 control-label">Region</label>

                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="inputRegion" name="inputRegion"
                               placeholder="Region">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputCountry" class="col-sm-2 control-label">Country</label>

                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="inputCountry" name="inputCountry"
                               placeholder="Country">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassport" class="col-sm-2 control-label">Passport No</label>

                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="inputPassport" name="inputPassport"
                               placeholder="Passport No">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPhone" class="col-sm-2 control-label">Phone No</label>

                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="inputPhone" name="inputPhone"
                               placeholder="Phone No">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputEmail" class="col-sm-2 control-label">Email</label>

                    <div class="col-sm-10">
                        <input type="email" class="form-control" id="inputEmail" name="inputEmail"
                               placeholder="Email">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPrepayment" class="col-sm-2 control-label">Prepayment</label>

                    <div class="col-sm-10">
                        <input type="number" class="form-control" id="inputPrepayment" name="inputPrepayment"
                               placeholder="inputPrepayment">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputBookId" class="col-sm-2 control-label">Book Id</label>

                    <div class="col-sm-10">
                        <input type="number" class="form-control" id="inputBookId" name="inputBookId"
                               placeholder="inputBookId">
                    </div>
                </div>
                <div class="form-group">
                    <label for="userId" class="col-sm-2 control-label">User Id</label>

                    <div class="col-sm-10">
                        <input type="number" class="form-control" id="userId" name="userId"
                               placeholder="inputBookId">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox"> Remember Password
                            </label>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">

                        <button type="submit" class="btn" name="action" value="createCustomer"> Confirm</button>
                    </div>
                </div>
            </form>


        </div>
        <div class="tab-pane" id="tab2">
            <p>Привет, я 2-я секция.</p>
        </div>
        <div class="tab-pane" id="tab3">
            <p>Привет, я 3-я секция.</p>
        </div>
        <div class="tab-pane" id="tab4">
            <p>Привет, я 4-я секция.</p>
        </div>
    </div>
</div>


</div>

</body>
</html>
