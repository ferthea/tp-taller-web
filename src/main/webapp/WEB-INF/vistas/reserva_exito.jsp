<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <div class="col s12 m8 offset-m2">
            <div class="row">
                <h3>Reserva registrada!</h3>
                <p>
                    <b>${reserva.getRestaurant().getNombre()}</b> te espera el
                    <fmt:formatDate value="${reserva.getFecha()}" pattern="yyyy-MM-dd HH:mm"/>
                </p>
                <p>Que lo disfrutes!</p>
            </div>
            <div class="row">
                <a href="/misreservas" class="btn waves-effect waves-light light-green darken-1">Mis reservas
                    <i class="material-icons right">send</i>
                </a>
            </div>
        </div>
    </div>
</div>
</body>
</html>