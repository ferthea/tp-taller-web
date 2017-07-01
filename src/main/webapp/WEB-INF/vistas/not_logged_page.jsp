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
        <div class="col s12 m8 offset-m2">
            <div class="row">
                <h3>Oops!</h3>
                <h5>Debes estar registrado para realizar esta acción.</h5>
                <p>
                    Para crear una cuenta, haz click <a href="/registro">aquí</a>
                </p>
                <p>Ya tienes una cuenta? <a href="/login">Iniciar sesión</a></p>
            </div>
        </div>
    </div>
</div>
</body>
</html>