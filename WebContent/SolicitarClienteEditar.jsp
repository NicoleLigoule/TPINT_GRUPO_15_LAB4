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
				<form action="servletLIstarClientesEditar" method="POST">
					<%
						ArrayList<Cliente> listaCliente = (ArrayList<Cliente>) request.getAttribute("listaCliente");
					%>
					<label for="clienteSeleccionado">Seleccione Cliente a Editar:</label> <select
						id="clienteSeleccionado" name="clienteSeleccionado" required onchange="this.form.submit()">
						<option value="" disabled selected>Seleccione Cliente a
							Editar</option>
						<%
							if (listaCliente != null) {
								for (Cliente Clie : listaCliente) {
						%>
						<option value="<%=Clie.getCuil().toString()%>"><%=Clie.getCuil().toString() + " " + Clie.getApellido()%>
						

						<%
						
							}
							}
						%>
					</select>
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