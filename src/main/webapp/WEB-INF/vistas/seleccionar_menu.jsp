<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <div class="row top40">
        <h5 class="center-align"> ${restaurant.getNombre()} - Reserva</h5>
    </div>

    <div class="row">
        <form:form action="/seleccionar_menu" method="post" modelAttribute="pedidoListWrapper">
            <c:forEach varStatus="i" items="${pedidoListWrapper.pedidosList}" var="pedido">
                <!--<div class="row">
                    <div class="col m4">
                        <b>Nombre: </b> ${pedido.getMenu().getNombre()}
                    </div>
                    <div class="col m4">
                        <b>Cantidad: </b> ${pedido.cantidad}
                        <form:input path="pedidosList[${i.index}].cantidad" type="text"/>
                    </div>
                </div>!-->

                <div class="card menu" data-ingredientes="${String.join(',', pedido.getMenu().getIngredientes())}">
                    <div class="card-content" >
                        <span class="card-title">${pedido.getMenu().getNombre()}</span>
                        <span class="menu-precio" style="float:right">$${pedido.getMenu().getPrecio()}</span>
                        <p>${pedido.getMenu().getDescripcion()}</p>
                    </div>
                    <div class="card-action">
                        <c:forEach items="${pedido.getMenu().getIngredientes()}" var="ingrediente">
                            <div class="chip">${ingrediente}</div>
                        </c:forEach>
                        <div class="right"><form:input path="pedidosList[${i.index}].cantidad" type="number"/></div>
                        <div class="right" style="margin-right: 20px; padding-top: 10px;">
                            <span>Cantidad</span>
                        </div>
                    </div>
                </div>

            </c:forEach>
            <button>enviar</button>
        </form:form>
    </div>
</div>
</div>

<jsp:include page="_Footer.jsp" />
</body>
</html>