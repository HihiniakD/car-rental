<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${requestScope.lang}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title><fmt:message key="addManager"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
</head>

<body>
<jsp:include page="parts/_admin_header.jsp"></jsp:include>
<section class="h-100">
    <div class="container h-100">
        <div class="row justify-content-sm-center h-100">
            <div class="col-xxl-4 col-xl-5 col-lg-5 col-md-7 col-sm-9">
                <div class="text-center my-3">
                    <img src="https://cdn.worldvectorlogo.com/logos/rent-a-car-systeme.svg" alt="logo" width="100">
                </div>
                <div class="card shadow-lg">
                    <div class="card-body p-5">
                        <h1 class="fs-4 card-title fw-bold mb-4"><fmt:message key="addManager"/></h1>
                        <c:choose>
                            <c:when test="${requestScope.error != null}">
                                <div style="text-align: center;"><p class="text-danger fw-bold"><fmt:message key="${requestScope.error}"/></p></div>
                            </c:when>
                        </c:choose>
                        <form method="POST" action="/processAddManager">
                            <div class="mb-3">
                                <label class="mb-2 text-muted" for="email"><fmt:message key="email"/></label>
                                <input id="email" type="email" class="form-control" name="email" required value="<c:out value="${requestScope.email}" default="" />">
                            </div>
                            <div class="mb-3">
                                <div class="mb-2 w-100">
                                    <label class="text-muted" for="password"><fmt:message key="password"/></label>
                                </div>
                                <input id="password" type="password" class="form-control" name="password" required value="<c:out value="${requestScope.password}" default="" />">
                                <div id="passwordHelp" class="form-text"><fmt:message key="passwordrules"/></div>
                            </div>
                            <div class="mb-3">
                                <div class="mb-2 w-100">
                                    <label class="text-muted" for="password"><fmt:message key="name"/></label>
                                </div>
                                <input id="name" type="text" class="form-control" name="name" required value="<c:out value="${requestScope.name}" default="" />">
                            </div>
                            <div class="mb-3">
                                <div class="mb-2 w-100">
                                    <label class="text-muted" for="password"><fmt:message key="number"/></label>
                                </div>
                                <input id="phone" type="text" inputmode="numeric" class="form-control" name="phone" required value="<c:out value="${requestScope.phone}" default="" />">
                                <div id="phoneHelp" class="form-text"><fmt:message key="phonerules"/></div>
                            </div>
                            <div class="d-flex align-items-center">
                                <button type="submit" class="btn btn-success ms-auto">
                                    <fmt:message key="addManager"/>
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

