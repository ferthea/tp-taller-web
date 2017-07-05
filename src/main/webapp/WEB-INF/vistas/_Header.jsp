<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<nav class="light-green darken-1">
	<div class="container">
	    <div class="nav-wrapper">
			<a href="/index" class="brand-logo">ReservAR</a>
		    <ul id="nav-mobile" class="right hide-on-med-and-down">
				<c:if test="${sessionScope.user eq null}">
					<li><a href="/login.html">Login</a></li>
					<li><a href="/registro.html">Registro</a></li>
				</c:if>
				<c:if test="${sessionScope.user != null}">
					<li><a class="dropdown-button" href="#!" data-beloworigin="true" data-activates="usermenu">${sessionScope.user.getNombre()}<i class="material-icons right">arrow_drop_down</i></a></li>
					<ul id="usermenu" class="dropdown-content">
						<li><a href="/misreservas">Reservas</a></li>
						<c:if test="${sessionScope.user.getTipo() == 'restaurant'}">
							<li><a href="/misrestaurantes">Mis restaurantes</a></li>
							<li class="divider"></li>
						</c:if>
						<li class="divider"></li>
						<li><a href="/logout">Salir</a></li>
					</ul>
				</c:if>
			</ul>
		</div>
	</div>
</nav>