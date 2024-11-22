<%@ page import="java.util.List"%>
<%@ page import="entidades.Cliente"%>
<%@ page import="entidades.Usuario"%>
<%
	Usuario usuario = (Usuario) session.getAttribute("usuario");
	if (usuario == null) {
		response.sendRedirect("Login.jsp");
		return;
	}
%>

<!--<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>-->
<!DOCTYPE html>
<html lang="es">
<head>
<link href="${pageContext.request.contextPath}/Css/EditarCun.css"
	type="text/css" rel="stylesheet"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css" />
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Listar Cuentas</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>

<style>
table {
	width: 100%;
	border-collapse: collapse;
}

th, td {
	border: 1px solid #000;
	padding: 8px;
	text-align: left;
}

th {
	background-color: #f2f2f2;
}
</style>

<script type="text/javascript">
        $(document).ready(function () {
            $('#table_id').DataTable({
                "language": {
                    "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json"
                },
                "aLengthMenu": [
                    [5, 25, 50, 100, -1],
                    [5, 25, 50, 100, "Todos"]
                ]
            });
        });
    </script>
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
		<jsp:include page="Admin_SubMenu.jsp" />

		<div class="content">
			<h2>Listado de Clientes</h2>
			<%
                List<Cliente> listaClientes = (List<Cliente>) request.getAttribute("listaClientes");
                if (listaClientes != null && !listaClientes.isEmpty()) {
            %>
			<table id="table_id" class="display">
				<thead>
					<tr>
						<th>CUIL</th>
						<th>DNI</th>
						<th>Nombre</th>
						<th>Apellido</th>
						<th>Sexo</th>
						<th>Nacionalidad</th>
						<th>Fecha Nacimiento</th>
						<th>Direccion</th>
						<th>Localidad</th>
						<th>Correo</th>
						<th>Telefono</th>
						<th>Estado</th>
					</tr>
				</thead>
				<tbody>
					<% for (Cliente cliente : listaClientes) { %>
					<tr>
						<td><%= cliente.getCuil() %></td>
						<td><%= cliente.getDni() %></td>
						<td><%= cliente.getNombre() %></td>
						<td><%= cliente.getApellido() %></td>
						<td><%= cliente.getDescripcion_sexo() %></td>
						<td><%= cliente.getId_nacionalidad() %></td>
						<td><%= cliente.getFechaNacimiento() %></td>
						<td><%= cliente.getDireccion() %></td>
						<td><%= cliente.getDescripcion_localidad() %></td>
						<td><%= cliente.getCorreo() %></td>
						<td><%= cliente.getTelefono() %></td>
						<td><%= cliente.isEstado() ? "Activo" : "Inactivo" %></td>
					</tr>
					<% } %>
				</tbody>
			</table>
			<% } else { %>
			<p>No se encontraron clientes.</p>
			<% } %>
		</div>
	</div>

	<script src="${pageContext.request.contextPath}/JS/MenuAdm.js"></script>
</body>
</html>
