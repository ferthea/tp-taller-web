<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>ReservAR</title>
    <jsp:include page="_Links.jsp" />
</head>
<body>
<jsp:include page="_Header.jsp" />

<c:if test="${sessionScope.logged != null}">
    TAS LOGEADO PERRO
</c:if>


<div class="container" id ="container">
    <div class="row">
        <div class="col s12 m11 offset-m1">
            <h5 class="center-align"> mis reservas!</h5>
        </div>
    </div>
</div>

<jsp:include page="_Footer.jsp" />
</body>
</html>