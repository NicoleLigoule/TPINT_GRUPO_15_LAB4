<%@page import="java.math.BigDecimal"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="entidades.Usuario"%>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("Login.jsp");
        return;
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Informacion Personal</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Css/VistaIPersonal.css">
<body>
	<nav class="navbar">
	<button class="hamburger" onclick="toggleSidebar()">
		<div class="line"></div>
		<div class="line"></div>
		<div class="line"></div>
	</button>
	<a href="${pageContext.request.contextPath}/Login.jsp"> <img
		src="${pageContext.request.contextPath}/img/png_logo.png"
		class="img_logo" alt="Logo UTN">
	</a> <span class="username"><%= usuario.getUsuarioUs() %></span> </nav>

	<div class="main-container">
		<aside class="sidebar" id="sidebar">
		<ul>
			<li class="menu-item"><a href="#" onclick="toggleSubmenu(event)">Transferencias</a>
			</li>

			<li class="menu-item"><a href="#" onclick="toggleSubmenu(event)">Solicitudes
					de prestamos</a></li>

			<li class="menu-item"><a href="#" onclick="toggleSubmenu(event)">Pago
					de prestamos</a></li>
			<li class="menu-item"><a href="#" onclick="toggleSubmenu(event)">Informacion
					personal</a></li>
		</ul>
		</aside>
		
<%
String nombreApellido = (String) request.getAttribute("nombreApellido");
String correo = (String) request.getAttribute("correo");
String telefono = (String) request.getAttribute("telefono");
String direccion = (String) request.getAttribute("direccion");
BigDecimal saldoCtaCorr= (BigDecimal) request.getAttribute("saldoCtaCorr");
BigDecimal saldoCajaAhorro= (BigDecimal) request.getAttribute("saldoCajaAhorro");
Integer numeroCajaAhorro = (Integer) request.getAttribute("numeroCajaAhorro");
Integer numeroCtaCorriente = (Integer) request.getAttribute("numeroCtaCorriente");
%>

		<section class="content">
		<h1 class="main-title">Información Personal</h1>
		<div class="info-container">
			<div class="personal-info">
				<h2>Datos del Usuario</h2>
				<p>
					<strong>Nombre:</strong> <%=nombreApellido != null ? nombreApellido : ""%>
				</p>
				<p>
					<strong>Correo:</strong> <%=correo != null ? correo : "" %>
				</p>
				<p>
					<strong>Teléfono:</strong> <%=telefono != null ? telefono : "" %>
				</p>
				<p>
					<strong>Dirección:</strong> <%=direccion != null ? direccion : "" %>
				</p>
			</div>
			<div
				style="width: 1px; height: 150px; background-color: #ccc; margin: 0 20px;"></div>
			<div class="account-info">
				<h2>Cuenta Corriente</h2>
				<p>
					<strong>Número de Cuenta:</strong> <%=numeroCtaCorriente != null ? numeroCtaCorriente : "" %>
				</p>
				<p>
					<strong>Saldo:</strong> <%=saldoCtaCorr != null ? saldoCtaCorr : "" %>
				</p>
			</div>

			<div class="account-info">
				<h2>Caja de Ahorro</h2>
				<p>
					<strong>Número de Cuenta:</strong> <%=numeroCajaAhorro != null ? numeroCajaAhorro : "" %>
				</p>
				<p>
					<strong>Saldo:</strong> <%=saldoCajaAhorro != null ? saldoCajaAhorro : "" %>
				</p>
			</div>
		</div>
		</section>
	</div>

	<script src="JS/MenuAdm.js"></script>
</body>
</html>
