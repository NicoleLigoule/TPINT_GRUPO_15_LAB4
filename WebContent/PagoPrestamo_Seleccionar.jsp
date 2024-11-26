<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entidades.Prestamo" %>
<%@ page import="entidades.CuotasXPrestamo" %>
<%@ page import="entidades.Usuario" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="javafx.util.Pair" %>

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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/PagoPrestamo.css">
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

                <%
                    // Recuperamos la lista de préstamos del request
                    List<Pair<Integer, ArrayList<Prestamo>>> prestamosXCuenta = (List<Pair<Integer, ArrayList<Prestamo>>>) request.getAttribute("prestamosXCuenta");
                    if (prestamosXCuenta != null && !prestamosXCuenta.isEmpty()) {
                        for (Pair<Integer, ArrayList<Prestamo>> pair : prestamosXCuenta) {
                            ArrayList<Prestamo> prestamos = pair.getValue();
                            for (Prestamo prestamo : prestamos) {
                            	
                            	long cuotasPendientes = prestamo.getCuotas().stream().filter(cuota -> !cuota.getPagada()).count(); 
                            	
                %>
                
					
					
					<p><strong>Cuotas Pendientes:</strong> <%= cuotasPendientes %></p>

                            <div class="loan-card" onclick="redirigirAPago(<%= prestamo.getIdPrestamoPt() %>)">
                                <h2>Préstamo #<%= prestamo.getIdPrestamoPt() %></h2>
                                <p><strong>Número de Cuenta:</strong> <%= prestamo.getNumeroDeCuentaCuPt() %></p>
                                <p><strong>Monto Total:</strong> $<%= prestamo.getMontoRestante() %></p>
                                <p><strong>Cuotas Pendientes:</strong> <%=cuotasPendientes %> </p>
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

                <p>${mensaje}</p>
                <a href="PagoPrestamo.jsp">Volver</a>
            </div>
        </div>
    </div>

    <script src="JS/MenuAdm.js"></script>
    <script>
        function redirigirAPago(idPrestamo) {
            window.location.href = "PagoPrestamo.jsp?idPrestamo=" + idPrestamo;
        }
    </script>
</body>
</html>
