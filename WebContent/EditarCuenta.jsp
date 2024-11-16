<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="entidades.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidades.Cuenta"%>
<%@page import="entidades.TipoDeCuenta"%>




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
		<aside class="sidebar" id="sidebar">
		<ul>
			<li class="menu-item"><a href="#" onclick="toggleSubmenu(event)">Clientes</a>
				<ul class="submenu">
					<li><a href="AgregarCliente.jsp">Agregar Cliente</a></li>
					<li><a href="EliminarCliente.jsp">Baja Cliente</a></li>
					<li><a href="EditarCliente.jsp">Editar Cliente</a></li>
					<li><a href="ListarCliente.jsp">Listar Cliente</a></li>
				</ul></li>
			<li class="menu-item"><a href="#" onclick="toggleSubmenu(event)">Cuentas</a>
				<ul class="submenu">
					<li><a href="AgregarCuenta.jsp">Agregar Cuenta</a></li>
					<li><a href="EliminarCuenta.jsp">Baja Cuenta</a></li>
					<li><a href="EditarCuenta.jsp">Editar Cuenta</a></li>
					<li><a href="ListarCuenta.jsp">Listar Cuenta</a></li>
				</ul></li>
			<li class="menu-item"><a href="#" onclick="toggleSubmenu(event)">Transacciones</a>
				<!-- <ul class="submenu">
                        <li> <a href="#">Registrar Transacci�n</a></li>
                        <li> <a href="#">Ver Historial</a></li>
                    </ul>
                     --></li>
			<li class="menu-item"><a href="#" onclick="toggleSubmenu(event)">Reportes</a>
				<!-- <ul class="submenu">
                        <li> <a href="#">Reporte de Clientes</a></li>
                        <li> <a href="#">Reporte de Cuentas</a></li>
                    </ul>
                     --></li>
		</ul>
		</aside>


		<div class="Mastercontainer">

			<div class="content">
				<div class="form-card">
					<h2>Buscar Cuentas Del Cliente</h2>
					<br>
					<h3>Datos de la Cuenta</h3>
					<!--  <form>
                    <label for="cuil">CUIL CLIENTE</label>
                    <input type="text" id="dni" name="dni" placeholder="DNI" >
                    <div class="button-group">
                        <button type="submit" class="submit-button">Buscar</button>
                    </div>
				</form>-->
					<form
						action="${pageContext.request.contextPath}/servletEditarCuenta"
						method="get">
						<label for="cuil">CUIL CLIENTE</label> <input type="text"
							id="cuil" name="cuil" placeholder="CUIL">
						<div class="button-group">
							<button type="submit" class="buscarBtn">Buscar</button>
						</div>
					</form>
				</div>
			</div>
			<div class="content">
				<div class="form-card">
					<form
						action="${pageContext.request.contextPath}/servletEditarCuenta"
						method="post">
						<label for="NC">NUMERO DE CUENTA</label> <select id="NroDeCuenta"
							name="NroDeCuenta" required>
							<option value="" disabled selected>Seleccione la cuenta</option>
							
							<%
								if(request.getAttribute("cuentas") != null){
									ArrayList<Cuenta> listaCuentas = (ArrayList<Cuenta>) request.getAttribute("cuentas");
									
									for(Cuenta cu : listaCuentas){
										%>
										
										<option value="<%=cu.getNumeroDeCuentaCu()%>">
										<%=cu.getNumeroDeCuentaCu()%></option>
										
										<%
									}
								}
							%>
							
							
							
						</select>

						<h3>Cambiar Tipo de Cuenta</h3>
						<label for="tipoDeCuenta">Tipo de Cuenta</label> <select
							id="tipoDeCuenta" name="tipoDeCuenta" required>
							<option value="" disabled selected>Seleccione un tipo de
								cuenta</option>
							<!-- Cargar opciones de tipos de cuenta aqu� -->
							
							<%
							if(request.getAttribute("tipos") != null){
								ArrayList<TipoDeCuenta> tipos = (ArrayList<TipoDeCuenta>)request.getAttribute("tipos");
								
								for(TipoDeCuenta tcu : tipos){
									
									%>
									<option value="<%=tcu.getIdTipoDeCuenta() %>" ><%=tcu.getNombreTipo() %></option>
									<%
								}
							}
							%>
							
							
							
							
						</select>

						<div class="button-group">
							<button type="button" class="cancel-button">Volver</button>
							<button type="submit" class="submit-button">Modificar</button>
						</div>
					</form>


					<!-- Mensaje de Exito o error -->
					<c:if test="${not empty mensaje}">
						<p>${mensaje}</p>
					</c:if>

					<!-- 	<form>
				<h2>Editar Cuenta Seleccionada</h2>
                    <label for="NC">NUMERO DE CUENTA</label>
                    <select id="NroDeCuenta"  name="NroDeCuenta"  required>
                    <option value="" disabled selected>Seleccione la cuenta</option>
                    </select> 

                    <br>
                    <h3>Cambiar Tipo de Cuenta</h3>               

                    <label for="tipoDeCuenta">Tipo de Cuenta</label>
                    <select id="tipoDeCuenta"  name="tipoDeCuenta"  required>
                    <option value="" disabled selected>Seleccione un tipo de cuenta</option>
                    </select>

                    

                    <div class="button-group">
                        <button type="button" class="cancel-button">Volver</button>
                        <button type="submit" class="submit-button">Modificar</button>
                    </div>
                </form> -->
				</div>
			</div>
		</div>
	</div>

	<script src="JS/MenuAdm.js"></script>
</body>
</html>