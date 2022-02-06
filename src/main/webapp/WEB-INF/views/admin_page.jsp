<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${requestScope.lang}"/>
<%@ taglib uri="myTags" prefix="calc" %>
<fmt:setBundle basename="messages"/>
<%@ page import="controller.Path" %>
<html>
<head>
    <title><fmt:message key="adminPage"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
</head>
<body>
<jsp:include page="parts/_admin_header.jsp"></jsp:include>
<div class="container fluid">
    <div class="py-3 text-center">
        <h3><fmt:message key="adminPage"/></h3>
        <button onclick="location.href='/users'"type="button" class="btn btn-success me-2">USERS</button>
        <button onclick="location.href='/cars'"type="button" class="btn btn-success me-2">CARS</button>
        <button onclick="location.href='/add_manager'"type="button" class="btn btn-success me-2">MANAGERS</button>
    </div>

</div>

</body>
</html>
