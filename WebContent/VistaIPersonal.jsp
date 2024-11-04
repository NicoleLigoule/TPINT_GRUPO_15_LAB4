<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link href="Css/VistaIPersonal.css" type="text/css" rel="stylesheet"/>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Vista Informaci�n Personal</title>
</head>
<body>
    <!-- Barra de navegaci�n -->
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

        <section class="content">
            <!-- T�tulo principal encima de la informaci�n del usuario -->
            <h1 class="main-title">Informaci�n Personal</h1>
            <div class="info-container">
                <div class="personal-info">
                    <h2>Datos del Usuario</h2>
                    <p><strong>Nombre:</strong> Nicole Ligoule</p>
                    <p><strong>Correo:</strong> nicole@gmail.com</p>
                    <p><strong>Tel�fono:</strong> +54 123 456789</p>
                    <p><strong>Direcci�n:</strong> Calle Lila 123, Pilar</p>
                </div>
                <div style="width: 1px; height: 150px; background-color: #ccc; margin: 0 20px;"></div>
                <div class="account-info">
                    <h2>Cuenta Corriente</h2>
                    <p><strong>N�mero de Cuenta:</strong> 1234567890</p>
                    <p><strong>Saldo:</strong> $2,039,000.00</p>
                </div>
                <div class="account-info">
                    <h2>Caja de Ahorro</h2>
                    <p><strong>N�mero de Cuenta:</strong> 9876543210</p>
                    <p><strong>Saldo:</strong> $578,000.00</p>
                </div>
            </div>
        </section>
    </div>

    <script src="JS/MenuAdm.js"></script>
</body>
</html>
