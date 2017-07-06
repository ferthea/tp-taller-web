<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
    <jsp:include page="_Links.jsp" />
</head>
<body>
<jsp:include page="_Header.jsp" />
<main>
    <div class="container">
        <div class="row">
            <div class="col s12 m8 offset-m2">
                <div class="row">
                    <c:if test="${not empty errores}">
                        <div>
                            <h3 class="red-text text-darken-4 center-align">ERROR</h3>
                            <div class="row">
                                <c:forEach items="${errores}" var="error">
                                    <div><h5><c:out value="${error}"/></h5></div>
                                </c:forEach>
                            </div>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</main>
<jsp:include page="_Footer.jsp" />
</body>
</html>