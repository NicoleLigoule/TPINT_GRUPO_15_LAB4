<!DOCTYPE html>
<html lang="es">
<head>
    <link href="Css/AgregarCli.css" type="text/css" rel="stylesheet"/>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Alta de Cliente</title>
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
                        <li> <a href="#">Registrar Transacci�n</a></li>
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
                <h2>Alta de Cliente</h2>
                <form>
                    <label for="dni">DNI</label>
                    <input type="text" id="dni" name="dni" placeholder="DNI" required>

                    <label for="cuil">CUIL</label>
                    <input type="text" id="cuil" name="cuil" placeholder="CUIL" required>

                    <label for="nombre">Nombre</label>
                    <input type="text" id="nombre" name="nombre" placeholder="Nombre" required>

                    <label for="apellido">Apellido</label>
                    <input type="text" id="apellido" name="apellido" placeholder="Apellido" required>

                    <label for="genero">G�nero:</label>
		            <select id="genero" name="genero" required>
		            <option value="" disabled selected>Seleccione su g�nero</option>
		            </select>

                    <label for="nacionalidad">Nacionalidad</label>
                    <select id="nacionalidad" name="nacionalidad" required>
                    <option value="" disabled selected>Seleccione su Nacionalidad</option>

                    </select>

                    <label for="fecha-nacimiento">Fecha de Nacimiento</label>
                    <input type="text" id="fecha-nacimiento" name="fecha-nacimiento" placeholder="YYYY/MM/DD" required>

                    <label for="direccion">Direcci�n</label>
                    <input type="text" id="direccion" name="direccion" placeholder="Direcci�n" required>

                    <label for="numero">N�mero</label>
                    <input type="text" id="numero" name="numero" placeholder="N�mero" required>

                    <label for="provincia">Provincia</label>
                    <select id="provincia" name="provincia" required>
                  	<option value="" disabled selected>Seleccione su Provincia</option>
                    </select>

                    <label for="localidad">Localidad</label>
                    <select id="localidad"  name="localidad"  required>
                    <option value="" disabled selected>Seleccione su Localidad</option>
                    </select>

                    <label for="telefono">Tel�fono</label>
                    <input type="tel" id="telefono" name="telefono" placeholder="Tel�fono" required>

                    <label for="email">Correo Electr�nico</label>
                    <input type="email" id="email" name="email" placeholder="Correo Electr�nico" required>

                    <div class="button-group">
                        <button type="button" class="cancel-button">Cancelar</button>
                        <button type="submit" class="submit-button">Agregar</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

<script src="JS/MenuAdm.js"></script>
</body>
</html>