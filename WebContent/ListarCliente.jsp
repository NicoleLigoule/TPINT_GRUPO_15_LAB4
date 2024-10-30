<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <link href="Css/AgregarCli.css" type="text/css" rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css"/>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Listar Clientes</title>    
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>

 <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #000;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>

  <script type="text/javascript">
  $(document).ready(function () {
	    $('#table_id').DataTable({
	        "language": {
	            "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json"
	        }, 
	        "aLengthMenu": [
	            [5, 25, 50, 100, -1], /* Paginación */
	            [5, 25, 50, 100, "Todos"]
	        ]
	    });
	});

</script>

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
           
           <div>
           		<h2>Listado de Clientes</h2>
           		<br>
           		
           <!-- TABLA EJEMPLO -->
           <div>
           		
			    <table id="table_id" class="display">
			        <thead>
			            <tr>
			                <th>Dni</th>
			                <th>Cuil</th>
			                <th>Nombre</th>
			                <th>Apellido</th>
			                <th>Género</th>
			                <th>Nacionalidad</th>
			                <th>Fecha de Nacimiento</th>
			                <th>Dirección</th>
			                <th>Provincia</th>			                
			                <th>Localidd</th>
			                <th>Correo Eletrónico</th>
			                <th>Teléfono</th>			                
			            </tr>
			        </thead>
			        <tbody>
			            <tr>
						    <td>12345678</td>
						    <td>20-12345678-9</td>
						    <td>Juan</td>
						    <td>Pérez</td>
						    <td>Masculino</td>
						    <td>Argentina</td>
						    <td>1990-05-14</td>
						    <td>Av. Siempre Viva 742</td>
						    <td>Buenos Aires</td>
						    <td>Capital Federal</td>
						    <td>juan.perez@gmail.com</td>
						    <td>11-4567-8900</td>
						</tr>
						<tr>
						    <td>23456789</td>
						    <td>27-23456789-0</td>
						    <td>Lucía</td>
						    <td>Gómez</td>
						    <td>Femenino</td>
						    <td>Argentina</td>
						    <td>1988-09-21</td>
						    <td>Calle Falsa 123</td>
						    <td>Córdoba</td>
						    <td>Córdoba Capital</td>
						    <td>lucia.gomez@hotmail.com</td>
						    <td>351-6789-1234</td>
						</tr>
						<tr>
						    <td>34567890</td>
						    <td>23-34567890-1</td>
						    <td>Carlos</td>
						    <td>Fernández</td>
						    <td>Masculino</td>
						    <td>Argentina</td>
						    <td>1995-02-11</td>
						    <td>San Martín 456</td>
						    <td>Santa Fe</td>
						    <td>Rosario</td>
						    <td>carlos.fernandez@gmail.com</td>
						    <td>341-1234-5678</td>
						</tr>
						<tr>
						    <td>45678901</td>
						    <td>24-45678901-2</td>
						    <td>María</td>
						    <td>Lopez</td>
						    <td>Femenino</td>
						    <td>Argentina</td>
						    <td>1992-12-03</td>
						    <td>Belgrano 789</td>
						    <td>Mendoza</td>
						    <td>Mendoza Capital</td>
						    <td>maria.lopez@yahoo.com</td>
						    <td>261-9876-5432</td>
						</tr>
						<tr>
						    <td>56789012</td>
						    <td>22-56789012-3</td>
						    <td>Fernando</td>
						    <td>Sánchez</td>
						    <td>Masculino</td>
						    <td>Argentina</td>
						    <td>1985-04-17</td>
						    <td>Independencia 321</td>
						    <td>Santa Fe</td>
						    <td>Rosario</td>
						    <td>fernando.sanchez@gmail.com</td>
						    <td>387-7654-3210</td>
						</tr>
						<tr>
						    <td>67890123</td>
						    <td>21-67890123-4</td>
						    <td>Ana</td>
						    <td>Martínez</td>
						    <td>Femenino</td>
						    <td>Argentina</td>
						    <td>1998-07-29</td>
						    <td>Av. Libertador 111</td>
						    <td>San Luis</td>
						    <td>Villa Mercedes</td>
						    <td>ana.martinez@gmail.com</td>
						    <td>2657-456-789</td>
						</tr>
						<tr>
						    <td>78901234</td>
						    <td>27-78901234-5</td>
						    <td>José</td>
						    <td>Gutiérrez</td>
						    <td>Masculino</td>
						    <td>Argentina</td>
						    <td>1987-10-10</td>
						    <td>Sarmiento 987</td>
						    <td>Chaco</td>
						    <td>Resistencia</td>
						    <td>jose.gutierrez@gmail.com</td>
						    <td>362-7890-1234</td>
						</tr>
						<tr>
						    <td>89012345</td>
						    <td>20-89012345-6</td>
						    <td>Camila</td>
						    <td>Díaz</td>
						    <td>Femenino</td>
						    <td>Argentina</td>
						    <td>1993-03-08</td>
						    <td>Alsina 654</td>
						    <td>La Pampa</td>
						    <td>Santa Rosa</td>
						    <td>camila.diaz@hotmail.com</td>
						    <td>2954-5678-901</td>
						</tr>
						<tr>
						    <td>90123456</td>
						    <td>23-90123456-7</td>
						    <td>Mateo</td>
						    <td>Vega</td>
						    <td>Masculino</td>
						    <td>Argentina</td>
						    <td>2000-11-15</td>
						    <td>Alem 123</td>
						    <td>Misiones</td>
						    <td>Posadas</td>
						    <td>mateo.vega@gmail.com</td>
						    <td>376-5678-4321</td>
						</tr>
						<tr>
						    <td>01234567</td>
						    <td>24-01234567-8</td>
						    <td>Sofía</td>
						    <td>Martinez</td>
						    <td>Femenino</td>
						    <td>Argentina</td>
						    <td>1996-06-06</td>
						    <td>San Juan 1234</td>
						    <td>San Juan</td>
						    <td>San Juan Capital</td>
						    <td>sofia.paz@gmail.com</td>
						    <td>264-6789-0123</td>
						</tr>
						<tr>
						    <td>11223344</td>
						    <td>20-11223344-9</td>
						    <td>Agustín</td>
						    <td>Navarro</td>
						    <td>Masculino</td>
						    <td>Argentina</td>
						    <td>1989-09-12</td>
						    <td>Castelli 567</td>
						    <td>Entre Ríos</td>
						    <td>Paraná</td>
						    <td>agustin.navarro@gmail.com</td>
						    <td>343-9876-1234</td>
						</tr>
						<tr>
						    <td>22334455</td>
						    <td>27-22334455-0</td>
						    <td>Valentina</td>
						    <td>Molina</td>
						    <td>Femenino</td>
						    <td>Argentina</td>
						    <td>1994-12-22</td>
						    <td>Mitre 999</td>
						    <td>San Salvador</td>
						    <td>Jujuy</td>
						    <td>valentina.molina@gmail.com</td>
						    <td>388-5678-9999</td>
						</tr>
						<tr>
						    <td>33445566</td>
						    <td>22-33445566-1</td>
						    <td>Facundo</td>
						    <td>Torres</td>
						    <td>Masculino</td>
						    <td>Argentina</td>
						    <td>1991-05-31</td>
						    <td>Av. Colón 444</td>
						    <td>Córdoba</td>
						    <td>Villa Carlos Paz</td>
						    <td>facundo.torres@gmail.com</td>
						    <td>3541-7890-567</td>
						</tr>
						<tr>
						    <td>44556677</td>
						    <td>23-44556677-2</td>
						    <td>Julia</td>
						    <td>Romero</td>
						    <td>Femenino</td>
						    <td>Argentina</td>
						    <td>1997-01-19</td>
						    <td>Urquiza 321</td>
						    <td>Santa Fe</td>
						    <td>Santa Fe Capital</td>
						    <td>julia.romero@hotmail.com</td>
						    <td>342-6789-1122</td>
						</tr>
			            
			            
			        </tbody>
			    </table>
           </div>                
              
          
        </div>
    </div>




<script src="JS/MenuAdm.js"></script>
    
    
    
</body>
</html>