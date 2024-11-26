<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="entidades.Usuario"%>
<%@ page import="entidades.Movimiento"%>
<%@ page import="entidades.Cuenta"%>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("Login.jsp");
        return;
    }

    List<Movimiento> listaMovimientos = (List<Movimiento>) request.getAttribute("listaMovimientos");
    List<Cuenta> cuentas = (List<Cuenta>) request.getAttribute("cuentas"); // Lista de cuentas asociadas al usuario
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Movimientos</title>
    <!-- Asegur�ndonos de que la ruta sea correcta -->
    <link href="${pageContext.request.contextPath}/Css/Movimientos.css" type="text/css" rel="stylesheet"/>

    <!-- Bloque de estilo directo en JSP para probar -->

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
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
	</a> <span class="username"><%= usuario.getUsuarioUs() %></span> </nav>

		<section class="content"> <!-- movimientos -->
			<jsp:include page="SubMenu_Cliente.jsp" />
			
		<div class="movements-list">
			<h3>Hoy</h3>
			<div class="movement-item">
				<div class="movement-icon"></div>
				<div class="movement-details">
					<p>
						<strong>Claudio Nu�ez</strong>
					</p>
					<p>Transferencia enviada</p>
					<p>Caja de ahorro</p>
				</div>
				<div class="movement-amount-negative">- $17,278.40</div>
				<div class="movement-time">18:28</div>
			</div>

			<h3>3 de noviembre</h3>
			<div class="movement-item">
				<div class="movement-icon"></div>
				<div class="movement-details">
					<p>
						<strong>Tomas Munguia</strong>
					</p>
					<p>Ingreso de dinero</p>
					<p>Transferencia externa</p>
				</div>
				<div class="movement-amount-positive">+ $220,000.00</div>
				<div class="movement-time">14:34</div>
			</div>

			<h3>2 de noviembre</h3>
			<div class="movement-item">
				<div class="movement-icon"></div>
				<div class="movement-details">
					<p>
						<strong>Matias Ferrero</strong>
					</p>
					<p>Transferencia enviada</p>
					<p>Cuenta Corriente</p>
				</div>
				<div class="movement-amount-negative">- $9,200.00</div>
				<div class="movement-time">19:49</div>
			</div>

			<h3>1 de noviembre</h3>
			<div class="movement-item">
				<div class="movement-icon"></div>
				<div class="movement-details">
					<p>
						<strong>Daniel Rios</strong>
					</p>
					<p>Transferencia enviada</p>
					<p>Caja de ahorro</p>
				</div>
                <% 
                    } else {
                %>
                    <p>No hay movimientos disponibles para la cuenta seleccionada.</p>
                <% } %>
            </div>
	
            <!-- Paginaci�n -->
            <div class="pagination">
                <span>1</span>
                <a href="#">2</a>
                <a href="#">3</a>
                <a href="#">Siguiente ></a>
            </div>
           
        </section>
		  
</form>
    </div>

    <script src="JS/MenuAdm.js"></script>
</body>
</html>
