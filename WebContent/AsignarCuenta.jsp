<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="entidades.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidades.Cuenta"%>
<%@page import="entidades.TipoDeCuenta"%>




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
<title>EditarCuenta</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Css/EditarCun.css">
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
	</a> <span class="username"><%=usuario.getUsuarioUs()%></span> </nav>

	<div class="main-container">
		
	<jsp:include page="SubMenu.jsp" />

		<div class="Mastercontainer">

			<div class="content">
				<div class="form-card">
					
					
					<form action="servletAsignarCuenta" method="post">
				        <h2>Formulario de Cuenta</h2>
				        <label for="numeroCuenta">Número de Cuenta:</label>
				        <input type="text" id="numeroCuenta" name="numeroCuenta" placeholder="Ingrese el número de cuenta" required>
				        
				        <label for="cuilCliente">Número de Cliente:</label>
				        <input type="text" id="cuilCliente" name="cuilCliente" placeholder="Ingrese el cuil del Cliente A AGREGAR CUENTA" required>
				        
				        <div class="button-group">
				            <button type="button" >Cancelar</button>
				            <button type="submit">Enviar</button>
				        </div>
				    </form>
					<!-- mensaje de que cargó bien y limpia, sino msj cargó mal -->
					<%
						String status = request.getParameter("status");
						String mensajeExito = "Agregaste la cuenta al cliente correctamente.";
						String mensajeError = (String) request.getAttribute("Error");
					%>
					<%
						if ("success".equals(status)) {
					%>
					<div class="alert alert-success">
						<%=mensajeExito%>
					</div>
					<%
						}
					%>

					<%
						if (mensajeError != null) {
					%>
					<div class="alert alert-danger">
						<%=mensajeError%>
					</div>
					<%
						}
					%>
					
				</div>
			</div>
		</div>
	</div>

	<script src="JS/MenuAdm.js"></script>
</body>
</html>