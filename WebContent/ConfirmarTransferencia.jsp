<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Confirmar Transferencia</title>
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
        <span class="username">${sessionScope.usuario.usuarioUs}</span>
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
                <h2>Confirmar Transferencia</h2>
                <p>Verifica los datos antes de confirmar la transferencia</p>
                <form action="servletConfirmarTransferencia" method="post">
                    <div class="form-group">
                        <label for="cuentaOrigen">Cuenta Origen:</label>
                        <input type="text" id="cuentaOrigen" name="cuentaOrigen" value="${requestScope.cuentaOrigen}" readonly>
                    </div>
                    <div class="form-group">
                        <label for="cbuDestino">Cuenta Destino:</label>
                        <input type="text" id="cbuDestino" name="cbuDestino" value="${requestScope.cbuDestino}" readonly>
                    </div>
                    <div class="form-group">
                        <label for="importe">Monto:</label>
                        <input type="number" id="importe" name="importe" value="${importe}" readonly>
                        
                    </div>
                    <div class="form-group">
                        <label for="detalle">Detalle:</label>
                        <input type="text" id="detalle" name="detalle" value="${requestScope.detalle}" readonly>
                    </div>
                   <button type="submit" class="btn" name="ConfirmarBtn">Confirmar</button>
                    <button type="button" class="btn" onclick="window.location.href='Transferencia.jsp'">Cancelar</button>
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
