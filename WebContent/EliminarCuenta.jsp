<%@ page import="entidades.ClienteCuentaDTO"%>
<%@ page import="java.util.ArrayList"%>
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
<title>Baja de cuenta</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Css/EliminarCun.css">
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
		</a> <span class="username"><%=usuario.getUsuarioUs()%></span> <span
			class="username"><%=usuario.getUsuarioUs()%></span>
	</nav>

	<div class="main-container">
		<jsp:include page="SubMenu.jsp" />

		<div class="content">
			<div class="form-card">
				<h2>Baja de Cuentas</h2>
				<br>
				<h3>Datos del socio</h3>

				<!-- Primer formulario: GET para buscar cliente -->
				<form action="servletEliminarCuenta" method="get">
					<label for="cuil">CUIL Cliente</label> 
					
					<input type="text" id="cuil"	name="cuil" value="${param.cuil}" placeholder="CUIL" required>					

					<div class="button-group">
						<button type="submit" name="buscarCliente" class="submit-button">Buscar	cliente</button>						
					</div>
					
					<label for="nombre">Cliente</label> <input type="text" id="nombre" name="nombre" value="${requestScope.nombreApellido}"
						placeholder="Nombre y Apellido" readonly> 
					

						<%
							// Obtenemos la lista de cuentas
							ArrayList<ClienteCuentaDTO> listaCuentas = (ArrayList<ClienteCuentaDTO>) request.getAttribute("listaCuentas");

							
						%>
						


				</form>

				<!-- Segundo formulario: POST para eliminar cuenta -->
				<%
					String nombreApellido = (String) request.getAttribute("nombreApellido");
					listaCuentas = (ArrayList<ClienteCuentaDTO>) request.getAttribute("listaCuentas");
				%>
				<br>
				<br>
				<form action="servletEliminarCuenta" method="post">
				
					<!-- <label for="nombre">Cliente</label> <input type="text" id="nombre" name="nombre"
						value="<%=nombreApellido != null ? nombreApellido : ""%>"
						placeholder="Nombre y Apellido" readonly>  -->
						
						
						
						 <select	id="cuentasDelCliente" name="cuentasDelCliente" required> 
						<option value="" disabled selected>Seleccione una cuenta
							a eliminar</option>
						<%
							if (listaCuentas != null) {
								for (ClienteCuentaDTO cuenta : listaCuentas) {
									int numeroCuenta = cuenta.getNumeroCuenta();
									String descripcionCuenta = cuenta.getTipoCuenta();

									if (numeroCuenta != 0) {
						%>
						<option value="<%=numeroCuenta%>"><%=numeroCuenta + " - " + descripcionCuenta%></option>
						<%
							}
								}
							}
						%>
					</select> 

					<div class="button-group">
					<button type="button" class="cancel-button" onclick="window.history.back();">Volver</button>
				        <button 
				            type="submit" 
				            class="submit-button"
				            onclick="return confirm('¿Estás seguro de que deseas eliminar esta cuenta?');">
				            Solicitar baja
				        </button>
					</div>

				<div class="button-group">
						
						
						<a href="MenuAdm.jsp"><button type="button" class="submit-button">Volver al Menú Principal</button></a>
						
					</div>
					<div
						style="text-align: center; font-size: 18px; padding: 10px; margin-top: 20px;">
						<%
							String mensaje = "";
							if (request.getAttribute("mensaje") != null) {
								mensaje = (String) request.getAttribute("mensaje");
							}
						%>
						<%=mensaje%>
					</div>


				</form>
			</div>
		</div>
	</div>



	<script src="JS/MenuAdm.js"></script>
</body>
</html>