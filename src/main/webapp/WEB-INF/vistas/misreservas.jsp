<%@ page import="ar.edu.unlam.tallerweb1.modelo.Menu" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>ReservAR</title>
    <jsp:include page="_Links.jsp" />
</head>
<body>
<jsp:include page="_Header.jsp" />
<main>
    <div class="container">
        <div class="row">
            <div clas="col s12 m8 offset-m2">

                <c:if test="${empty reservas}">
                    <h5 class="center-align">Al parecer no tienes reservas</h5>
                </c:if>

                <c:if test="${not empty reservas}">
                    <h5 style="margin-top: 40px; margin-bottom: 40px;">Reservas al día de la fecha</h5>
                    <input type="text" hidden value="${cantidad}" />
                    <c:forEach var="reserva" items="${reservas}">
                        <div class="card-panel">
                            <div class="row">
                                <div class="col m4 left-align">
                                    <i class="material-icons tooltipped" data-position="bottom" data-delay="50" data-tooltip="Lugar">place</i>
                                    <span style="vertical-align: super">${reserva.restaurant.nombre}</span>
                                </div>
                                <div class="col m4 center-align">
                                    <i class="material-icons tooltipped" data-position="bottom" data-delay="50" data-tooltip="Cantidad comensales">people</i>
                                    <span style="vertical-align: super">${reserva.cantidadComensales}</span>
                                </div>
                                <div class="col m4 right-align">
                                    <i class="material-icons tooltipped" data-position="bottom" data-delay="50" data-tooltip="fecha">date_range</i>
                                    <span style="vertical-align: super"><fmt:formatDate value="${reserva.getFecha()}" pattern="yyyy-MM-dd HH:mm"/></span>
                                </div>

                                <div style="padding-top: 70px;">
                                    <c:forEach var="pedido" items="${reserva.getPedidos()}">
                                        <div class="col m6">
                                                ${pedido.menu.nombre} (x${pedido.cantidad})
                                        </div>
                                        <div class="col m6 right right-align">
                                                ${pedido.menu.precio * pedido.cantidad}
                                        </div>
                                    </c:forEach>
                                </div>

                            </div>
                            <div class="row divider"></div>
                            <div class="row">
                                <div class="col m6">
                                    Total
                                </div>
                                <div class="col m6 right right-align">
                                    <c:set var="total" value="${0}"/>
                                    <c:forEach var="pedido" items="${reserva.getPedidos()}">
                                        <c:set var="total" value="${total + pedido.menu.precio}" />
                                    </c:forEach>
                                    $ ${total}
                                </div>
                            </div>
                            <c:if test="${user.getTipo().equals('cliente') and reserva.fecha > now}">
                                <div class="row" style="margin-bottom: 0px; margin-left: 0px;">
                                    <a href="#modal_borrar" data-reserva="${reserva.id}" class="waves-effect waves-light btn red darken-4 link_borrar">
                                        <i class="material-icons right">close</i>Cancelar
                                    </a>
                                </div>
                            </c:if>
                            <c:if test="${user.getTipo().equals('cliente') and reserva.fecha < now}">
                                <div class="row" style="margin-bottom: 0px; margin-left: 0px;">
                                    <a href="#" class="waves-effect waves-light btn green lighten-2">
                                        <i class="material-icons right">done</i>Finalizada
                                    </a>
                                </div>
                            </c:if>
                        </div>
                    </c:forEach>

                    <c:if test="${user.getTipo().equals('cliente')}">
                        <div id="modal_borrar" class="modal">
                            <div class="modal-content">
                                <h4>Estas seguro?</h4>
                                <p>Si aceptas no lo podrás deshacer.</p>
                            </div>
                            <div class="modal-footer">
                                <a id="modal_confirm" class="modal-action modal-close waves-effect waves-green btn-flat">Estoy seguro</a>
                            </div>
                        </div>
                    </c:if>

                    <div class="row center-align" style="margin-top: 30px;">
                        <ul class="pagination">
                            <li class="disabled"><a href="#!"><i class="material-icons">chevron_left</i></a></li>
                            <li class="waves-effect"><a href="?page=1">1</a></li>
                            <li class="waves-effect"><a href="?page=2">2</a></li>
                            <li class="waves-effect"><a href="?page=2"><i class="material-icons">chevron_right</i></a></li>
                        </ul>
                    </div>
                </c:if>

                <c:if test="${not empty restaurants}">
                    <h5>Selecciona el restaurant del que quieres ver las reservas</h5>
                    <c:forEach var="restaurant" items="${restaurants}">
                        <a style="font-size: 1.3em" href="?restaurant=${restaurant.id}">${restaurant.nombre}</a>
                    </c:forEach>
                </c:if>

            </div>
        </div>
    </div>
</main>
<jsp:include page="_Footer.jsp" />
<script>

    $(document).ready(function(){
        $('.modal').modal();
        let modal = $("#modal_confirm");

        if(modal){
            let btn_borrar = $(".link_borrar");
            $(btn_borrar).click(function(e){
                let id_reserva = $(this).data("reserva");
                $("#modal_confirm").attr("href", "/cancelar_reserva?reserva=" + id_reserva);
            })
        }

        if(getUrlParameter("restaurant")){
            $("ul.pagination > li > a").each(function(e){
                var original_value = $(this).attr("href");
                var new_value = original_value+"&restaurant="+getUrlParameter("restaurant");
                $(this).attr("href", new_value);
            })

        }
    });

    var getUrlParameter = function getUrlParameter(sParam) {
        var sPageURL = decodeURIComponent(window.location.search.substring(1)),
            sURLVariables = sPageURL.split('&'),
            sParameterName,
            i;

        for (i = 0; i < sURLVariables.length; i++) {
            sParameterName = sURLVariables[i].split('=');

            if (sParameterName[0] === sParam) {
                return sParameterName[1] === undefined ? true : sParameterName[1];
            }
        }
    };


</script>
</body>
</html>