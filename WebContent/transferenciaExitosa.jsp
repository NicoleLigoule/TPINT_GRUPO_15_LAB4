<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page import="javax.servlet.http.HttpSession"%>
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
<title>Transferencia</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Css/Transferencia.css">
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

		<div class="form-container">
			<h2>Transferencia Completada</h2>
			<p>La transferencia se realizó con éxito.</p>
			<p>
				<strong>CBU Destino:</strong> ${sessionScope.cbu}
			</p>
			<p>
				<strong>Importe Transferido:</strong> $${param.importe}
			</p>
			<div style="margin-top: 30px;"></div>
			<a href="InicioCliente.jsp" class="btn">Volver al Menú</a>
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
