<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <link href="Css/InicioCli.css" type="text/css" rel="stylesheet"/>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Cuentas</title>
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
        <h1>MENU PRINCIPAL</h1>
        <span class="username">USUARIO XXXX</span>
    </nav>
    
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
        
        <div class="content">
            <div class="center-content">
                <div class="form-card">
                    <form>
                        <label for="NC">NUMERO DE CUENTA: XXXX</label>
                        <label for="TC">TIPO DE CUENTA</label>
                        <label for="TC">SALDO: XXX$</label>
                    </form>
                </div>
                
                <div class="form-card">
                    <form>
                        <label for="NC">NUMERO DE CUENTA: XXXX</label>
                        <label for="TC">TIPO DE CUENTA</label>
                        <label for="TC">SALDO: XXX$</label>
                    </form>
                </div>
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
    </script>
</body>
</html>
