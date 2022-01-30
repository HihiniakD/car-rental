<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="controller.Path" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${requestScope.lang}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title><fmt:message key="maintitle"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
</head>

<body>
<jsp:include page="WEB-INF/views/parts/_header.jsp"></jsp:include>
<section class="h-100">
    <div class="container h-100">
        <div class="row justify-content-sm-center h-100">
            <div class="col-xxl-4 col-xl-5 col-lg-5 col-md-7 col-sm-9">
                <div class="text-center my-5">
                    <img src="https://getbootstrap.com/docs/5.0/assets/brand/bootstrap-logo.svg" alt="logo" width="100">
                </div>
                <div class="card shadow-lg">
                    <div class="card-body p-5">
                        <h1 class="fs-4 card-title fw-bold mb-4"><fmt:message key="greeting"/></h1>
                        <form method="POST" action="${Path.SEARCH_CARS_PATH}">
                            <div class="mb-3">
                                <label class="mb-2 text-muted" > <fmt:message key="pickupcity"/></label>
                                <select name="city" class="form-select">
                                    <option value="1"><fmt:message key="kyiv"/></option>
                                    <option value="2"><fmt:message key="kharkiv"/></option>
                                    <option value="3"><fmt:message key="lviv"/></option>
                                </select>
                            </div>

                            <div class="mb-3">
                                <div class="mb-2 w-100">
                                    <label class="text-muted"><fmt:message key="pickupdate"/></label>
                                </div>
                                <input class="form-control" pattern="\d{4}-\d{2}-\d{2}" name="pickup" type="text" placeholder="<%= java.time.LocalDate.now() %>" required>
                            </div>

                            <div class="mb-3">
                                <div class="mb-2 w-100">
                                    <label class="text-muted"><fmt:message key="dropoffdate"/></label>
                                </div>
                                <input class="form-control" pattern="\d{4}-\d{2}-\d{2}" type="text" name="dropoff" placeholder="<%= java.time.LocalDate.now().plusDays(3) %>" aria-label="Type" required>
                            </div>

                            <div class="d-flex align-items-center">
                                <button type="submit" class="btn btn-success ms-auto">
                                    <fmt:message key="search"/>
                                </button>
                            </div>
                        </form>
                    </div>

                </div>
                <div class="text-center mt-5 text-muted">
                    <fmt:message key="copyright"/>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
