<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${requestScope.lang}"/>
<%@ taglib uri="myTags" prefix="calc" %>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <title><fmt:message key="cars"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="parts/_admin_header.jsp"></jsp:include>
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
        <h3><fmt:message key="cars"/></h3>
        <button onclick="location.href='/addCar'"type="button" class="btn btn-success me-2"><fmt:message key="addCar"/></button>
    </div>
    <div class="table-responsive">
        <table class="table table-striped table-light">
            <thead>
            <tr>
                <th scope="col"><fmt:message key="carId"/></th>
                <th scope="col"><fmt:message key="carName"/></th>
                <th scope="col"><fmt:message key="Passengers"/></th>
                <th scope="col"><fmt:message key="price"/></th>
                <th scope="col"><fmt:message key="Transmission"/></th>
                <th scope="col"><fmt:message key="status"/></th>
                <th scope="col"><fmt:message key="actions"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.cars}" var="car">
                <tr>
                    <td>${car.id}</td>
                    <td>${car.model}</td>
                    <td>${car.passengers}</td>
                    <td>${car.price}$</td>
                    <td><fmt:message key="${car.transmission}"/></td>
                    <td><fmt:message key="${car.statusId}"/></td>
                    <c:choose>
                        <c:when test="${car.statusId=='BUSY'}">
                        <td>
                            <span class="text-secondary"><fmt:message key="edit"/></span>
                            &nbsp;&nbsp;&nbsp;
                            <span class="text-secondary"><fmt:message key="delete"/></span>
                        </td>
                    </c:when>
                    <c:otherwise>
                        <td>
                            <a href="/editCar?id=<c:out value='${car.id}'/>"><fmt:message key="edit"/></a>
                            &nbsp;&nbsp;&nbsp;
                            <a href="/deleteCar?id=<c:out value='${car.id}'/>"><fmt:message key="delete"/></a>
                        </td>
                    </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>



</div>


</body>
</html>
