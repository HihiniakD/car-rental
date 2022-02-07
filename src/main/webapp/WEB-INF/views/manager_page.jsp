<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${requestScope.lang}"/>
<%@ taglib uri="myTags" prefix="calc" %>
<fmt:setBundle basename="messages"/>


<html>
<head>
    <title><fmt:message key="managerPage"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="parts/_manager_header.jsp"></jsp:include>
<div class="container fluid">
    <c:if test="${sessionScope.message != null}">
    <div class="alert alert-success alert-dismissible fade show" role="alert">
        <p><fmt:message key="${sessionScope.message}"/></p>
        <c:remove var="message" scope="session" />
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
</c:if>
    <div class="py-3 text-center">
        <h3><fmt:message key="newBookingRequests"/></h3>
    </div>
    <div class="table-responsive">
        <table class="table table-striped table-light">
            <thead>
            <tr>
                <th scope="col"><fmt:message key="orderId"/></th>
                <th scope="col"><fmt:message key="clientFullName"/></th>
                <th scope="col"><fmt:message key="carName"/></th>
                <th scope="col"><fmt:message key="pickupcity"/></th>
                <th scope="col"><fmt:message key="pickupdate"/></th>
                <th scope="col"><fmt:message key="dropoffdate"/></th>
                <th scope="col"><fmt:message key="totalPrice"/></th>
                <th scope="col"><fmt:message key="status"/></th>
                <th scope="col"><fmt:message key="personalDriver"/></th>
                <th scope="col"><fmt:message key="comment"/></th>
                <th scope="col"><fmt:message key="actions"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.newOrders}" var="newOrder">
                <tr>
                    <td>${newOrder.id}</td>
                    <td>${newOrder.userName}</td>
                    <td>${newOrder.carName}</td>
                    <td><fmt:message key="${newOrder.cityName}"/></td>
                    <td>${newOrder.pickupDate}</td>
                    <td>${newOrder.dropoffDate}</td>
                    <td>${newOrder.totalPrice}$</td>
                    <td><fmt:message key="${newOrder.statusId}"/></td>
                    <td><fmt:message key="${newOrder.withDriver}"/></td>
                    <td>${newOrder.comment}</td>
                    <td>
                        <a href="/approveBooking?id=<c:out value='${newOrder.id}'/>"><fmt:message key="approveRequest"/></a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/declineBooking?id=<c:out value='${newOrder.id}'/>&car_id=<c:out value='${newOrder.carId}'/>"><fmt:message key="declineRequest"/></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>


    <div class="py-3 text-center">
        <h3><fmt:message key="bookingsInProgress"/></h3>
    </div>
    <div class="table-responsive">
        <table class="table table-striped table-light">
            <thead>
            <tr>
                <th scope="col"><fmt:message key="orderId"/></th>
                <th scope="col"><fmt:message key="clientFullName"/></th>
                <th scope="col"><fmt:message key="carName"/></th>
                <th scope="col"><fmt:message key="pickupcity"/></th>
                <th scope="col"><fmt:message key="pickupdate"/></th>
                <th scope="col"><fmt:message key="dropoffdate"/></th>
                <th scope="col"><fmt:message key="totalPrice"/></th>
                <th scope="col"><fmt:message key="status"/></th>
                <th scope="col"><fmt:message key="personalDriver"/></th>
                <th scope="col"><fmt:message key="comment"/></th>
                <th scope="col"><fmt:message key="actions"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.ordersInProgress}" var="orderInProgress">
                <tr>
                    <td>${orderInProgress.id}</td>
                    <td>${orderInProgress.userName}</td>
                    <td>${orderInProgress.carName}</td>
                    <td><fmt:message key="${orderInProgress.cityName}"/></td>
                    <td>${orderInProgress.pickupDate}</td>
                    <td>${orderInProgress.dropoffDate}</td>
                    <td>${orderInProgress.totalPrice}$</td>
                    <td><fmt:message key="${orderInProgress.statusId}"/></td>
                    <td><fmt:message key="${orderInProgress.withDriver}"/></td>
                    <td>${orderInProgress.comment}</td>
                    <td>
                        <a href="/finishBooking?id=<c:out value='${orderInProgress.id}'/>&car_id=<c:out value='${orderInProgress.carId}'/>"><fmt:message key="finishBooking"/></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>



    <div class="py-3 text-center">
        <h3><fmt:message key="finishedBookings"/></h3>
    </div>
    <div class="table-responsive">
        <table class="table table-striped table-light">
            <thead>
            <tr>
                <th scope="col"><fmt:message key="orderId"/></th>
                <th scope="col"><fmt:message key="clientFullName"/></th>
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
            <c:forEach items="${requestScope.finishedOrders}" var="finishedOrder">
                <tr>
                    <td>${finishedOrder.id}</td>
                    <td>${finishedOrder.userName}</td>
                    <td>${finishedOrder.carName}</td>
                    <td><fmt:message key="${finishedOrder.cityName}"/></td>
                    <td>${finishedOrder.pickupDate}</td>
                    <td>${finishedOrder.dropoffDate}</td>
                    <td>${finishedOrder.totalPrice}$</td>
                    <td><fmt:message key="${finishedOrder.statusId}"/></td>
                    <td><fmt:message key="${finishedOrder.withDriver}"/></td>
                    <td>${finishedOrder.comment}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>


    <div class="py-3 text-center">
        <h3><fmt:message key="declinedBookings"/></h3>
    </div>
    <div class="table-responsive">
        <table class="table table-striped table-light">
            <thead>
            <tr>
                <th scope="col"><fmt:message key="orderId"/></th>
                <th scope="col"><fmt:message key="clientFullName"/></th>
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
            <c:forEach items="${requestScope.declinedOrders}" var="declinedOrder">
                <tr>
                    <td>${declinedOrder.id}</td>
                    <td>${declinedOrder.userName}</td>
                    <td>${declinedOrder.carName}</td>
                    <td><fmt:message key="${declinedOrder.cityName}"/></td>
                    <td>${declinedOrder.pickupDate}</td>
                    <td>${declinedOrder.dropoffDate}</td>
                    <td>${declinedOrder.totalPrice}$</td>
                    <td><fmt:message key="${declinedOrder.statusId}"/></td>
                    <td><fmt:message key="${declinedOrder.withDriver}"/></td>
                    <td>${declinedOrder.comment}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</div>
</body>
</html>
