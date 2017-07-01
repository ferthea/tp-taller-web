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
      <div class="row">
        <c:if test="${error != null}">
          <h5 class="center-align">No se han encontrado resultados.</h5>
        </c:if>
        <c:if test="${error == null}">
          <h5 class="center-align">Resultados de la búsqueda</h5>
        </c:if>
      </div>
      <div class="row">
        <c:forEach items="${restaurants}" var="restaurant">
          <div class="col s12 m5 offset-m1">
            <div class="card">
              <div class="card-image" style="background-image: url('/images/restaurants/${restaurant.getFoto()}'); background-size: cover;">
                <a href="/restaurants/${restaurant.getId()}"}>
                  <span class="card-title">${restaurant.getNombre()}</span></a>
              </div>
              <div class="card-content">
                <p><i class="material-icons">location_on</i> ${restaurant.getDireccion()}</p>
              </div>
            </div>
          </div>
        </c:forEach>
      </div>
    </div>
</div>

<jsp:include page="_Footer.jsp" />
</body>
</html>