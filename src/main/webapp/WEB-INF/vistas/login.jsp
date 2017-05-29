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
    <div class="col s10 l6 offset-l3">
      <c:if test="${empty registrado}">
        <div class="row">
          <h5 class="center-align">Formulario de registro</h5>
        </div>

        <form:form action="login" method="POST" modelAttribute="user">

          <div class="row">
            <div class="input-field col s12">
              <form:input path="email" id="email" type="email" class="form-control" />
              <label for="email">Email</label>
            </div>
          </div>

          <div class="row">
            <div class="input-field col s12">
              <form:input path="password" id="password" type="password" class="form-control" />
              <label for="password">Password</label>
            </div>
          </div>

          <div class="row">
            <div class="input-field col s12">
              <button class="btn waves-effect waves-light light-green darken-1" type="submit" name="submit">Enviar
                <i class="material-icons right">send</i>
              </button>
            </div>
          </div>

          <div class="row">
            <c:if test="${not empty errores}">
              <div>
                <h5 class="red-text text-darken-4">Se han producido los siguientes errores:</h5>
                <c:forEach items="${errores}" var="error">
                  <div><c:out value="${error}"/></div>
                </c:forEach>
              </div>
            </c:if>
          </div>

        </form:form>
      </c:if>
    </div>
  </div>
</div>
</body>
</html>