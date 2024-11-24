<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page import="entidades.Usuario"%>
<%@ page import="daoImpl.TransferenciaDaoImpl"%>
<%@ page import="java.util.List"%>
<%@ page import="entidades.Transferencias"%>
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
    <title>Realizar Transferencia</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/Transferencia.css">
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
                <li class="menu-item"><a href="#" onclick="toggleSubmenu(event)">Transferencias</a></li>
                <li class="menu-item"><a href="#" onclick="toggleSubmenu(event)">Solicitudes de préstamos</a></li>
                <li class="menu-item"><a href="#" onclick="toggleSubmenu(event)">Pago de préstamos</a></li>
                <li class="menu-item"><a href="#" onclick="toggleSubmenu(event)">Información personal</a></li>
            </ul>
        </aside>

        <div class="content">
            <div class="form-card">
                <h2>Realizar Transferencia</h2>
                <p>Completa los datos para realizar una transferencia</p>
                <!-- Cambiar acción a Servlet para procesar la transferencia -->
                <form action="servletTransferencia" method="post">

    <label for="cuentaOrigen">Cuenta Origen</label>
    <input type="text" id="cuentaOrigen" name="cuentaOrigen" required pattern="\d+" placeholder="Cuenta Origen">

    <label for="cuentaDestino">Cuenta Destino</label>
    <input type="text" id="cuentaDestino" name="cuentaDestino" required pattern="\d+" placeholder="Cuenta Destino">

    <label for="monto">Monto</label>
    <input type="text" id="monto" name="monto" required pattern="\d+(\.\d{1,2})?" placeholder="Monto (ejemplo: 1000.50)">

    <label for="detalle">Detalle</label>
    <input type="text" id="detalle" name="detalle" placeholder="Detalle de la transferencia">

    <button type="submit" class="submit-button" name="RealizarBtn">Realizar Transferencia</button>
</form>

            </div>
        </div>
    </div>

    <script src="JS/MenuAdm.js"></script>
    <script>
        function toggleSidebar() {
            const sidebar = document.getElementById('sidebar');
            sidebar.classList.toggle('active');
        }

        function toggleSubmenu(event) {
            const menuItem = event.target.parentElement;
            menuItem.classList.toggle('active');
        }
    </script>
</body>
</html>
