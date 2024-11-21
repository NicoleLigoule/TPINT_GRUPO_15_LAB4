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
<title>Confirmación de Prestamo</title>
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

				<form action="ServletRegistrarseNuevo" method="GET">
					<label for="dni">Cuenta Destino</label> <input type="text" id="miTextbox" name="miTextbox" value="Texto de ejemplo" readonly> 
					<label for="dni">Monto Solicitado</label> <input type="text" id="miTextbox" name="miTextbox" value="Texto de ejemplo" readonly>
					<label for="dni">Monto Con Interes</label> <input type="text" id="miTextbox" name="miTextbox" value="Texto de ejemplo" readonly>	
					<label for="dni">Cantidad De Cuotas</label> <input type="text" id="miTextbox" name="miTextbox" value="Texto de ejemplo" readonly>
					<label for="dni">Monto Por Cuota</label> <input type="text" id="miTextbox" name="miTextbox" value="Texto de ejemplo" readonly>
				</form>



					<div class="button-group">
						<button type="button" class="cancel-button">Cancelar</button>
						<button type="submit" class="submit-button" name="agregarBtn">Confirmar</button>
					</div>

					<!-- mensaje de que cargó bien y limpia, sino msj cargó mal -->
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
				</form>

			</div>
		</div>
	</div>


</body>
</html>
