<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${requestScope.lang}"/>
<%@ taglib uri="myTags" prefix="calc" %>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <title><fmt:message key="bookTitle"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
</head>
<body>
<jsp:include page="parts/_header.jsp"></jsp:include>

<%--<div class="card mb-3">--%>
<%--    <img src="${car.imageUrl}" class="card-img-top">--%>
<%--    <div class="card-body">--%>
<%--        <h5 class="card-title">${car.model}</h5>--%>
<%--        <p class="card-text">${car.transmission}</p>--%>
<%--        <p class="card-text"><fmt:message key="priceforoneday"/> ${car.price}$</p>--%>
<%--        <p class="card-text"><fmt:message key="priceforndays"/> <calc:totalPriceTag price="${car.price}" days="${days}"/>$</p>--%>
<%--&lt;%&ndash;        <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>&ndash;%&gt;--%>
<%--    </div>--%>
<%--</div>--%>
<div class="d-flex align-items-center justify-content-center">
    <div class="px-4 py-5 my-5 text-center">
        <div class="card mb-3">
            <img src="${car.imageUrl}" class="d-block mx-auto mb-4" width="499" height="399">
            <div class="card-body">
                <h5 class="card-title">Car info</h5>
                <p class="card-text"><fmt:message key="carModel"/> ${car.model}</p>
                <p class="card-text"><fmt:message key="transmission"/> ${car.transmission}</p>
                <p class="card-text"><fmt:message key="passengersPre"/> ${car.passengers}</p>
                <p class="card-text"><fmt:message key="priceforoneday"/> ${car.price}$   <fmt:message key="priceforndays"/> ${days} <fmt:message key="days"/> <calc:totalPriceTag price="${car.price}" days="${days}"/>$</p>
                <input class="form-check-input" type="checkbox" value="" id="flexCheckIndeterminate">
                <label class="form-check-label" for="flexCheckIndeterminate">
                    Personal driver option
                </label>
                <button type="button" class="btn btn-primary">Proceed booking</button>
            </div>
        </div>
</div>

<%--        <div class="d-grid gap-2 d-sm-flex justify-content-sm-center">--%>
<%--            <button type="button" class="btn btn-primary btn-lg px-4 gap-3">Primary button</button>--%>
<%--            <button type="button" class="btn btn-outline-secondary btn-lg px-4">Secondary</button>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>
<%--    <p>DAYS: ${days}</p>--%>
<%--    <p>${pickupDate}</p>--%>
<%--    <p>${dropoffDate}</p>--%>
<%--    <p>${car.model}</p>--%>
<%--    <p>${car.price}</p>--%>
<%--    <p>${car.transmission}</p>--%>
<%--    <img src="${car.imageUrl}" alt="">--%>

</body>
</html>


<%--<div class="px-4 py-5 my-5 text-center">--%>
<%--    <img class="d-block mx-auto mb-4" src="${car.imageUrl}" alt="" width="200" height="300">--%>
<%--    <h1 class="display-5 fw-bold">{car.model}</h1>--%>
<%--    <div class="col-lg-6 mx-auto">--%>
<%--        <p class="lead mb-4">Quickly design and customize responsive mobile-first sites with Bootstrap, the world’s most popular front-end open source toolkit, featuring Sass variables and mixins, responsive grid system, extensive prebuilt components, and powerful JavaScript plugins.</p>--%>
<%--        <div class="d-grid gap-2 d-sm-flex justify-content-sm-center">--%>
<%--            <button type="button" class="btn btn-primary btn-lg px-4 gap-3">Primary button</button>--%>
<%--            <button type="button" class="btn btn-outline-secondary btn-lg px-4">Secondary</button>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>
