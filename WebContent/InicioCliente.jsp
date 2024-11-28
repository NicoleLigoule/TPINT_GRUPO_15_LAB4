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
String DNI = (String) request.getAttribute("dni");
String CUIL = (String) request.getAttribute("Cuil");
String correo = (String) request.getAttribute("correo");
String telefono = (String) request.getAttribute("telefono");
String direccion = (String) request.getAttribute("direccion");
BigDecimal saldo= (BigDecimal) request.getAttribute("saldo");
String Localidad = (String) request.getAttribute("localidad");
String provincia = (String) request.getAttribute("provincia");
String fechaNac = (String) request.getAttribute("fecha");
String nacionalidad = (String) request.getAttribute("nacionalidad");
%>

		<section class="content">
		<h1 class="main-title">Informacion Personal</h1>
		<div class="info-container">
			<div class="personal-info">
				<h2>Datos del Usuario</h2>
				<p>
					<strong>Nombre:</strong> <%=nombreApellido != null ? nombreApellido : ""%>
				</p>
				<p>
					<strong>DNI:</strong> <%=DNI != null ? DNI : ""%>
				</p>
				<p>
					<strong>CUIL:</strong> <%=CUIL != null ? CUIL : ""%>
				</p>
				<p>
					<strong>Fecha de nacimiento:</strong> <%=fechaNac != null ? fechaNac : "" %>
				</p>
				<p>
					<strong>Nacionalidad:</strong> <%=nacionalidad != null ? nacionalidad : "" %>
				</p>
				<p>
					<strong>Correo:</strong> <%=correo != null ? correo : "" %>
				</p>				
				<p>
					<strong>Telefono:</strong> <%=telefono != null ? telefono : "" %>
				</p>
				<p>
					<strong>Direccion:</strong> <%=direccion != null ? direccion : "" %>
				</p>
				<p>
					 <strong>Localidad:</strong> <%=Localidad != null ? Localidad : "" %> 
				</p>
				<p>
					 <strong>Provincia:</strong> <%=provincia != null ? provincia : "" %> 
				</p>
			</div>
			
			<%
			ArrayList<ClienteCuentaDTO> cuentas = (ArrayList<ClienteCuentaDTO>) request.getAttribute("listaCuentas");
			%>
			
			<div
				style="width: 1px; height: 150px; background-color: #ccc; margin: 0 20px;"></div>
				
		 <div class="cards-container">
    <% 
    boolean hayCuentasValidas = false; // Indicador para tarjetas v�lidas
    
    if (cuentas != null && !cuentas.isEmpty()) {
        for (ClienteCuentaDTO cuenta : cuentas) { 
        	 if (cuenta != null && cuenta.getNumeroCuenta() > 0 && cuenta.getSaldo() != null) {
        		 hayCuentasValidas = true; // Hay al menos una cuenta v�lida
    %>
    <div class="account-info">
        <h2><%= cuenta.getTipoCuenta() %></h2>
        <p><strong>Numero de Cuenta:</strong> <%= cuenta.getNumeroCuenta() %></p>
        <p><strong>Saldo:</strong> $<%= cuenta.getSaldo() %></p>
        
    </div>
    <% 
        	 }
        }
    } 
 	// Mostrar el mensaje solo si no hay cuentas v�lidas
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
