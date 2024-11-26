<%@page import="entidades.Nacionalidad"%>
<%@page import="entidades.Sexo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="entidades.Prestamo"%>
<%@page import="entidades.Localidad"%>
<%@page import="entidades.Cliente"%>
<%@page import="entidades.InteresesXCantidadDeMeses"%>
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
<title>Examinar Prestamo</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Css/AgregarCli.css">
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
				<h2>Examinar Prestamo</h2>
				<%Prestamo IDPRestam =(Prestamo)request.getAttribute("Prestamo"); %>
				<%Cliente Client =(Cliente)request.getAttribute("Clientes"); %>
				<%InteresesXCantidadDeMeses Interese =(InteresesXCantidadDeMeses)request.getAttribute("Interes"); %>
				
				<form action="servletExaminarPrestamo" method="POST">
					<label for="ID">Identificador De prestamo</label> <input type="text" id="Identificador" name="Identificador"
						placeholder="Identificador"
						value="<%=IDPRestam.getIdPrestamoPt()!= -1 ? IDPRestam.getIdPrestamoPt() : ""%>"
						readonly>
			       <label for="Importe">Importe solicitado</label> <input type="text" id="Importe" name="Importe"
						placeholder="DNI"
						value=$"+<%=IDPRestam.getImporteSolicitadoPt() != null ? IDPRestam.getImporteSolicitadoPt() : ""%>"
						readonly>
					<label for="cuil">Importe con intereses</label> <input
						type="text" id="cuil" name="cuil" placeholder="CUIL"
						value="<%=request.getParameter("cuil") != null ? request.getParameter("cuil") : ""%>"
						required> 

				</form>

			</div>
		</div>
		<div class="content">
			<div class="form-card">
				<h2>Examinar Prestamo</h2>
				
				
				<form action="servletExaminarPrestamo" method="POST">
					<label for="ID">Identificador De prestamo</label> <input type="text" id="Identificador" name="Identificador"
						placeholder="Identificador"
						value="<%=IDPRestam.getIdPrestamoPt()!= -1 ? IDPRestam.getIdPrestamoPt() : ""%>"
						readonly>
			       <label for="Importe">Importe solicitado</label> <input type="text" id="Importe" name="Importe"
						placeholder="DNI"
						value=$"+<%=IDPRestam.getImporteSolicitadoPt() != null ? IDPRestam.getImporteSolicitadoPt() : ""%>"
						readonly>
					<label for="cuil">Importe con intereses</label> <input
						type="text" id="cuil" name="cuil" placeholder="CUIL"
						value="<%=request.getParameter("cuil") != null ? request.getParameter("cuil") : ""%>"
						required> 

				</form>

			</div>
		</div>
	</div>
</div>

	<script src="JS/MenuAdm.js"></script>
</body>
</html>
