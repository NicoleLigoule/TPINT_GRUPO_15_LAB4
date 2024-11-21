<%@page import="entidades.Nacionalidad"%>
<%@page import="entidades.Sexo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="daoImpl.LocalidadDaoImpl"%>
<%@page import="daoImpl.ProvinciaDaoImpl"%>
<%@page import="entidades.Provincia"%>
<%@page import="entidades.Localidad"%>

<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Confirmaci�n de Prestamo</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Css/RegistrarseNuevo.css">
</head>
<body>
	<nav>
	<div class="nav-content">
		<a href="#Login.jsp"> <img src="img/png_logo.png" class="img_logo"
			alt="Logo UTN">
		</a>

		<h1>BANCO UTN</h1>
	</div>
	</nav>

	<div class="main-container">


		<div class="content">
			<div class="form-card">
				<h2>Confirmar Solicitud De Prestamo</h2>

				<form action="ServletProcesarPrestamo" method="post">
				    <label for="cuenta_destino">Cuenta Destino</label>
				    <input type="text" id="cuenta_destino" name="cuenta_destino" value="<%= request.getAttribute("cuentaDestino") %>" readonly>
				
				    <label for="importe_solicitado">Monto Solicitado</label>
				    <input type="text" id="importe_solicitado" name="importe_solicitado" value="<%= request.getAttribute("importeSolicitado") %>" readonly>
				
				    <label for="monto_con_interes">Monto Con Inter�s</label>
				    <input type="text" id="monto_con_interes" name="monto_con_interes" value="<%= request.getAttribute("montoConInteres") %>" readonly>
				
				    <label for="plazo_pago">Cantidad De Cuotas</label>
				    <input type="text" id="plazo_pago" name="plazo_pago" value="<%= request.getAttribute("plazoPago") %>" readonly>
				
				    <label for="monto_por_cuota">Monto Por Cuota</label>
				    <input type="text" id="monto_por_cuota" name="monto_por_cuota" value="<%= request.getAttribute("montoPorCuota") %>" readonly>
				
				    <div class="button-group">
				        <button type="button" onclick="window.location.href='${pageContext.request.contextPath}/InicioCliente.jsp'" class="cancel-button">Volver</button>
				        <button type="submit" class="submit-button" name="confirmar">Confirmar</button>
				    </div>
				</form>


					<!-- mensaje de que carg� bien y limpia, sino msj carg� mal -->
					<%
						String status = request.getParameter("status");
						String mensajeExito = "Se ha generado el Cliente correctamente.";
						String mensajeError = (String) request.getAttribute("Error");
					%>
					<%
						if ("success".equals(status)) {
					%>
					<div class="alert alert-success">
						<%=mensajeExito%>
					</div>
					<%
						}
					%>

					<%
						if (mensajeError != null) {
					%>
					<div class="alert alert-danger">
						<%=mensajeError%>
					</div>
					<%
						}
					%>

			</div>
		</div>
	</div>


</body>
</html>
