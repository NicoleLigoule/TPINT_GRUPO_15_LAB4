<%@page import="entidades.Nacionalidad"%>
<%@page import="entidades.Sexo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="daoImpl.LocalidadDaoImpl"%>
<%@page import="daoImpl.ProvinciaDaoImpl"%>
<%@page import="entidades.Provincia"%>
<%@page import="entidades.Localidad"%>
<%@ page import="entidades.Usuario"%>
<%@ page import="entidades.Cliente"%>
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
<title>Editar Cliente</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Css/AgregarCli.css">
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
				<h2>Editar cliente </h2>
				<%Cliente cliSeleccionado = (Cliente)request.getAttribute("clienteSeleccionado"); %>
				<!-- Primer formulario (GET) GUARDAMOS HASTA PROVINCIA -->
				<form action="servletEditarCliente" method="POST">
					<label for="dni">DNI</label> <input type="text" id="dni" name="dni"
						placeholder="DNI"
						value="<%=Integer.toString(cliSeleccionado.getDni()) != null ? Integer.toString(cliSeleccionado.getDni()) : ""%>"
						required> <input
						type="hidden" id="cuil" name="cuil" placeholder="CUIL"
						value="<%=cliSeleccionado.getCuil() != null ? cliSeleccionado.getCuil() : ""%>"
						required> <label for="nombre">Nombre</label> <input
						type="text" id="nombre" name="nombre" placeholder="Nombre"
						value="<%=cliSeleccionado.getNombre() != null ? cliSeleccionado.getNombre() : ""%>"
						required> <label for="apellido">Apellido</label> <input
						type="text" id="apellido" name="apellido" placeholder="Apellido"
						value="<%=cliSeleccionado.getApellido() != null ? cliSeleccionado.getApellido() : ""%>"
						required>

					<%
						ArrayList<Sexo> listaSexo = (ArrayList<Sexo>) request.getAttribute("listaSexo");
					%>
					<label for="genero">Genero:</label> <select id="genero"
						name="genero" required>
						<option value="" disabled selected>Seleccione su genero</option>
						<%
							if (listaSexo != null) {
								for (Sexo sex : listaSexo) {
						%>
						<option value="<%=sex.getId_sexo()%>"
							<%=sex.getId_sexo() == cliSeleccionado.getId_sexo() ? "selected" : ""%>><%=sex.getDescripcion()%></option>
						<%
							}
							}
						%>
					</select>

					<%
						ArrayList<Nacionalidad> listaNacionalidad = (ArrayList<Nacionalidad>) request.getAttribute("listaNacionalidad");
					%>
					<label for="nacionalidad">Nacionalidad</label> <select
						id="nacionalidad" name="nacionalidad" required>
						<option value="" disabled selected>Seleccione su
							Nacionalidad</option>
						<%
							if (listaNacionalidad != null) {
								for (Nacionalidad nac : listaNacionalidad) {
						%>
						<option value="<%=nac.getIdNacionalidadNc()%>"
							<%=nac.getIdNacionalidadNc().equals(cliSeleccionado.getId_nacionalidad()) ? "selected" : ""%>><%=nac.getDescripcionNc()%></option>
						<%
							}
							}
						%>
					</select> <label for="fecha-nacimiento">Fecha de Nacimiento</label> <input
						type="text" id="fecha-nacimiento" name="fecha-nacimiento"
						placeholder="YYYY/MM/DD"
						value="<%=cliSeleccionado.getFechaNacimiento() != null ? cliSeleccionado.getFechaNacimiento() : ""%>"
						required> <label for="direccion">Direccion</label> <input
						type="text" id="direccion" name="direccion"
						placeholder="Direccion"
						value="<%=cliSeleccionado.getDireccion() != null ? cliSeleccionado.getDireccion() : ""%>"
						required> <label for="telefono">Telefono</label> <input
						type="tel" id="telefono" name="telefono" placeholder="Telefono"
						value="<%=cliSeleccionado.getTelefono() != null ? cliSeleccionado.getTelefono() : ""%>"
						required> <label for="email">Correo Electronico</label> <input
						type="email" id="email" name="email"
						placeholder="Correo Electronico"
						value="<%=cliSeleccionado.getCorreo() != null ? cliSeleccionado.getCorreo() : ""%>"
						required> <label for="provincia">Provincia</label> <select
						id="provincia" name="provincia" onchange="this.form.submit();"
						required>
						<option value="" disabled selected>Seleccione su
							Provincia</option>
						<%
							ArrayList<Provincia> listaProvincias = (ArrayList<Provincia>) request.getAttribute("listaProvincias");
						
							if (listaProvincias != null && !listaProvincias.isEmpty()) {
								for (Provincia prov : listaProvincias) {
						%>
						<option value="<%=prov.getId_provincia()%>"
							<%=prov.getId_provincia() == cliSeleccionado.getId_provincia() ? "selected" : ""%>><%=prov.getNombre()%></option>
						<%
							}
							}
						%>
					</select> 
					
					<label for="localidad">Localidad</label> <select
						id="localidad" name="localidad"
						required>
						<option value="" disabled selected>Seleccione su Localidad</option>
						<%
							ArrayList<Localidad> listaLocalidades = (ArrayList<Localidad>) request.getAttribute("listaLocalidad");
							
							if (listaLocalidades != null && !listaLocalidades.isEmpty()) {
								for (Localidad loc : listaLocalidades) {
						%>
						<option value="<%=loc.getId_localidad()%>"
							<%=loc.getId_localidad() == cliSeleccionado.getId_localidad() ? "selected" : ""%>><%=loc.getNombreLoca()%></option>
						<%
							}
							}
						%>
					</select> 
					
					<!-- boton agregar, aca enviamos todo con el "agregarBtn" -->
					<div class="button-group">
						<button type="button" class="cancel-button">Cancelar</button>
						<button type="submit" class="submit-button" name="agregarBtn">Agregar</button>
					</div>

					<!-- mensaje de que carg� bien y limpia, sino msj carg� mal -->
					<%
						String status = request.getParameter("status");
						String mensajeExito = "Se ha generado el Cliente correctamente.";
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
