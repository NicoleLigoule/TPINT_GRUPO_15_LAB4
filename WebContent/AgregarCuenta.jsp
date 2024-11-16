<%@page import="entidades.TipoDeCuenta"%>
<%@page import="java.util.ArrayList"%>

<%@ page import="entidades.Usuario"%>
<%@ page import="entidades.Cuenta"%>

<%
	Usuario usuario = (Usuario) session.getAttribute("usuario");
	if (usuario == null) {
		response.sendRedirect("Login.jsp");
		return;
	}
%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Alta de Cuentas</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Css/AgregarCun.css">
</head>
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
		</a> <span class="username"><%=usuario.getUsuarioUs()%></span>
	</nav>
	<div class="main-container">
		<jsp:include page="SubMenu.jsp" />

		<div class="content">
			<div class="form-card">
				<h2>Agregar Cuenta</h2>
				<br>
				<h3>Datos del socio</h3>
				<form action="servletAgregarCuenta" method="post">
					<!-- <label for="dni">DNI Cliente</label>
                    <input type="text" id="dni" name="dni" placeholder="DNI" required> -->


					<label for="cuil">CUIL Cliente</label> <input type="text" id="cuil"
						name="cuil" placeholder="CUIL"> <br>
					<%
						ArrayList<TipoDeCuenta> listaSeguros = null;
						if (request.getAttribute("listaTCuentas") != null) {
							listaSeguros = (ArrayList<TipoDeCuenta>) request.getAttribute("listaTCuentas");
						}
					%>
					<h3>Datos Bancarios</h3>

					<!-- Número de Cuenta -->


					<!-- Tipo de Cuenta Dropdown -->
					<label for="tipoDeCuenta">Tipo de Cuenta</label> <select
						id="tipoDeCuenta" name="tipoCuenta" required>
						<option value="" disabled selected>Seleccione un tipo de
							cuenta</option>
						<%
							if (listaSeguros != null) {
								for (TipoDeCuenta cuenta : listaSeguros) {
						%>
						<option value="<%=cuenta.getIdTipoDeCuenta()%>"><%=cuenta.getNombreTipo()%></option>
						<%
							}
							}
						%>
					</select>

					<!-- Saldo -->
					<label for="saldo">Saldo Inicial</label> <input type="text"
						id="saldo" name="saldo" placeholder="Ingrese el saldo inicial"
						required>

					<!-- Fecha de Apertura -->
					<label for="fechaApertura">Fecha de Apertura</label> <input
						type="date" id="fechaApertura" name="fechaApertura" required>

					<!-- Estado de la Cuenta -->
					<!--  <label for="estado">Estado de la Cuenta</label> <select id="estado"
						name="estado" required>
						<option value="" disabled selected>Seleccione el estado
							de la cuenta</option>
						<option value="Activa">Activa</option>
						<option value="Inactiva">Inactiva</option>
					</select>-->
					<!-- Estado de la Cuenta -->
					
					<%
					    Cuenta cuenta = (Cuenta) request.getAttribute("cuenta");
					    if (cuenta == null) {
					        cuenta = new Cuenta();
					    }
					%>
					<div style="text-align: left; margin-bottom: 10px;">
				    <label for="estado" style="font-size: 18px;">CUENTA ACTIVA</label>
				    <input type="checkbox" id="estado" name="estado" style="transform: scale(1.5); margin-left: 10px;" <%= cuenta.isEstadoCu() ? "checked" : "" %>>
				</div>

					<div class="button-group">
						<button type="button" class="cancel-button"
							onclick="window.history.back()">Volver</button>
						<button type="submit" class="submit-button" name="agregarBtn">Agregar</button>
					</div>
					<!-- mensaje de que cargó bien y limpia, sino msj cargó mal -->
					<%
						String status = request.getParameter("status");
						String mensajeExito = "Se ha generado la cuenta correctamente.";
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
				</form>
			</div>
		</div>
	</div>

	<script src="JS/MenuAdm.js"></script>
</body>
</html>
