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
<div class="container">

    <c:if test="${empty error}">
        <div class="row top40">
            <h5 class="center-align">${restaurant.getNombre()} - Reserva</h5>
        </div>
    </c:if>

    <div class="row top40">

        <div class="col s12 m8 offset-m2">

            <c:if test="${not empty error}">
                <h5>Error!</h5>
                <p>${error}</p>
            </c:if>

            <c:if test="${empty error}">
                <form action="reservar?restaurant_id=${restaurant.getId()}" method="post">
                    <div class="row">
                        <div class="input-field col s12 m6">
                            <span class="grey-text">Dia (mes/dia/año)</span>
                            <input type="datetime-local"  id="fecha" name="fecha">
                            <input type="text" id="nueva_fecha" name="nueva_fecha" hidden>
                            <i style="vertical-align: bottom; color: grey;" class="material-icons">info</i><span> Lugares disponibles: <span id="lugares_disponibles"></span></span>
                        </div>

                        <div class="input-field col s12 m6">
                            <span class="grey-text">Cantidad de comensales</span>
                            <select name="cantidad_comensales" id="cantidad_comensales">
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                                <option value="6">6</option>
                                <option value="7">7</option>
                                <option value="8">8</option>
                                <option value="9">9</option>
                                <option value="10">10</option>
                            </select>
                        </div>
                    </div>

                    <div class="top40 row">
                        <div class="input-field col s12 m offset-m4">
                            <button class="btn waves-effect waves-light light-green darken-1" type="submit" name="submit" id="enviar">Continuar
                                <i class="material-icons right">send</i>
                            </button>
                        </div>
                    </div>
                </form>
            </c:if>

            <div class="row top40">
                <c:if test="${not empty errores}">
                    <div>
                        <h5 class="red-text text-darken-4">Se han producido los siguientes errores:</h5>
                        <c:forEach items="${errores}" var="error">
                            <div><c:out value="${error}"/></div>
                        </c:forEach>
                    </div>
                </c:if>
            </div>

        </div>

    </div>
</div>
<script>
    $(document).ready(function() {
        $('select').material_select();
        const fecha = $("#fecha");
        const nueva_fecha = $("#nueva_fecha");

        fecha.on('focusout', function(){
            const aux = new Date(fecha.val()).getTime();
            setLugaresDisponibles(aux);
            nueva_fecha.val(aux);
        })

        $("#cantidad_comensales").on('change', chequearLugaresDisponibles);


        const aux = new Date(new Date().setHours(new Date().getHours() - 2 )).toISOString().slice(0,16);
        const nuevaFecha = aux;
        console.log(nuevaFecha);
        fecha.val(nuevaFecha);

        nueva_fecha.val(new Date(nuevaFecha).getTime());
        setLugaresDisponibles(new Date(nuevaFecha).getTime());

        function setLugaresDisponibles(fecha){
            const restaurant = ${restaurant.getId()};
            console.log("fecha %s - restaurant: %s", fecha, restaurant);
            $.get( "api/lugaresdisponibles", { restaurant_id: restaurant, fecha: fecha } )
                .done(function( data ) {
                    let lugares_disponibles = data.lugares_disponibles;
                    $("#lugares_disponibles").text(lugares_disponibles);

                    chequearLugaresDisponibles();
                });
        }

        function chequearLugaresDisponibles(){
            let lugares_seleccionados = $("#cantidad_comensales").val();
            let lugares_disponibles = $("#lugares_disponibles").text();

            if(parseInt(lugares_seleccionados) > parseInt(lugares_disponibles)){
                $("#enviar").addClass("disabled");
            }else{
                $("#enviar").removeClass("disabled");
            }
        }
    });
</script>
</body>
</html>