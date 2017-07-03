<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <div class="col s12 m8 offset-m2">
            <div class="row">
                <h5 class="center-align">Confirma tu reserva</h5>
            </div>

            <div class="row">
                <div class="col s12">
                    <div class="col s4 center-align">
                        <p>
                            <b>Restaurant: </b> ${reserva.getRestaurant().getNombre()}
                        </p>
                    </div>
                    <div class="col s4 center-align">
                        <p>
                            <b>Fecha: </b> <fmt:formatDate value="${reserva.getFecha()}" pattern="yyyy-MM-dd HH:mm" />
                        </p>
                    </div>
                    <div class="col s4 center-align">
                        <p>
                            <b>Comensales: </b> ${reserva.getCantidadComensales()}
                        </p>
                    </div>
                </div>
            </div>

            <div class="row">
                <form action="crear_reserva" method="post">
                    <c:if test="${not empty reserva.getPedidos()}">
                        <div class="card-panel">
                            <div class="row">
                                <c:forEach var="pedido" items="${reserva.getPedidos()}">
                                    <div class="col m6">
                                        ${pedido.menu.nombre} (x${pedido.cantidad})
                                    </div>
                                    <div class="col m6 right right-align">
                                        ${pedido.menu.precio * pedido.cantidad}
                                    </div>
                                </c:forEach>
                            </div>
                            <div class="row divider"></div>
                            <div class="row">
                                <div class="col m6">
                                    Total
                                </div>
                                <div class="col m6 right right-align">
                                    ${total}
                                </div>
                            </div>
                        </div>
                    </c:if>
                    <div class="row">
                        <div class="input-field col s12 m4 offset-m4">
                            <button class="btn waves-effect waves-light light-green darken-1" type="submit" name="submit">Reservar
                                <i class="material-icons right">send</i>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script type="application/javascript" src="/js/restaurant.js"></script>
<jsp:include page="_Footer.jsp" />
</body>
</html>