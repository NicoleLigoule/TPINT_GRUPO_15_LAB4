<%@page import="entidades.Cliente"%>
<%@page import="entidades.Sexo"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>EditarCuenta</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Css/EditarCun.css">
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
		
		<jsp:include page="SubMenu.jsp" />

		<div class="Mastercontainer">

			<div class="content">
				<div class="form-card">
									<h2>Editar de Cliente</h2>
				<form action="servletEditarCliente" method="get">
					<label for="cuil">Ingrese CUIL del Cliente:</label>
				    <input type="text" id="cuil" name="cuil" placeholder="CUIL" required>
				      <div class="button-group">
				        <button type="submit" class="submit-button">Enviar</button>
				        <button type="reset" class="cancel-button">Cancelar</button>
				      </div>
				      <div class="button-group">
						
						
						<a href="MenuAdm.jsp"><button type="button" class="submit-button">Volver al Menú Principal</button></a>
						
					</div>
				</form>


					<!-- Mensaje de Exito o error -->
					<c:if test="${not empty mensaje}">
						<p>${mensaje}</p>
					</c:if>

				</div>
			</div>
		</div>
	</div>

	<script src="JS/MenuAdm.js"></script>
</body>
</html>