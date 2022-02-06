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
    <title><fmt:message key="users"/></title>
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
        <h3><fmt:message key="users"/></h3>
    </div>
    <div class="table-responsive">
        <table class="table table-striped table-light">
            <thead>
            <tr>
                <th scope="col"><fmt:message key="userId"/></th>
                <th scope="col"><fmt:message key="clientFullName"/></th>
                <th scope="col"><fmt:message key="email"/></th>
                <th scope="col"><fmt:message key="number"/></th>
                <th scope="col"><fmt:message key="blocked"/></th>
                <th scope="col"><fmt:message key="actions"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.users}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.email}</td>
                    <td>${user.phone}</td>
                    <td><fmt:message key="${user.blocked}"/></td>
                    <td>
                        <c:choose>
                            <c:when test="${user.blocked == true}">
                                <a href="/?id=<c:out value='${user.id}'/>&blocked=<c:out value='${user.blocked}'/>"><fmt:message key="unblockUser"/></a>
                            </c:when>
                            <c:otherwise>
                                <a href="/?id=<c:out value='${user.id}'/>&blocked=<c:out value='${user.blocked}'/>"><fmt:message key="blockUser"/></a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <%@ include file="parts/pagination.jsp" %>
    </div>



</div>


</body>
</html>
