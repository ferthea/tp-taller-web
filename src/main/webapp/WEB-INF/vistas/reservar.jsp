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
    <div class="row">
        <h5>Reservar!</h5>
    </div>
    <div class="row">
        <form:form action="reservar" method="POST" modelAttribute="reserva">

            <div class="row">
                <div class="input-field col s12">
                    <input type="date" id="fecha" name="fecha" class="form-control">
                </div>
            </div>

            <div class="row">
                <input type="number" name="id_restaurant">
            </div>

            <div class="row">
                <div class="input-field col s12">
                    <form:input path="cantidadComensales" id="cantidad_comensales" type="number" class="form-control" />
                    <label for="cantidad_comensales">Comensales</label>
                </div>
            </div>

            <div class="row">
                <div class="input-field col s12">
                    <form:input path="observaciones" id="observaciones" type="text" class="form-control" />
                    <label for="observaciones">Observaciones</label>
                </div>
            </div>

            <div class="row">
                <div class="input-field col s12">
                    <button class="btn waves-effect waves-light light-green darken-1" type="submit" name="submit">Enviar
                        <i class="material-icons right">send</i>
                    </button>
                </div>
            </div>

        </form:form>
    </div>
</div>
</body>
</html>