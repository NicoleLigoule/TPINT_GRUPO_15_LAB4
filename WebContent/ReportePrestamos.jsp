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
	href="${pageContext.request.contextPath}/Css/ReportePrestamo.css">
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
	<%double PorcentajesAprobados =(double)request.getAttribute("POrcentajesAporbados"); %>	
	<%double RendimietnosAprobados =(double)request.getAttribute("RendimientosAporbados"); %>	
	<%double RendimietnosMEnsulaAprobados =(double)request.getAttribute("RendimientosMEnsualAporbados"); %>
	<%double RendimientosAnualPagado =(double)request.getAttribute("RendimientosAnualPagado"); %>	
	
   <div class="Mastercontainer">
		<div class="content">
			<div class="form-card">
				<h2>Porcentaje De Prestamos Aprobados</h2>
  				<p style="border: 2px solid black; padding: 8px;"><%
    out.print(PorcentajesAprobados+"%");
				%></p>
				

			</div>
		</div>
		<div class="content">
			<div class="form-card">
				<h2>Rendimientos De Los Pagos Aprobados Este Año</h2>
				<p style="border: 2px solid black; padding: 8px;"><%
    out.print("$ "+RendimietnosAprobados);
				%></p>
				


			</div>
		</div>
		<div class="content">
			<div class="form-card">
				<h2>Rendimientos Cobrados En Este Mes</h2>
  				<p style="border: 2px solid black; padding: 8px;"><%
    out.print("$ "+RendimietnosMEnsulaAprobados);
				%></p>


			</div>
		</div>
		<div class="content">
			<div class="form-card">
				<h2>Rendimientos Cobrados En Este Año</h2>
  				<p style="border: 2px solid black; padding: 8px;"><%
    out.print("$ "+RendimientosAnualPagado);
				%></p>

				
			</div>
		</div>
	</div>
</div>
	<div class="button-group">
                        <button type="button" class="cancel-button" onclick="window.history.back();">Volver</button>
                       
                    </div>
	<script src="JS/MenuAdm.js"></script>
</body>
</html>
