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
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Proceder Pago</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Css/ProcederPago.css">
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
	<jsp:include page="SubMenu_Cliente.jsp" />

		<div class="content">
			<div class="form-card">
				<h2>Confirmación de Pago</h2>
				<p>A continuación se muestra la información del préstamo
					seleccionado:</p>

				<!-- Datos de préstamo (simulados para el prototipo) -->
				<div class="loan-details">
					<p>
						<strong>Número de Préstamo:</strong> 12345
					</p>
					<p>
						<strong>Importe de la Cuota:</strong> $1500
					</p>
					<p>
						<strong>Cuotas Restantes:</strong> 5
					</p>
				</div>

				<!-- Botones de acción -->
				<form action="#" method="post">
					<button type="submit" class="confirm-button">Confirmar
						Pago</button>
				</form>

				<form action="PagoPrestamo.jsp" method="get">
					<button type="submit" class="cancel-button">Cancelar</button>
				</form>
			</div>
		</div>
	</div>

	<script src="JS/MenuAdm.js"></script>
	<script>
        function toggleSidebar() {
            const sidebar = document.getElementById('sidebar');
            sidebar.classList.toggle('active');
        }

        function toggleSubmenu(event) {
            const menuItem = event.target.parentElement;
            menuItem.classList.toggle('active');
        }
</body>
</html>
