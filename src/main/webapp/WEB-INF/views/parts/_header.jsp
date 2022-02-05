<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="controller.Path" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${requestScope.lang}"/>
<fmt:setBundle basename="messages"/>

<header class="p-3 bg-dark text-white">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <a href="${Path.MAIN_PATH}" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
                <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap"><use xlink:href="#bootstrap"></use></svg>
            </a>

            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li><a href="${Path.MAIN_PATH}" class="nav-link px-2 text-white"><fmt:message key="main"/></a></li>
            </ul>

            <div class="text-end">
                <c:choose>
                    <c:when test="${requestScope.lang == 'en'}">
                        <a href="javascript:setLang('ua')"><button type="button" class="btn btn-secondary me-2">Українська версія</button></a>
                    </c:when>
                    <c:otherwise>
                        <a href="javascript:setLang('en')"><button type="button" class="btn btn-secondary me-2">English version</button></a>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${sessionScope.user == null}">
                        <button onclick="location.href='${Path.LOGIN_PATH}'"type="button" class="btn btn-success me-2"><fmt:message key="login"/></button>
                        <button onclick="location.href='${Path.SIGNUP_PATH}'" type="button" class="btn btn-warning me-2"><fmt:message key="signup"/></button>
                    </c:when>
                    <c:otherwise>
                        <button onclick="location.href='${Path.MY_BOOKING_PATH}'"type="button" class="btn btn-light me-2"><fmt:message key="myBooking"/></button>
                <button onclick="location.href='${Path.LOGOUT_PATH}'"type="button" class="btn btn-success me-2"><fmt:message key="logout"/></button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</header>

<script type="text/javascript">
    function setLang(lang) {
        document.cookie = "lang=" + lang + "; path=/; max-age=" + (10 * 365 * 24 * 60 * 60);
        location.reload();
    }
</script>

