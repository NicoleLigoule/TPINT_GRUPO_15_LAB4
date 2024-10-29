<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link href="Css/AgregarCli.css" type="text/css" rel="stylesheet"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Editar Cliente</title>
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
                <li class="menu-item">
                  <a href="#" onclick="toggleSubmenu(event)">Clientes</a>
                    <ul class="submenu">
                        <li> <a href="AgregarCliente.jsp">Agregar Cliente</a></li>
                        <li> <a href="EliminarCliente.jsp">Baja Cliente</a></li>
                        <li> <a href="#">Editar Cliente</a></li>
                        <li> <a href="ListarCliente.jsp">Listar Cliente</a></li>
                    </ul>
                </li>
                <li class="menu-item">
                    <a href="#" onclick="toggleSubmenu(event)">Cuentas</a>
                    <ul class="submenu">
                        <li> <a href="AgregarCuenta.jsp">Agregar Cuenta</a></li>
                        <li> <a href="#">Baja Cuenta</a></li>
                        <li> <a href="#">Editar Cuenta</a></li>
                        <li> <a href="#">Listar Cuenta</a></li>
                    </ul>
                </li>
                <li class="menu-item">
                    <a href="#" onclick="toggleSubmenu(event)">Transacciones</a>
                    <!-- <ul class="submenu">
                        <li> <a href="#">Registrar Transacción</a></li>
                        <li> <a href="#">Ver Historial</a></li>
                    </ul>
                     -->
                </li>
                <li class="menu-item">
                    <a href="#" onclick="toggleSubmenu(event)">Reportes</a>
                    <!-- <ul class="submenu">
                        <li> <a href="#">Reporte de Clientes</a></li>
                        <li> <a href="#">Reporte de Cuentas</a></li>
                    </ul>
                     -->
                </li>
            </ul>
        </aside>

        <div class="content">
            <div class="form-card">
                <h2>Editar de Cliente</h2>
                <form>
                    <label for="dni">DNI</label>
                     <label for="dniCli">XXXXXXX</label>

                    <label for="cuil">CUIL</label>
                    <label for="cuilCli">XXXXXXXX</label>

                    <label for="nombre">Nombre</label>
                    <input type="text" id="nombre" name="nombre" placeholder="Nombre" required>

                    <label for="apellido">Apellido</label>
                    <input type="text" id="apellido" name="apellido" placeholder="Apellido" required>

                    <label for="genero">Género:</label>
		            <select id="genero" name="genero" required>
		            <option value="" disabled selected>Seleccione su género</option>
		            </select>

                    <label for="nacionalidad">Nacionalidad</label>
                    <select id="nacionalidad" name="nacionalidad" required>
                    <option value="" disabled selected>Seleccione su Nacionalidad</option>

                    </select>

                    <label for="fecha-nacimiento">Fecha de Nacimiento</label>
                    <input type="text" id="fecha-nacimiento" name="fecha-nacimiento" placeholder="YYYY/MM/DD" required>

                    <label for="direccion">Dirección</label>
                    <input type="text" id="direccion" name="direccion" placeholder="Dirección" required>

                    <label for="numero">Número</label>
                    <input type="text" id="numero" name="numero" placeholder="Número" required>

                    <label for="provincia">Provincia</label>
                    <select id="provincia" name="provincia" required>
                  	<option value="" disabled selected>Seleccione su Provincia</option>
                    </select>

                    <label for="localidad">Localidad</label>
                    <select id="localidad"  name="localidad"  required>
                    <option value="" disabled selected>Seleccione su Localidad</option>
                    </select>

                    <label for="telefono">Teléfono</label>
                    <input type="tel" id="telefono" name="telefono" placeholder="Teléfono" required>

                    <label for="email">Correo Electrónico</label>
                    <input type="email" id="email" name="email" placeholder="Correo Electrónico" required>

                    <div class="button-group">
                        <button type="button" class="cancel-button">Cancelar</button>
                        <button type="submit" class="submit-button">Guardar</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

<script src="JS/MenuAdm.js"></script>
</body>
</html>