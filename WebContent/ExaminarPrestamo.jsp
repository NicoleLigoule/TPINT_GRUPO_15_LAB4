<%@page import="entidades.Nacionalidad"%>
<%@page import="entidades.Sexo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="entidades.Prestamo"%>
<%@page import="entidades.Localidad"%>
<%@page import="entidades.Cliente"%>
<%@page import="entidades.InteresesXCantidadDeMeses"%>
<%@ page import="entidades.Usuario"%>
<%@page import= "java.math.BigDecimal"%>
<%@page import="java.math.RoundingMode"%>
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
	href="${pageContext.request.contextPath}/Css/EditarCun.css">
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
						value=<%=IDPRestam.getImporteSolicitadoPt() != null ?IDPRestam.getImporteSolicitadoPt() : ""%>
						readonly>
					<label for="cuil">Importe con intereses</label> <input

						type="text" id="ImporteInteres" name="ImporteInteres" placeholder="CUIL"
						
						<%BigDecimal importeSolicitado = IDPRestam.getImporteSolicitadoPt();
						BigDecimal interes = Interese.getInteresIxm(); 
						BigDecimal porcentajeInteres = interes.divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);
						BigDecimal factor = BigDecimal.ONE.add(porcentajeInteres);
						BigDecimal precioInteres = importeSolicitado.multiply(factor); %>
						value=<%=precioInteres != null ? precioInteres.toString() : ""%>
						readonly>
						
				   <label for="Importe">Ganacias del Prestamo</label> <input type="text" id="GAnanci" name="GAnanci"
						placeholder="DNI"
						<% BigDecimal ganacia= precioInteres.subtract(IDPRestam.getImporteSolicitadoPt()); %>
						value=<%=ganacia != null ?ganacia : ""%>
						readonly>
				  <label for="Importe">Plazo de Pago En Mes</label> <input type="text" id="Plazo" name="Plazo"
						placeholder="Plazo"
						value=<%=Interese.getMeses() != -1 ?Interese.getMeses() : ""%>
						readonly>
				 <div class="button-group">
						<button type="submit" class="cancel-button" name="Rechazar">Rechazar</button>
						<button type="submit" class="submit-button" name="AprobarBtn" onclick="return aprobarPrestamo()">Aprobar</button>

						
					</div>
						

				</form>

			</div>
		</div>
		<div class="content">
			<div class="form-card">
				<h2> Solicitante</h2>
				
				
				<form>
					<label for="ID">C.U.I.L</label> <input type="text" id="Identificador" name="Identificador"
						placeholder="CuilCliente"
						value="<%=Client.getCuil()!= null ? Client.getCuil() : ""%>"
						readonly>
			       <label for="Importe">Nombre</label> <input type="text" id="Importe" name="Importe"
						placeholder="NombreClinet"
						value=<%=Client.getNombre() != null ? Client.getNombre() : ""%>
						readonly>
						<label for="Importe">Apellido</label> <input type="text" id="Importe" name="Importe"
						placeholder="NombreClinet"
						value=<%=Client.getApellido() != null ? Client.getApellido() : ""%>
						readonly>
  				<label for="Importe">Motivos De la solicitud</label>
  				<p><%
    out.print(IDPRestam.getDetalleSolicitudPt());
%></p>
				</form>

			</div>
		</div>
	</div>
</div>
<script>
    function aprobarPrestamo() {
        alert("El prestamo a sido aprobado con éxito por usted");
        return true; 
    }
</script>


	<script src="JS/MenuAdm.js"></script>
</body>
</html>
