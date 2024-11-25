<%@ page import="entidades.ClienteCuentaDTO"%>
<%@ page import="java.util.ArrayList"%>
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
			<jsp:include page="SubMenu_Cliente.jsp" />
<%
String nombreApellido = (String) request.getAttribute("nombreApellido");
String correo = (String) request.getAttribute("correo");
String telefono = (String) request.getAttribute("telefono");
String direccion = (String) request.getAttribute("direccion");
BigDecimal saldo= (BigDecimal) request.getAttribute("saldo");

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
			
			<%
			ArrayList<ClienteCuentaDTO> cuentas = (ArrayList<ClienteCuentaDTO>) request.getAttribute("listaCuentas");
			%>
			
			<div
				style="width: 1px; height: 150px; background-color: #ccc; margin: 0 20px;"></div>
				
		 <div class="cards-container">
    <% 
    boolean hayCuentasValidas = false; // Indicador para tarjetas válidas
    
    if (cuentas != null && !cuentas.isEmpty()) {
        for (ClienteCuentaDTO cuenta : cuentas) { 
        	 if (cuenta != null && cuenta.getNumeroCuenta() > 0 && cuenta.getSaldo() != null) {
        		 hayCuentasValidas = true; // Hay al menos una cuenta válida
    %>
    <div class="account-info">
        <h2><%= cuenta.getTipoCuenta() %></h2>
        <p><strong>Número de Cuenta:</strong> <%= cuenta.getNumeroCuenta() %></p>
        <p><strong>Saldo:</strong> $<%= cuenta.getSaldo() %></p>
        
    </div>
    <% 
        	 }
        }
    } 
 	// Mostrar el mensaje solo si no hay cuentas válidas
    if (!hayCuentasValidas) {  
    %>
    	<p>No hay cuentas disponibles.</p>
    <% } %>
		</div>
				
			
		</div>
		</section>
	</div>

	<script src="JS/MenuAdm.js"></script>
</body>
</html>
