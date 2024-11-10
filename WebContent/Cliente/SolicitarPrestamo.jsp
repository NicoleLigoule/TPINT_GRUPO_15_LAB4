<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SolicitarPrestamo</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/Css/SolicitarPrestamo.css">
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
    </nav><span class="username">USUARIO XXXX</span>
    </nav>

    <div class="main-container">
        <aside class="sidebar" id="sidebar">
            <ul>
                <li class="menu-item">
                    <a href="#" onclick="toggleSubmenu(event)">Transferencias</a>
                </li>
                
               <li class="menu-item">
                    <a href="#" onclick="toggleSubmenu(event)">Solicitudes de prestamos</a>
                </li>
                
                <li class="menu-item">
                    <a href="#" onclick="toggleSubmenu(event)">Pago de prestamos</a>
                </li>
                <li class="menu-item">
                    <a href="#" onclick="toggleSubmenu(event)">Informacion personal</a>
                </li>
            </ul>
        </aside>

        	 <div class="content">
            <div class="form-card">
                <h2>Solicitud de Préstamo</h2>
                <form action="tu_archivo_backend.php" method="post">
                    <label for="numero_cuenta">Número de Cuenta</label>
                    <input type="text" id="numero_cuenta" name="numero_cuenta" required class="form-input">

                    <!-- Fecha de petición como input deshabilitado -->
                    <label for="fecha_peticion">Fecha de Petición</label>
                    <input type="text" id="fecha_peticion" name="fecha_peticion" disabled class="form-input">

                    <label for="importe_solicitado">Importe Solicitado</label>
                    <input type="number" id="importe_solicitado" name="importe_solicitado" required step="0.01" class="form-input">

                    <!-- Plazo de Pago como un Dropdown (DDL) -->
                    <label for="plazo_pago">Plazo de Pago (en meses)</label>
                    <select id="plazo_pago" name="plazo_pago" required class="form-input">
                        <option value="6">6 meses</option>
                        <option value="12">12 meses</option>
                        <option value="18">18 meses</option>
                        <option value="24">24 meses</option>
                        <option value="36">36 meses</option>
                    </select>

                    <div class="button-group">
                        <button type="reset" class="cancel-button">Cancelar</button>
                        <button type="submit" class="submit-button">Enviar Solicitud</button>
                    </div>
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
        const menuItem = event.currentTarget.parentElement; 
        menuItem.classList.toggle('active'); 
    }
        window.onload = function() {
            const fechaElement = document.getElementById('fecha_peticion');
            const fechaActual = new Date().toLocaleDateString();
            fechaElement.value = fechaActual;
        };
    </script>
</body>
</html>
