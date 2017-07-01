<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
        <h5 class="center-align">A�adir nuevo restaurant</h5>
    </div>
    <div class="row">
        <div class="col s12 m8 offset-m2">
            <form:form action="nuevorestaurant" modelAttribute="restaurant" method="POST" enctype="multipart/form-data">
                <div class="row">
                    <div class="input-field">
                        <form:input path="nombre" id="nombre" type="text" class="form-control" />
                        <label for="nombre">Nombre</label>
                    </div>
                </div>

                <div class="row">
                    <div class="input-field">
                        <form:select path="tipo"  class="browser-default">
                            <form:option value="" disabled="true" selected="true">Categor�a</form:option>
                            <form:options items="${tiposDeRestaurant}" />
                        </form:select>
                    </div>
                </div>

                <div class="row">
                    <div class="input-field">
                        <form:input path="direccion" id="direccion" type="text" class="form-control"/>
                        <label for="direccion">Direccion</label>
                    </div>
                </div>

                <div class="row">
                    <div class="input-field">
                        <form:input path="maximaCantidadDeClientes" id="capacidadMaxima" type="number" class="form-control"/>
                        <label for="capacidadMaxima">Capacidad m�xima</label>
                    </div>
                </div>

                <div class="row">
                    <div class="file-field input-field">
                        <div class="btn light-green darken-1">
                            <span>Foto</span>
                            <input type="file" id="foto_restaurant" name="foto_restaurant">
                        </div>
                        <div class="file-path-wrapper">
                            <input class="file-path validate" type="text">
                        </div>
                    </div>
                </div>

                <div class="row">
                    <button class="btn waves-effect waves-light light-green darken-1" type="submit" name="submit">Enviar
                        <i class="material-icons right">send</i>
                    </button>
                </div>

            </form:form>
        </div>
    </div>
    <div class="row">
        <div class="col s12 m8 offset-m2">
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

<jsp:include page="_Footer.jsp" />
</body>
</html>