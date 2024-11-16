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
		<jsp:include page="SubMenu.jsp" />
		<div class="content">
			<div class="form-card">
				<h2>Bienvenido, <%= usuario.getUsuarioUs() %> al menu de los administradores</h2>
			</div>
		</div>

	</div>

	<script src="JS/MenuAdm.js"></script>

</body>
</html>