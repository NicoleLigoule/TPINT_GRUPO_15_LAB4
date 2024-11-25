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
<title>Baja de cliente</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Css/EliminarCli.css">
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
		
		<jsp:include page="SubMenu_Admin.jsp" />

		<div class="content">
			<div class="form-card">
				<h2>BAJA CLIENTE</h2>
				<form action="servletEliminarCliente" method="get">
					<label for="cuil">Ingresar C.U.I.L</label> <input type="text"
						id="cuil" name="cuil" placeholder="C.U.I.L" required>
					<div class="button-group">
						   <button 
				            type="submit" 
				            name="btnBaja" 
				            class="submit-button"
				            onclick="return confirm('¿Estás seguro de que deseas dar de baja este cliente?');">
				            Dar Baja
				        </button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div
		style="text-align: center; font-size: 18px; padding: 10px; margin-top: 20px;">
		<%
            String mensaje = "";
            if (request.getAttribute("mensaje") != null) {
                mensaje = (String) request.getAttribute("mensaje");
            }
        %>
		<%= mensaje %>
	</div>

	<script src="JS/MenuAdm.js"></script>
</body>
</html>
