<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="entidades.Cuenta"%>
<%@ page import="entidades.InteresesXCantidadDeMeses"%>
<%@ page import="java.util.List"%>
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
<title>SolicitarPrestamo</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Css/SolicitarPrestamo.css">
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


	<jsp:include page="SubMenu_Cliente.jsp" />
	<div class="main-container">

		<div class="content">
			<div class="form-card">
				<h2>Solicitud de Prestamo</h2>
				<form action="${pageContext.request.contextPath}/ServletConfirmarPrestamo" method="post">


				<label for="cuenta_destino">Cuenta Destino</label>
				<select id="cuenta_destino" name="cuenta_destino" required>
				    <option value="" disabled selected>Seleccione la cuenta</option>
				    <%
				        List<Cuenta> cuentas = (List<Cuenta>) request.getAttribute("cuentas");
				        if (cuentas != null && !cuentas.isEmpty()) {
				            for (Cuenta cuenta : cuentas) {
				    %>
				                <option value="<%= cuenta.getNumeroDeCuentaCu() %>">
				                    Cuenta: <%= cuenta.getNumeroDeCuentaCu() %> 
				                </option>
				    <%
				            }
				        } else {
				    %>
				        <option value="" disabled>No hay cuentas disponibles</option>
				    <%
				        }
				    %>
				</select>
					
					<label for="fecha_peticion">Fecha de Petición</label> <input
						type="text" id="fecha_peticion" name="fecha_peticion" disabled
						class="form-input"> <label for="importe_solicitado">Importe Solicitado (Sin signo $)</label> <input type="number" 
						id="importe_solicitado" name="importe_solicitado" required step="0.01" class="form-input">

					<!-- Plazo de Pago como un Dropdown (DDL) -->
					<%-- <label for="plazo_pago">Cantidad de Cuotas</label> <select
						id="plazo_pago" name="plazo_pago" required class="form-input">
                     <option value="" disabled selected>Seleccione las Cuotas</option>
				    <%
				    ArrayList<InteresesXCantidadDeMeses> interes = (ArrayList<InteresesXCantidadDeMeses>) request.getAttribute("intereses");
				        if (interes != null && !interes.isEmpty()) {
				            for (InteresesXCantidadDeMeses intere : interes) {
				    %>
				                <option value="<%= intere.getPlazoDPagosEnMesesIxm() %>">
				                    en : <%= intere.getMeses() %> cuotas
				                </option>
				    <%
				            }
				        } else {
				    %>
				        <option value="" disabled>No hay cuentas disponibles</option>
				    <%
				        }
				    %>
					</select> --%>
					
					<!-- Plazo de Pago como un Dropdown (DDL) -->
					<label for="plazo_pago">Cantidad de Cuotas</label>
					<select id="plazo_pago" name="plazo_pago" required>
					    <option value="" disabled selected>Seleccione el plazo de pago</option>
					    <%
					        List<InteresesXCantidadDeMeses> plazosPago = (List<InteresesXCantidadDeMeses>) request.getAttribute("plazosPago");
					        if (plazosPago != null && !plazosPago.isEmpty()) {
					            for (InteresesXCantidadDeMeses plazo : plazosPago) {
					    %>
					            <option value="<%= plazo.getPlazoDPagosEnMesesIxm() %>">
					                <%= plazo.getMeses() %> meses - Interes: <%= plazo.getInteresIxm() %>%
					            </option>
					    <%
					            }
					        } else {
					    %>
					        <option value="" disabled>No hay plazos de pago disponibles</option>
					    <%
					        } 
					    %>
					</select>

					<div class="button-group">
					 <button type="button" class="cancel-button" onclick="location='/TPINT_GRUPO_15_LAB4/servletInformacionCliente'">Cancelar</button>
						<button type="submit" class="submit-button">Enviar Solicitud</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<script src="JS/MenuAdm.js"></script>
	<script>
        // Script para mostrar la fecha del sistema en el campo de fecha deshabilitado
        window.onload = function() {
            const fechaElement = document.getElementById('fecha_peticion');
            const fechaActual = new Date().toLocaleDateString();
            fechaElement.value = fechaActual;
        };
    </script>
</body>
</html>
