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
        <div class="col s10 l8 offset-l2">
            <div class="row">
                <h5 class="center-align">Agregar nuevo menu</h5>
            </div>

            <form:form action="agregar_menu?idRestaurant=${id_restaurant}" method="POST" modelAttribute="menu">
                <div clas="row">
                    <div class="input-field col s12 m6">
                        <form:input path="nombre" id="nombre" type="text" class="form-control" />
                        <label for="nombre">Nombre</label>
                    </div>
                </div>

                <div clas="row">
                    <div class="input-field col s12 m6">
                        <form:input path="precio" id="precio" type="number" class="form-control" />
                        <label for="precio">Precio</label>
                    </div>
                </div>

                <div clas="row">
                    <div class="input-field col s12">
                        <form:input path="descripcion" id="descripcion" type="text" class="form-control" />
                        <label for="descripcion">Descripcion</label>
                    </div>
                </div>

                <div class="row" style="padding-left: 20px;">
                    <div class="input-field col s12">
                        <div class="row">
                            <span class="grey-text">Ingredientes</span>
                        </div>
                        <c:forEach var="ingrediente" items="${ingredientes}">
                            <div class="col s2 m4">
                                <input type="checkbox" id="${ingrediente}" name="c_ingredientes" value="${ingrediente.toString().toLowerCase()}">
                                <label for="${ingrediente}">${ingrediente}</label>
                            </div>
                        </c:forEach>
                    </div>
                </div>

                <div class="top40 row">
                    <div class="input-field col s12 m offset-m4">
                        <button class="btn waves-effect waves-light light-green darken-1" type="submit" name="submit" id="enviar">Agregar
                            <i class="material-icons right">send</i>
                        </button>
                    </div>
                </div>
            </form:form>
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
</div>
</body>
</html>