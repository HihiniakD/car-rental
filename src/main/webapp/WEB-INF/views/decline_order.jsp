<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${requestScope.lang}"/>
<%@ taglib uri="myTags" prefix="calc" %>
<fmt:setBundle basename="messages"/>


<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
    <title><fmt:message key="declineRequest"/></title>
</head>
<body>
<jsp:include page="parts/_manager_header.jsp"></jsp:include>
<section class="h-100">
    <div class="container h-100">
        <div class="row justify-content-sm-center h-100">
            <div class="col-xxl-4 col-xl-5 col-lg-5 col-md-7 col-sm-9">
                <div class="text-center my-3">
                    <img src="https://cdn.worldvectorlogo.com/logos/rent-a-car-systeme.svg" alt="logo" width="100">
                </div>
                <div class="card shadow-lg">
                    <div class="card-body p-5">
                        <h1 class="fs-4 card-title fw-bold mb-4"><fmt:message key="declineRequest"/></h1>
                        <form method="POST" action="/processDeclineBooking">
                            <div class="mb-3">
                                <label class="mb-2 text-muted" for="id"><fmt:message key="orderId"/></label>
                                <input id="id" type="text" class="form-control" name="id" required value="<c:out value="${order.id}"/>" readonly>
                            </div>
                            <div class="mb-3">
                                <label class="mb-2 text-muted" for="id"><fmt:message key="carId"/></label>
                                <input id="carId" type="text" class="form-control" name="carId" required value="<c:out value="${order.carId}"/>" readonly>
                            </div>
                            <div class="mb-3">
                                <div class="mb-2 w-100">
                                    <label class="text-muted" for="comment"><fmt:message key="comment"/></label>
                                </div>
                                <input id="comment" type="text" class="form-control" name="comment" required value="<c:out value="${order.comment}"/>">
                            </div>

                            <div class="d-flex align-items-center">

                                <button type="submit" class="btn btn-success ms-auto">
                                    <fmt:message key="declineRequest"/>
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>


</body>
</html>
