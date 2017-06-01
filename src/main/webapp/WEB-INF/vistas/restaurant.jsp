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

<div class="container" id ="container">
  <h5>hola!</h5>
  <c:if test"${not empty error}">
    <h2>${error}</h2>
  </c:if>
  <c:if test="${not empty restaurant}">
    <h5>${restaurant.getNombre()}</h5>
  </c:if>
</div>

<jsp:include page="_Footer.jsp" />
</body>
</html>