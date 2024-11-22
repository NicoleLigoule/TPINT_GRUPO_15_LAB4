<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Prestamos</title>
<link href="${pageContext.request.contextPath}/Css/AgregarCli.css"
	type="text/css" rel="stylesheet"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css" />
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
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
	$(document)
			.ready(
					function() {
						$('#table_id')
								.DataTable(
										{
											"language" : {
												"url" : "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json"
											},
											"aLengthMenu" : [
													[ 5, 25, 50, 100, -1 ],
													[ 5, 25, 50, 100, "Todos" ] ]
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
	</a> <span class="username"><%=usuario.getUsuarioUs()%></span> </nav>

	<div class="main-container">
		<jsp:include page="Admin_SubMenu.jsp" />

		<div class="content">
			<div>
				<h2>Listado de Préstamos Solicitados</h2>
				<br>
				<div>
					<table id="table_id" class="display">
						<thead>
							<tr>
								<th>Dni</th>
								<th>Cuil</th>
								<th>Nombre</th>
								<th>Apellido</th>
								<th>Género</th>
								<th>Nacionalidad</th>
								<th>Fecha de Nacimiento</th>
								<th>Dirección</th>
								<th>Provincia</th>
								<th>Localidad</th>
								<th>Correo Electrónico</th>
								<th>Teléfono</th>
								<th>Seleccionar</th>
							</tr>
						</thead>
						<tbody>

							<tr>
								<td>12345678</td>
								<td>20-12345678-9</td>
								<td>Juan</td>
								<td>Pérez</td>
								<td>M</td>
								<td>Argentina</td>
								<td>1980-05-15</td>
								<td>Av. Siempre Viva 123</td>
								<td>Buenos Aires</td>
								<td>La Plata</td>
								<td>juan.perez@example.com</td>
								<td>1234-5678</td>
								<td><button onclick="seleccionarFila(this)">Seleccionar</button></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

	<script src="JS/MenuAdm.js"></script>
	<script>
		function seleccionarFila(boton) {
			const fila = boton.closest('tr');
			fila.style.backgroundColor = "#d1e7dd";
			alert("Fila seleccionada: " + fila.cells[2].innerText + " "
					+ fila.cells[3].innerText);
		}
	</script>
</body>
</html>
