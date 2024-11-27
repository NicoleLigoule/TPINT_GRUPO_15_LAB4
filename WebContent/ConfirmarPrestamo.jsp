<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
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
    <title>Confirmación de Prestamo</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/SolicitarPrestamo.css">
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
		</a> <span class="username"><%= usuario.getUsuarioUs() %></span>
	</nav>
	<jsp:include page="SubMenu_Cliente.jsp" />

    <div class="main-container">
        <div class="content">
            <div class="form-card">
                <h2>Confirmar Solicitud De Prestamo</h2>

                <form action="ServletProcesarPrestamo" method="post">
                    <label for="cuenta_destino">Cuenta Destino</label>
                    <input type="text" id="cuenta_destino" name="cuenta_destino" value="<%= request.getAttribute("cuenta_destino") != null ? request.getAttribute("cuenta_destino") : request.getParameter("cuenta_destino") %>" readonly>

                    <label for="importe_solicitado">Monto Solicitado</label>
                    <input type="text" id="importe_solicitado" name="importe_solicitado" value="<%= request.getAttribute("importe_solicitado") != null ? request.getAttribute("importe_solicitado") : request.getParameter("importe_solicitado")%>" readonly>

                    <label for="monto_con_interes">Monto Con Interés</label>
                    <input type="text" id="monto_con_interes" name="monto_con_interes" value="<%= request.getAttribute("monto_con_interes") != null ? request.getAttribute("monto_con_interes") : request.getParameter("monto_con_interes")%>" readonly>

                    <label for="plazo_pago">Cantidad De Cuotas</label>
                    <input type="text" id="plazo_pago" name="plazo_pago" value="<%= request.getAttribute("plazo_pago") != null ? request.getAttribute("plazo_pago") : request.getParameter("plazo_pago")%>" readonly>
                    
					

                    <label for="monto_por_cuota">Monto Por Cuota</label>
                    <input type="text" id="monto_por_cuota" name="monto_por_cuota" value="<%= request.getAttribute("monto_por_cuota") != null ? request.getAttribute("monto_por_cuota") : request.getParameter("monto_por_cuota")%>" readonly>
   					
   				    <input type="hidden" name="plazo_pago_IXM" value="<%= request.getAttribute("plazo_pago_IXM") %>">
				    <input type="hidden" name="motivo" value="<%= request.getAttribute("motivo") %>">
			
                    <div class="button-group">
                        <button type="submit" class="cancel-button"name="Volver">Volver</button>
                        <button type="submit" class="submit-button" name="confirmar">Confirmar</button>
                    </div>
 				</form>


            </div>
        </div>
    </div>
<script src="JS/MenuAdm.js"></script>
</body>
</html>
