<%@page import="entidades.Nacionalidad"%>
<%@page import="entidades.Sexo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="entidades.Prestamo"%>
<%@page import="entidades.Localidad"%>
<%@page import="entidades.Cliente"%>
<%@page import="entidades.InteresesXCantidadDeMeses"%>
<%@ page import="entidades.Usuario"%>
<%@page import= "java.math.BigDecimal"%>
<%@page import="java.math.RoundingMode"%>
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
<title>Examinar Prestamo</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Css/ReportePrestamo.css">
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
		<jsp:include page="SubMenu_Admin.jsp" />
		
   <div class="Mastercontainer">
		<div class="content">
			<div class="form-card">
				<h2>Porcentaje De Prestamos Aprobados</h2>

				
				<form action="servletExaminarPrestamo" method="POST">

						

				</form>

			</div>
		</div>
		<div class="content">
			<div class="form-card">
				<h2>Rendimiento Prestamos Aprobados</h2>
				
				
				<form>
					
				</form>

			</div>
		</div>
	</div>
</div>

	<script src="JS/MenuAdm.js"></script>
</body>
</html>
