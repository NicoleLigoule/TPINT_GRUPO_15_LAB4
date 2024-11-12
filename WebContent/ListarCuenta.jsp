<%@ page import="java.util.List" %>
<%@ page import="entidades.Cuenta" %>

<!--<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>-->
<!DOCTYPE html>
<html lang="es">
<head>
    <link href="${pageContext.request.contextPath}/Css/EditarCun.css" type="text/css" rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css"/>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Listar Cuentas</title>    
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
        <a href="${pageContext.request.contextPath}/Login.jsp">
            <img src="${pageContext.request.contextPath}/img/png_logo.png" class="img_logo" alt="Logo UTN">
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
           		<h2>Listado de Cuentas</h2>
    <%
        
        List<Cuenta> listaCuenta = (List<Cuenta>) request.getAttribute("listaCuenta");
        if (listaCuenta != null && !listaCuenta.isEmpty()) {
    %>
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
        	<% 
               for (Cuenta cuenta : listaCuenta) {
             %>
            <tr>
				<td><%= cuenta.getNumeroDeCuentaCu() %></td>
                <td><%= cuenta.getCuilCliCu() %></td>
                <td><%= cuenta.getFechaCreacionCu() %></td>
                <td><%= cuenta.getIdTipoCuenta() %></td>
                <td><%= cuenta.getCbuCu() %></td> 
                <td><%= cuenta.getSaldoCu() %></td>
                <td><%= cuenta.isEstadoCu()? "Activa" : "Inactiva" %></td>
            </tr>
			<% } %>
        </tbody>
    </table>
    <% 
        } else {
            out.println("<p>No se encontraron Cuentas.</p>");
        }
    %>
</div>





<script src="JS/MenuAdm.js"></script>
    
    
    
</body>
</html>