<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Confirmar Transferencia</title>
    <link rel="stylesheet" href="Css/Transferencia.css">
</head>
<body>
    <nav class="navbar">
        <button class="hamburger" onclick="toggleSidebar()">
            <div class="line"></div>
            <div class="line"></div>
            <div class="line"></div>
        </button>
        <a href="Login.jsp">
            <img src="img/png_logo.png" class="img_logo" alt="Logo UTN">
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

        <div class="form-container">
            <h2>Confirma que todo esté bien</h2>
            <div class="confirmation-details">
                <p><strong>CBU Destino:</strong> ${param.cbu}</p>
                <p><strong>Importe:</strong> $${param.importe}</p>
            </div>
            <form action="transferenciaExitosa.jsp" method="post">
                <input type="hidden" name="cbu" value="${param.cbu}">
                <input type="hidden" name="importe" value="${param.importe}">
                <button type="submit" class="btn">Confirmar Transferencia</button>
            </form>
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
