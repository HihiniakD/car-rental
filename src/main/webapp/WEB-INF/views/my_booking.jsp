<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="controller.Path" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${requestScope.lang}"/>
<fmt:setBundle basename="messages"/>
<%@ taglib uri="myTags" prefix="showDate" %>


<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <title><fmt:message key="myBookingHistory"/></title>
</head>
<body>
<jsp:include page="parts/_header.jsp"></jsp:include>
<div class="container fluid">
    <c:if test="${sessionScope.success_message != null}">
        <div class="alert alert-success alert-dismissible fade show" role="alert">
            <p><fmt:message key="${sessionScope.success_message}"/></p>
            <c:remove var="success_message" scope="session" />
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    </c:if>
    <div class="py-5 text-center">
        <h3><fmt:message key="myBookingHistory"/></h3>
    </div>
    <div class="table-responsive">
        <table class="table table-striped table-sm table-light">
            <thead>
            <tr>
                <th scope="col"><fmt:message key="carName"/></th>
                <th scope="col"><fmt:message key="pickupcity"/></th>
                <th scope="col"><fmt:message key="pickupdate"/></th>
                <th scope="col"><fmt:message key="dropoffdate"/></th>
                <th scope="col"><fmt:message key="totalPrice"/></th>
                <th scope="col"><fmt:message key="status"/></th>
                <th scope="col"><fmt:message key="personalDriver"/></th>
                <th scope="col"><fmt:message key="comment"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.orders}" var="order">
                <tr>
                    <td>${order.carName}</td>
                    <td><fmt:message key="${order.cityName}"/></td>
                    <td>${order.pickupDate}</td>
                    <td>${order.dropoffDate}</td>
                    <td>${order.totalPrice}$</td>
                    <td><fmt:message key="${order.statusId == 'PROCESSING' ? 'processing':'approved'}"/></td>
                    <td><fmt:message key="${order.withDriver}"/></td>
                    <td>${order.comment}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
