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
        <div class="col s12 offset-m1">
            <h5 class="center-align"> Mis restaurantes</h5>
            <a href="/nuevorestaurant" class="waves-effect waves-light btn light-green lighten-1"><i class="material-icons right">add</i>Añadir</a>
        </div>
    </div>

    <style>
        div.card-content > p > i, span {
            display: inline-flex;
            vertical-align: middle;
        }
    </style>

    <div class="row">
        <c:if test="${not empty restaurants}">
                <c:forEach items="${restaurants}" var="restaurant">
                    <div class="col s12 m5 offset-m1">
                        <div class="card">
                            <div class="card-image">
                                <a href="/restaurants/${restaurant.getId()}"}><img src="images/restaurant.jpg">
                                    <span class="card-title">${restaurant.getNombre()}</span></a>
                            </div>
                            <div class="card-content">
                                <p><i class="material-icons">keyboard_arrow_right</i><span>${restaurant.getTipo()}</span></p>
                                <p><i class="material-icons">location_on</i><span>${restaurant.getDireccion()}</span></p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
        </c:if>
    </div>
    <c:if test="${empty restaurants}">
        <strong>Oops! No tienes ningun restaurant cargado.</strong>
    </c:if>
</div>

<jsp:include page="_Footer.jsp" />
</body>
</html>