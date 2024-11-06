<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Transferencia</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/Css/Transferencia.css">
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
        <span class="username">USUARIO XXXX</span>
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
                <h2>¿Cuánto vas a transferir?</h2>
               
                <form action="confirmarTransferencia.jsp" method="post">
                    <div class="amount-input">
                        <input type="text" id="importe" name="importe" required pattern="\d+" placeholder="$0" maxlength="10">
                    </div>
                    <p class="available-balance">Saldo disponible: <strong>$1000</strong></p>
                    <button type="submit" class="confirm-button">Continuar</button>
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
