<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entidades.Prestamo" %>
<%@ page import="entidades.CuotasXPrestamo" %>
<%@ page import="entidades.Usuario" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="javafx.util.Pair" %>
<%@ page import="java.math.BigDecimal" %>

<%
    // Obtención del usuario de la sesión
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
    <title>Seleccionar Préstamo</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/PagoPrestamo_Seleccionar.css">
</head>
<body>
    <nav class="navbar">
        <a href="${pageContext.request.contextPath}/Login.jsp"> 
            <img src="${pageContext.request.contextPath}/img/png_logo.png" class="img_logo" alt="Logo UTN">
        </a> 
        <span class="username"><%= usuario.getUsuarioUs() %></span>
    </nav>

    <div class="main-container">
        <jsp:include page="SubMenu_Cliente.jsp" />

        <div class="content">
            <div class="form-card">
                <h2>Seleccionar Préstamo</h2>
                <p>Seleccione uno de los préstamos disponibles para realizar el pago:</p>   
				<br>
                <div class="loan-cards-container">
                    <%
                        // Recuperamos la lista de préstamos del request
                        List<Pair<Integer, ArrayList<Prestamo>>> prestamosXCuenta = (List<Pair<Integer, ArrayList<Prestamo>>>) request.getAttribute("PrestamosXCuenta");
                        if (prestamosXCuenta != null && !prestamosXCuenta.isEmpty()) {
                            for (Pair<Integer, ArrayList<Prestamo>> pair : prestamosXCuenta) {
                                ArrayList<Prestamo> prestamos = pair.getValue();
                                for (Prestamo prestamo : prestamos) {
                                    int cuotasImpagas = 0;
                                    float totalDebt = 0;

                                    // Calcular las cuotas impagas y el total de la deuda
                                    for (CuotasXPrestamo cuota : prestamo.getCuotas()) {
                                    	
                                    	if (!cuota.getPagada()) {
                                            cuotasImpagas++;
                                            totalDebt += prestamo.getDetalle().getImporteXCuotasDt().floatValue();
                                        }
                                    }
                                    
                                    if (cuotasImpagas == 0) {
                                    continue;
                                    }
                    %>
                                    <!-- Generar tarjeta de préstamo -->
                                    <div class="loan-card" onclick="redirigirAPago(<%= prestamo.getIdPrestamoPt() %>)">
                                        <h3>Préstamo #<%= prestamo.getIdPrestamoPt() %></h3>
                                        <p><strong>Número de Cuenta:</strong> <%= prestamo.getNumeroDeCuentaCuPt() %></p>
                                        <p><strong>Monto Total:</strong> $<%= String.format("%.2f", totalDebt) %></p>
                                        <p><strong>Cuotas Pendientes:</strong> <%= cuotasImpagas %></p>
                                        <p><strong>Precio por Cuota:</strong> $<%= prestamo.getDetalle().getImporteXCuotasDt() %></p>
                                    </div>
                    <%
                                
                                    }
                            }
                        } else {
                    %>
                            <p>No hay préstamos disponibles para seleccionar.</p>
                    <%
                        }
                    %>
                </div>
                <div style="margin-top: 30px;"></div>
                <button type="button" class="submit-button" onclick="location='/TPINT_GRUPO_15_LAB4/servletInformacionCliente'">Volver al Menu Principal</button>
	 </div>
        </div>
    </div>

    <script src="${pageContext.request.contextPath}/JS/MenuAdm.js"></script>
    <script>
        function redirigirAPago(idPrestamo) {
            // Redirige a la página PagarPrestamo.jsp con el ID del préstamo seleccionado
            window.location.href = "ServletPagoPrestamo?idPrestamo=" + idPrestamo;
        }
    </script>
</body>
</html>