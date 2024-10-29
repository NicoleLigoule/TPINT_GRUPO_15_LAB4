<!--<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>-->
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
                <li><a href="#">Clientes</a></li>
                <li><a href="#">Cuentas</a></li>
                <li><a href="#">Transacciones</a></li>
                <li><a href="#">Reportes</a></li>
                <li><a href="#">Ajustes</a></li>
            </ul>
        </aside>

        <div class="content">
           
           <div>
           		<h2>Listado de Cuentas</h2>
           		<br>
           		
           <!-- TABLA EJEMPLO -->
     <div>
    <table id="table_id" class="display">
        <thead>
            <tr>
                <th>Número de Cuenta</th>
                <th>CUIL</th>
                <th>Fecha de Creación</th>
                <th>ID Tipo Cuenta</th>
                <th>CBU</th>
                <th>Saldo</th>
                <th>Estado</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>123456789</td>
                <td>20-12345678-9</td>
                <td>2021-05-14</td>
                <td>1</td>
                <td>1234567890123456789012</td>
                <td>50000.00</td>
                <td>Activo</td>
            </tr>
            <tr>
                <td>234567890</td>
                <td>27-23456789-0</td>
                <td>2020-09-21</td>
                <td>2</td>
                <td>2345678901234567890123</td>
                <td>75000.00</td>
                <td>Activo</td>
            </tr>
            <tr>
                <td>345678901</td>
                <td>23-34567890-1</td>
                <td>2019-02-11</td>
                <td>3</td>
                <td>3456789012345678901234</td>
                <td>120000.00</td>
                <td>Inactivo</td>
            </tr>
            <!-- Agrega más filas según sea necesario -->
        </tbody>
    </table>
</div>





    <script>
        function toggleSidebar() {
            const sidebar = document.getElementById('sidebar');
            sidebar.classList.toggle('active');
        }
    </script>
    
    
    
</body>
</html>