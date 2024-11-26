<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page import="entidades.Usuario"%>
<%@ page import="java.util.List"%>
<%@ page import="entidades.Transferencias"%>
<%@ page import="entidades.Cuenta"%>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("Login.jsp");
        return;
    }

    // Obtener las cuentas desde el atributo de la sesión o desde la base de datos
    List<Cuenta> cuentas = (List<Cuenta>) request.getAttribute("cuentas"); // O el método que uses para pasar las cuentas
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Realizar Transferencia</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/Transferencia.css">
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
		</a> <span class="username"><%= usuario.getUsuarioUs() %></span>
	</nav>

	<div class="main-container">
			<jsp:include page="SubMenu_Cliente.jsp" />
		<div class="content">
			<div class="form-card">
				<h2>Validar CBU</h2>
				<p>Elegi a que cuenta transferir</p>
				<form action="ImporteTransferencia.jsp" method="post">
					<input type="text" id="cbu" name="cbu" required pattern="\d{22}"
						placeholder="Ingresa el CBU" maxlength="22">
					<button type="submit" class="confirm-button">Validar</button>
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
    </script>
</body>
</html>
