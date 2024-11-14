<%@page import="entidades.Nacionalidad"%>
<%@page import="entidades.Cliente"%>
<%@page import="entidades.Sexo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="daoImpl.LocalidadDaoImpl"%>
<%@page import="daoImpl.ProvinciaDaoImpl"%>
<%@page import="entidades.Provincia"%>
<%@page import="entidades.Localidad"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>EditarCliente</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Css/EditarCli.css">
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
	</a> <span class="username">USUARIO XXXX</span> </nav>

	<div class="main-container">
		<aside class="sidebar" id="sidebar">
		<ul>
			<li class="menu-item"><a href="#" onclick="toggleSubmenu(event)">Clientes</a>
				<ul class="submenu">
					<li><a href="AgregarCliente.jsp">Agregar Cliente</a></li>
					<li><a href="EliminarCliente.jsp">Baja Cliente</a></li>
					<li><a href="#">Editar Cliente</a></li>
					<li><a href="ListarCliente.jsp">Listar Cliente</a></li>
				</ul></li>
			<li class="menu-item"><a href="#" onclick="toggleSubmenu(event)">Cuentas</a>
				<ul class="submenu">
					<li><a href="AgregarCuenta.jsp">Agregar Cuenta</a></li>
					<li><a href="#">Baja Cuenta</a></li>
					<li><a href="#">Editar Cuenta</a></li>
					<li><a href="#">Listar Cuenta</a></li>
				</ul></li>
			<li class="menu-item"><a href="#" onclick="toggleSubmenu(event)">Transacciones</a>
				<!-- <ul class="submenu">
                        <li> <a href="#">Registrar Transacción</a></li>
                        <li> <a href="#">Ver Historial</a></li>
                    </ul>
                     --></li>
			<li class="menu-item"><a href="#" onclick="toggleSubmenu(event)">Reportes</a>
				<!-- <ul class="submenu">
                        <li> <a href="#">Reporte de Clientes</a></li>
                        <li> <a href="#">Reporte de Cuentas</a></li>
                    </ul>
                     --></li>
		</ul>
		</aside>

		<div class="content">
			<div class="form-card">
				<h2>Editar de Cliente</h2>
				<form action="servletEditarCliente" method="POST">
					<%
						ArrayList<Cliente> listaCliente = (ArrayList<Cliente>) request.getAttribute("listaCliente");
					%>
					<label for="Cliente">Seleccione Cliente a Editar:</label> <select
						id="Cliente" name="Cliente" required onchange="this.form.submit()">
						<option value="" disabled selected>Seleccione Cliente a
							Editar</option>
						<%
							if (listaCliente != null) {
								for (Cliente Clie : listaCliente) {
						%>
						<option value="<%=Clie.getCuil().toString()%>"><%=Clie.getCuil().toString() + " " + Clie.getApellido()%></option>
						<%
							}
							}
						%>
					</select>
				</form>
				<!-- Primer formulario (GET) GUARDAMOS HASTA PROVINCIA -->
				<form action="servletEditarCliente" method="GET">
					<%
						Cliente clienteSe = new Cliente();

						if (request.getAttribute("clienteSeleccionado") != null) {
							System.out.println("Estoy dentro");

							String cuilBuscado = (String) request.getAttribute("clienteSeleccionado");
							System.out.println(cuilBuscado);
							for (Cliente cliente : listaCliente) {

								if (cliente.getCuil().equals(cuilBuscado)) {
									System.out.println("Lo encontré");

									clienteSe = cliente;
									System.out.println(clienteSe.toString());
									break;
								}
							}

						} else {
							System.out.println("no entre culiado");
						}
					%>
					<label for="dni">DNI</label> <input type="text" id="dni" name="dni"
						placeholder="DNI"
						value="<%=request.getParameter("nombre") != null ? request.getParameter("nombre") : ""%>"
						required> <label for="cuil">CUIL</label> <input
						type="text" id="cuil" name="cuil" placeholder="CUIL"
						value="<%=request.getParameter("nombre") != null ? request.getParameter("nombre") : ""%>"
						required> <label for="nombre">Nombre</label> <input
						type="text" id="nombre" name="nombre" placeholder="Nombre"
						value="<%=request.getParameter("nombre") != null ? request.getParameter("nombre") : ""%>"
						required> <label for="apellido">Apellido</label> <input
						type="text" id="apellido" name="apellido" placeholder="Apellido"
						value="<%=request.getParameter("apellido") != null ? request.getParameter("apellido") : ""%>"
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
							<%=Integer.toString(sex.getId_sexo()).equals(request.getParameter("genero")) ? "selected"
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
							<%=nac.getIdNacionalidadNc().equals(request.getParameter("nacionalidad")) ? "selected" : ""%>><%=nac.getDescripcionNc()%></option>
						<%
							}
							}
						%>
					</select> <label for="fecha-nacimiento">Fecha de Nacimiento</label> <input
						type="text" id="fecha-nacimiento" name="fecha-nacimiento"
						placeholder="YYYY/MM/DD"
						value="<%=request.getParameter("fecha-nacimiento") != null ? request.getParameter("fecha-nacimiento") : ""%>"
						required> <label for="direccion">Dirección</label> <input
						type="text" id="direccion" name="direccion"
						placeholder="Dirección"
						value="<%=request.getParameter("direccion") != null ? request.getParameter("direccion") : ""%>"
						required> <label for="numero">Número</label> <input
						type="text" id="numero" name="numero" placeholder="Número"
						value="<%=request.getParameter("numero") != null ? request.getParameter("numero") : ""%>"
						required> <label for="telefono">Teléfono</label> <input
						type="tel" id="telefono" name="telefono" placeholder="Teléfono"
						value="<%=request.getParameter("telefono") != null ? request.getParameter("telefono") : ""%>"
						required> <label for="email">Correo Electrónico</label> <input
						type="email" id="email" name="email"
						placeholder="Correo Electrónico"
						value="<%=request.getParameter("email") != null ? request.getParameter("email") : ""%>"
						required> <label for="provincia">Provincia</label> <select
						id="provincia" name="provincia" onchange="this.form.submit();"
						required>
						<option value="" disabled selected>Seleccione su
							Provincia</option>
						<%
							ArrayList<Provincia> listaProvincias = (ArrayList<Provincia>) request.getAttribute("listaProvincias");
							String selectedProvincia = request.getParameter("provincia");
							if (listaProvincias != null) {
								for (Provincia prov : listaProvincias) {
						%>
						<option value="<%=prov.getId_provincia()%>"
							<%=Integer.toString(prov.getId_provincia()).equals(selectedProvincia) ? "selected" : ""%>><%=prov.getNombre()%></option>
						<%
							}
							}
						%>
					</select> <input type="hidden" name="localidad"
						value="<%=request.getParameter("localidad")%>">
				</form>

				<!-- Segundo formulario (POST) USAMOS LO QUE GUARDAMOS ANTES y le sumamos la localidad q cambia segun la provincia elegida -->
				<form action="servletEditarCliente" method="POST">
					<input type="hidden" name="dni"
						value="<%=request.getParameter("dni")%>"> <input
						type="hidden" name="cuil"
						value="<%=request.getParameter("cuil")%>"> <input
						type="hidden" name="nombre"
						value="<%=request.getParameter("nombre")%>"> <input
						type="hidden" name="apellido"
						value="<%=request.getParameter("apellido")%>"> <input
						type="hidden" name="genero"
						value="<%=request.getParameter("genero")%>"> <input
						type="hidden" name="nacionalidad"
						value="<%=request.getParameter("nacionalidad")%>"> <input
						type="hidden" name="fecha-nacimiento"
						value="<%=request.getParameter("fecha-nacimiento")%>"> <input
						type="hidden" name="direccion"
						value="<%=request.getParameter("direccion")%>"> <input
						type="hidden" name="numero"
						value="<%=request.getParameter("numero")%>"> <input
						type="hidden" name="telefono"
						value="<%=request.getParameter("telefono")%>"> <input
						type="hidden" name="email"
						value="<%=request.getParameter("email")%>"> <input
						type="hidden" name="provincia"
						value="<%=request.getParameter("provincia")%>"> <label
						for="localidad">Localidad</label> <select id="localidad"
						name="localidad" required>
						<option value="" disabled selected>Seleccione su
							Localidad</option>
						<%
							ArrayList<Localidad> listaLocalidad = (ArrayList<Localidad>) request.getAttribute("listaLocalidad");
							String selectedLocalidad = request.getParameter("localidad");
							if (listaLocalidad != null) {
								for (Localidad loc : listaLocalidad) {
						%>
						<option value="<%=loc.getId_localidad()%>"
							<%=Integer.toString(loc.getId_localidad()).equals(selectedLocalidad) ? "selected" : ""%>><%=loc.getNombreLoca()%></option>
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