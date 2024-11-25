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
<title>Pago Prestamo</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Css/PagoPrestamo.css">
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
				<h2>Pago de Préstamos</h2>
				<p>Seleccione el préstamo y la cuota que desea pagar:</p>   
				
			    <h1>Realizar Pago</h1>
			    <form action="ServletPagoPrestamo" method="post">
			        <label for="idPrestamo">ID del Préstamo:</label>
			        <input type="text" id="idPrestamo" name="idPrestamo" required>
			        <br>
			        <label for="monto">Monto a Pagar:</label>
			        <input type="number" id="monto" name="monto" step="0.01" required>
			        <br>
			        <button type="submit">Pagar</button>
			    </form>

		    <p>${mensaje}</p>
		    <a href="PagoPrestamo.jsp">Volver</a>

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
    </script>
</body>
</html>
