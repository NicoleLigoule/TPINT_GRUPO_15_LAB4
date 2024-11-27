<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="entidades.Usuario"%>
<%
    // Obtener el usuario desde la sesión
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("Login.jsp");
        return;
    }

    // Obtener los valores de CBU, importe, fecha y hora, y detalle de la sesión
    String cbuDestino = (String) session.getAttribute("cbuDestino");
    String importe = (String) session.getAttribute("importe");
    String fechaHoraTransferencia = (String) session.getAttribute("fechaHoraTransferencia");
    String detalle = (String) session.getAttribute("detalle");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Transferencia</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/Transferencia.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    
</head>
<body>
    <nav class="navbar">
        <button class="hamburger" onclick="toggleSidebar()">
            <div class="line"></div>
            <div class="line"></div>
            <div class="line"></div>
        </button>
        <a href="${pageContext.request.contextPath}/Login.jsp">
            <img src="${pageContext.request.contextPath}/img/png_logo.png" class="img_logo" alt="Logo UTN">
        </a>
        <span class="username"><%= usuario.getUsuarioUs() %></span>
    </nav>

    <div class="main-container">
        <jsp:include page="SubMenu_Cliente.jsp" />
        
        <!-- Card de transferencia -->
        <div class="content">
            <div class="form-card">
                <h2>Transferencia Completada</h2>
                <p>La transferencia se realizó con éxito.</p>
                <p>
                    <strong>CBU Destino:</strong> <%= cbuDestino %>
                </p>
                <p>
                    <strong>Importe Transferido:</strong> $<%= importe %>
                </p>
                <p>
                    <strong>Fecha y Hora de Transferencia:</strong> <%= fechaHoraTransferencia %>
                </p>
                <p>
                    <strong>Detalle:</strong> <%= detalle %>
                </p>
					<div class="print-button-container">
                <button class="print-button" onclick="window.print()">
                    <i class="fas fa-print"></i> Imprimir
                </button>
            </div>
            
        
                <div style="margin-top: 30px;"></div>
                <button type="button" class="submit-button" onclick="location='/TPINT_GRUPO_15_LAB4/servletInformacionCliente'">Volver al Menú Principal</button>
            </div>
        </div>

    </div>

    <script src="JS/MenuAdm.js"></script>
    <script>
        function toggleSidebar() {
            const sidebar = document.getElementById('sidebar');
            sidebar.classList.toggle('active');
        }
    </script>
</body>
</html>
