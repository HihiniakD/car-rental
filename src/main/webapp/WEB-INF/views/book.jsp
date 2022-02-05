<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${requestScope.lang}"/>
<%@ taglib uri="myTags" prefix="calc" %>
<fmt:setBundle basename="messages"/>
<%@ page import="controller.Path" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><fmt:message key="checkout"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
</head>

<body>
<jsp:include page="parts/_header.jsp"></jsp:include>
<div class="container">
    <main>
        <div class="py-5 text-center">
            <h2><fmt:message key="checkout"/></h2>
            <c:choose>
                <c:when test="${requestScope.error != null}">
                    <div style="text-align: center;"><p class="text-danger fw-bold"><fmt:message key="${requestScope.error}"/></p></div>
                </c:when>
            </c:choose>
        </div>
        <div class="row g-5">
            <div class="col-md-5 col-lg-4 order-md-last">
                <h4 class="d-flex justify-content-between align-items-center mb-3">
                    <span class="text-primary"><fmt:message key="priceBreakdown"/></span>
                </h4>
                <ul class="list-group mb-3">
                    <li class="list-group-item d-flex justify-content-between lh-sm">
                        <div>
                            <h6 class="my-0"><fmt:message key="carRent"/></h6>
                            <small class="text-muted"><fmt:message key="for"/> ${days} <fmt:message key="daysTwo"/></small>
                        </div>
                        <span class="text-muted">${car_rental_price}$</span>
                    </li>
                        <c:if test = "${driver == true}">
                    <li class="list-group-item d-flex justify-content-between lh-sm">
                    <div>
                        <h6 class="my-0"><fmt:message key="personalDriver"/></h6>
                        <small class="text-muted"><fmt:message key="for"/> ${days} <fmt:message key="daysTwo"/></small>
                    </div>
                    <span class="text-muted">${driver_price}$</span>
                </li>
                        </c:if>
                    <li class="list-group-item d-flex justify-content-between">
                        <span><fmt:message key="totalPrice"/></span>
                        <strong>${total_price}$</strong>
                    </li>
                </ul>


            </div>
            <div class="col-md-7 col-lg-8">
                <h4 class="mb-3"><fmt:message key="orderDetails"/></h4>
                <form class="needs-validation" method="post" novalidate="" action="${Path.SEARCH_CARS_PATH+=Path.VIEW_DEAL_PATH+=Path.BOOK_PATH+=Path.PROCESS_BOOKING_PATH}">
                    <div class="row g-3">
                        <div class="col-sm-6">
                            <label for="FullName" class="form-label"><fmt:message key="name"/></label>
                            <input name="name" type="text" id="FullName" class="form-control" value="${user.name}">
                        </div>
                        <div class="col-sm-6">
                            <label for="disabledEmail" class="form-label"><fmt:message key="email"/></label>
                            <input type="text" id="disabledEmail" class="form-control" value="${user.email}" disabled>
                        </div>
                        <div class="col-sm-6">
                            <label for="disabledPhone" class="form-label"><fmt:message key="number"/></label>
                            <input type="text" id="disabledPhone" class="form-control" disabled value="${user.phone}">
                        </div>
                        <div class="col-sm-6">
                            <label for="disabledPickUpDate" class="form-label"><fmt:message key="pickupdate"/></label>
                            <input type="text" id="disabledPickUpDate" class="form-control" placeholder="${pickupDate}" disabled>
                        </div>
                        <div class="col-sm-6">
                            <label for="disabledDropOffDate" class="form-label"><fmt:message key="dropoffdate"/></label>
                            <input type="text" id="disabledDropOffDate" class="form-control" placeholder="${dropoffDate}" disabled>
                        </div>
                        <div class="col-sm-6">
                            <label for="disabledPickUpCity" class="form-label"><fmt:message key="pickupcity"/></label>
                            <input type="text" id="disabledPickUpCity" class="form-control" disabled value="${city}">
                        </div>
                    </div>
                    <hr class="my-4">

                    <h4 class="mb-3"><fmt:message key="payment"/></h4>


                    <div class="row gy-3">
                        <div class="col-md-6">
                            <label for="cc_name" class="form-label"><fmt:message key="cardHolderName"/></label>
                            <input name="cc_name" type="text" class="form-control" id="cc_name" placeholder="" required="">
                            <small class="text-muted"><fmt:message key="cardHolderNameHelp"/></small>
                        </div>

                        <div class="col-md-6">
                            <label for="cc_number" class="form-label"><fmt:message key="cardNumber"/></label>
                            <input name="cc_number" type="text" class="form-control" id="cc_number" placeholder="" required="">
                        </div>

                        <div class="col-md-3">
                            <label for="cc_expiration" class="form-label"><fmt:message key="expiryDate"/></label>
                            <input name="cc_expiration" type="text" class="form-control" id="cc_expiration" placeholder="" required="">
                            <div class="invalid-feedback">
                                Expiration date required
                            </div>
                        </div>

                        <div class="col-md-3">
                            <label for="cc_cvv" class="form-label"><fmt:message key="cvv"/></label>
                            <input name="cvv" type="text" class="form-control" id="cc_cvv" placeholder="" required="">
                            <div class="invalid-feedback">
                                Security code required
                            </div>
                        </div>
                    </div>

                    <hr class="my-4">

                    <button class="w-100 btn btn-success btn" type="submit"><fmt:message key="book"/></button>
                </form>
            </div>
        </div>
    </main>

</div>

</body>
</html>
