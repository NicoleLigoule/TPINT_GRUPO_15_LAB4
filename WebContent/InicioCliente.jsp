<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
                        <li> <a href="EditarCliente.jsp">Editar Cliente</a></li>
                        <li> <a href="ListarCliente.jsp">Listar Cliente</a></li>
                    </ul>
                </li>
                <li class="menu-item">
                    <a href="#" onclick="toggleSubmenu(event)">Cuentas</a>
                    <ul class="submenu">
                        <li> <a href="AgregarCuenta.jsp">Agregar Cuenta</a></li>
                        <li> <a href="EliminarCuenta.jsp">Baja Cuenta</a></li>
                        <li> <a href="EditarCuenta.jsp">Editar Cuenta</a></li>
                        <li> <a href="ListarCuenta.jsp">Listar Cuenta</a></li>
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
 

 <div class="Mastercontainer">

        <div class="content">
        <a href="EditarCliente.jsp" class="Content-cuenta">
            <div class="form-card">
                
                <form>

					<label for="NC">NUMERO DE CUENTA:XXXX</label>
					<label for="TC">  TIPO DE CUENTA</label>
					 <br>
					<label for="TC">SALDO:XXX$</label>
                    
				</form>
				 </div>
				 </a>
				 </div>
 	 <div class="content">
		<a href="EditarCliente.jsp" class="Content-cuenta">
		            <div class="form-card">
		                
		                <form>
		
							<label for="NC">NUMERO DE CUENTA:XXXX</label>
							<label for="TC">  TIPO DE CUENTA</label>
							 <br>
							<label for="TC">SALDO:XXX$</label>
		                    
						</form>
						 </div>
						 </a>
        </div>
    </div>
</div>

<script src="JS/MenuAdm.js"></script>
</body>
</html>