<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${requestScope.lang}"/>
<%@ taglib uri="myTags" prefix="calc" %>
<fmt:setBundle basename="messages"/>
<%@ page import="controller.Path" %>


<html>
<head>
    <title><fmt:message key="bookTitle"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
</head>
<body>
<jsp:include page="parts/_header.jsp"></jsp:include>
<c:set var="bookLink" value="${Path.SEARCH_CARS_PATH+=Path.VIEW_DEAL_PATH+=Path.BOOK_PATH +='?id=' += car.id +='&days=' += days +='&pickupDate=' += pickupDate +='&dropoffDate='+=dropoffDate}"/>

<div class="d-flex align-items-center justify-content-center">
    <div class="px-4 py-5 my-5 text-center">
        <div class="card mb-3">
            <img src="${car.imageUrl}" class="d-block mx-auto mb-4" width="399" height="299">
            <div class="card-body">
                <h5 class="card-title lh-1"><fmt:message key="yourDeal"/></h5>
                <p class="card-text lh-1"><fmt:message key="carModel"/> ${car.model}</p>
                <p class="card-text lh-1"><fmt:message key="transmission"/> ${car.transmission}</p>
                <p class="card-text lh-1"><fmt:message key="passengersPre"/> ${car.passengers}</p>
                <p class="card-text lh-1"><fmt:message key="priceforoneday"/> ${car.price}$   <fmt:message key="priceforndays"/> ${days} <fmt:message key="days"/> <calc:totalPriceTag price="${car.price}" days="${days}"/>$</p>
                <form method="post" action="${Path.SEARCH_CARS_PATH+=Path.VIEW_DEAL_PATH+=Path.BOOK_PATH}">
                    <input value="true" type="checkbox" name="driver" class="form-check-input" id="checkBox">
                    <label class="form-check-label" for="checkBox"><fmt:message key="personalDriver"/></label>
                    <button type="submit" class="btn btn-success"><fmt:message key="goToBook"/></button>
                </form>
<%--                <input class="form-check-input" type="checkbox" value="" id="flexCheckIndeterminate">--%>
<%--                <label class="form-check-label" for="flexCheckIndeterminate">--%>
<%--                    <fmt:message key="personalDriver"/>--%>
<%--                </label> <br><br>--%>
<%--                <button type="button" class="btn btn-primary"><fmt:message key="goToBook"/></button>--%>
            </div>
        </div>
</div>
</div>
</body>
</html>



