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
<title>MenuAdm</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Css/MenuAdm.css">
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
		</a> <span class="username"><%= usuario.getUsuarioUs() %></span>
	</nav>

	<div class="main-container">
		<aside class="sidebar">
			<ul>
				<li class="menu-item"><a href="#"
					onclick="toggleSubmenu(event)">Clientes</a>
					<ul class="submenu">
						<li><a
							href="${pageContext.request.contextPath}/servletAgregarCliente?Param=1">Agregar
								Cliente</a></li>
						<li><a href="EliminarCliente.jsp">Baja Cliente</a></li>
						<li><a
							href="${pageContext.request.contextPath}/servletEditarCliente?Param=1">Editar
								Cliente</a></li>
						<li><a
							href="${pageContext.request.contextPath}/servletListarCliente?Param=1">Listar
								Cliente</a></li>
					</ul></li>
				<li class="menu-item"><a href="#"
					onclick="toggleSubmenu(event)">Cuentas</a>
					<ul class="submenu">
						<li><a
							href="${pageContext.request.contextPath}/servletsSolicitarCuenta?Param=1">Agregar
								Cuenta</a></li>


						<li><a
							href="${pageContext.request.contextPath}/servletEditarCuenta?Param=1">Editar
								Cuenta</a></li>

						<li><a href="EliminarCuenta.jsp">Baja Cuenta</a></li>

						<li><a
							href="${pageContext.request.contextPath}/ServletListarCuenta?Param=1">Listar
								Cuenta</a></li>

					</ul></li>
				<li class="menu-item"><a href="#"
					onclick="toggleSubmenu(event)">Transacciones</a> <!-- <ul class="submenu">
                        <li> <a href="#">Registrar Transacci�n</a></li>
                        <li> <a href="#">Ver Historial</a></li>
                    </ul>
                     --></li>
				<li class="menu-item">
					<!--  <a href="#" onclick="toggleSubmenu(event)">Reportes</a>--> <a
					href="servletsSolicitarCuenta?Param=1"
					onclick="toggleSubmenu(event)">Reportes</a> <!-- <ul class="submenu">
                        <li> <a href="#">Reporte de Clientes</a></li>
                        <li> <a href="#">Reporte de Cuentas</a></li>
                    </ul>
                     -->
				</li>
			</ul>
		</aside>
		<div class="content">
			<div class="form-card">
				<h2>Este es el men� de los usuarios administradores</h2>
			</div>
		</div>

	</div>

	<script src="JS/MenuAdm.js"></script>

</body>
</html>