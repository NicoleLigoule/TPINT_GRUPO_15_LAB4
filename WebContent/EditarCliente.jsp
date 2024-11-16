<%@page import="entidades.Nacionalidad"%>
<%@page import="entidades.Sexo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="daoImpl.LocalidadDaoImpl"%>
<%@page import="daoImpl.ProvinciaDaoImpl"%>
<%@page import="entidades.Provincia"%>
<%@page import="entidades.Localidad"%>
<%@ page import="entidades.Usuario"%>
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
		<aside id="sidebar" class="sidebar">
			<ul>
				<li class="menu-item"><a href="#"
					onclick="toggleSubmenu(event)">Clientes</a>
					<ul class="submenu">
						<li><a href="AgregarCliente.jsp">Agregar Cliente</a></li>
						<li><a href="EliminarCliente.jsp">Baja Cliente</a></li>
						<li><a href="#">Editar Cliente</a></li>
						<li><a href="ListarCliente.jsp">Listar Cliente</a></li>
					</ul></li>
				<li class="menu-item"><a href="#"
					onclick="toggleSubmenu(event)">Cuentas</a>
					<ul class="submenu">
						<li><a href="AgregarCuenta.jsp">Agregar Cuenta</a></li>
						<li><a href="#">Baja Cuenta</a></li>
						<li><a href="#">Editar Cuenta</a></li>
						<li><a href="#">Listar Cuenta</a></li>
					</ul></li>
				<li class="menu-item"><a href="#"
					onclick="toggleSubmenu(event)">Transacciones</a></li>
				<li class="menu-item"><a href="#"
					onclick="toggleSubmenu(event)">Reportes</a></li>
			</ul>
		</aside>

		<div class="content">
			<div class="form-card">
				<h2>Editar cliente </h2>

				<!-- Primer formulario (GET) GUARDAMOS HASTA PROVINCIA -->
				<form action="servletEditarCliente" method="GET">
					<label for="dni">DNI</label> <input type="text" id="dni" name="dni"
						placeholder="DNI"
						value="<%=request.getAttribute("dni") != null ? request.getAttribute("dni") : ""%>"
						required> <label for="cuil">CUIL</label> <input
						type="text" id="cuil" name="cuil" placeholder="CUIL"
						value="<%=request.getAttribute("cuil") != null ? request.getAttribute("cuil") : ""%>"
						required> <label for="nombre">Nombre</label> <input
						type="text" id="nombre" name="nombre" placeholder="Nombre"
						value="<%=request.getAttribute("nombre") != null ? request.getAttribute("nombre") : ""%>"
						required> <label for="apellido">Apellido</label> <input
						type="text" id="apellido" name="apellido" placeholder="Apellido"
						value="<%=request.getAttribute("apellido") != null ? request.getAttribute("apellido") : ""%>"
						required>

					<%
						ArrayList<Sexo> listaSexo = (ArrayList<Sexo>) request.getAttribute("listaSexo");
					%>
					<label for="genero">Género:</label> <select id="genero"
						name="genero" required>
						<option value="" disabled selected>Seleccione su género</option>
						<%
							if (listaSexo != null) {
								for (Sexo sex : listaSexo) {
						%>
						<option value="<%=sex.getId_sexo()%>"
							<%=sex.getId_sexo()==(int)request.getAttribute("genero") ? "selected"
							: ""%>><%=sex.getDescripcion()%></option>
						<%
							}
							}
						%>
					</select>

					<%
						ArrayList<Nacionalidad> listaSeguros = (ArrayList<Nacionalidad>) request.getAttribute("listaNacionalidad");
					%>
					<label for="nacionalidad">Nacionalidad</label> <select
						id="nacionalidad" name="nacionalidad" required>
						<option value="" disabled selected>Seleccione su
							Nacionalidad</option>
						<%
							if (listaSeguros != null) {
								for (Nacionalidad nac : listaSeguros) {
						%>
						<option value="<%=nac.getIdNacionalidadNc()%>"
			     	<%=request.getAttribute("nacionalidad") != null && nac.getIdNacionalidadNc().equals(request.getAttribute("nacionalidad")) ? "selected" : ""%>><%=nac.getDescripcionNc()%></option>
						<%
							}
							}
						%>
					</select> <label for="fecha-nacimiento">Fecha de Nacimiento</label> <input
						type="text" id="fecha-nacimiento" name="fecha-nacimiento"
						placeholder="YYYY/MM/DD"
						value="<%=request.getAttribute("fecha-nacimiento") != null ? request.getAttribute("fecha-nacimiento") : ""%>"
						required> <label for="direccion">Dirección</label> <input
						type="text" id="direccion" name="direccion"
						placeholder="Dirección"
						value="<%=request.getAttribute("direccion") != null ? request.getAttribute("direccion") : ""%>"

						value="<%=request.getAttribute("numero") != null ? request.getAttribute("numero") : ""%>"
						required> <label for="telefono">Teléfono</label> <input
						type="tel" id="telefono" name="telefono" placeholder="Teléfono"
						value="<%=request.getAttribute("telefono") != null ? request.getAttribute("telefono") : ""%>"
						required> <label for="email">Correo Electrónico</label> <input
						type="email" id="email" name="email"
						placeholder="Correo Electrónico"
						value="<%=request.getAttribute("email") != null ? request.getAttribute("email") : ""%>"
						required> <label for="provincia">Provincia</label> <select
						id="provincia" name="provincia" "
						required>
						<option value="" disabled selected>Seleccione su
							Provincia</option>
						<%
							ArrayList<Provincia> listaProvincias = (ArrayList<Provincia>) request.getAttribute("listaProvincias");
								int selectedPRov = (int) request.getAttribute("idProvincia");
							if (listaProvincias != null) {
								for (Provincia prov : listaProvincias) {
						%>
						<option value="<%=prov.getId_provincia()%>"
							<%=prov.getId_provincia() == selectedPRov ? "selected" : ""%>><%=prov.getNombre()%></option>
						<%
							}
							}
						%>
						<input
						type="hidden" name="localidad"
						value="<%=request.getAttribute("localidad")%>">
						
					<div class="button-group">
						<button type="submit" class="submit-button" name="cambiarBTn">cambiar</button>
					</div>
				</form>

				<!-- Segundo formulario (POST) USAMOS LO QUE GUARDAMOS ANTES y le sumamos la localidad q cambia segun la provincia elegida -->
				<form action="servletEditarCliente" method="POST">
					<input type="hidden" name="dni"
						value="<%=request.getAttribute("dni")%>"> <input
						type="hidden" name="cuil"
						value="<%=request.getAttribute("cuil")%>"> <input
						type="hidden" name="nombre"
						value="<%=request.getAttribute("nombre")%>"> <input
						type="hidden" name="apellido"
						value="<%=request.getAttribute("apellido")%>"> <input
						type="hidden" name="genero"
						value="<%=request.getAttribute("genero")%>"> <input
						type="hidden" name="nacionalidad"
						value="<%=request.getAttribute("nacionalidad")%>"> <input
						type="hidden" name="fecha-nacimiento"
						value="<%=request.getAttribute("fecha-nacimiento")%>"> <input
						type="hidden" name="direccion"
						value="<%=request.getAttribute("direccion")%>"> <input
						type="hidden" name="numero"
						value="<%=request.getAttribute("numero")%>"> <input
						type="hidden" name="telefono"
						value="<%=request.getAttribute("telefono")%>"> <input
						type="hidden" name="email"
						value="<%=request.getAttribute("email")%>"> <input
						type="hidden" name="provincia"
						value="<%=request.getAttribute("idProvincia")%>"> <label
						for="localidad">Localidad</label> <select id="localidad"
						name="localidad" required>
						<option value="" disabled selected>Seleccione su
							Localidad</option>
						<%
							ArrayList<Localidad> listaLocalidad = (ArrayList<Localidad>) request.getAttribute("listaLocalidad");
								int selectedLocalidad = (int) request.getAttribute("localidad");
							if (listaLocalidad != null) {
								for (Localidad loc : listaLocalidad) {
						%>
						<option value="<%=loc.getId_localidad()%>"
							<%=loc.getId_localidad() == selectedLocalidad ? "selected" : ""%>><%=loc.getNombreLoca()%></option>
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

					<!-- mensaje de que cargó bien y limpia, sino msj cargó mal -->
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
