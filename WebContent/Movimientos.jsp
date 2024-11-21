<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="entidades.Usuario"%>
<%@ page import="entidades.Movimiento"%>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("Login.jsp");
        return;
    }

    List<Movimiento> listaMovimientos = (List<Movimiento>) request.getAttribute("listaMovimientos");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Movimientos</title>
   <link href="Css/Movimientos.css" type="text/css" rel="stylesheet"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
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
        <aside class="sidebar" id="sidebar">
            <ul>
                <li><a href="#">Transferencias</a></li>
                <li><a href="#">Solicitudes de préstamos</a></li>
                <li><a href="#">Pago de préstamos</a></li>
                <li><a href="#">Información personal</a></li>
            </ul>
        </aside>

        <section class="content">
    <div class="movements-list">
        <h3>Movimientos</h3>

        <% 
            if (listaMovimientos != null && !listaMovimientos.isEmpty()) {
        %>
            <div class="movement-list">
                <% for (Movimiento movimiento : listaMovimientos) { %>
                    <div class="movement-item">
                        <h4>ID Movimiento: <%= movimiento.getIdMovimientoMov() %></h4>
                        <p><strong>Fecha:</strong> <%= movimiento.getFechaMovimientoMov() %></p>
                        <p><strong>Detalle:</strong> <%= movimiento.getDetalleMov() %></p>
                        <p><strong>Importe:</strong> <%= movimiento.getImporteMov() %></p>
                        <p><strong>Tipo Movimiento:</strong> <%= movimiento.getIdTipoMovTMMov() %></p>
                    </div>
                <% } %>
            </div>
        <% 
            } else {
        %>
            <p>No hay movimientos disponibles.</p>
        <% } %>
    </div>

    <!-- Paginación -->
    <div class="pagination">
        <span>1</span>
        <a href="#">2</a>
        <a href="#">3</a>
        <a href="#">Siguiente ></a>
    </div>
</section>

    </div>

    <script src="JS/MenuAdm.js"></script>
</body>
</html>
