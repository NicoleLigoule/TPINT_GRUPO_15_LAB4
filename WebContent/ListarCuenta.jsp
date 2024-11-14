<%@ page import="java.util.List" %>
<%@ page import="entidades.Cuenta" %>
<%@ page import="entidades.Usuario" %>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("Login.jsp");
        return;
    }
%>


<!DOCTYPE html>
<html lang="es">
<head>
    <link href="${pageContext.request.contextPath}/Css/EditarCun.css" type="text/css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/Css/EditarCun.css" type="text/css" rel="stylesheet"/>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Listar Cuentas</title>    
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
	<link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css"/>
 
    <style>
	<link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css"/>
 
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
        
        .mensaje-error {
		    text-align: center;
		    margin-top: 20px;
		}

		.mensaje-sin-cuentas {
		    text-align: center; 
		    margin-top: 10px; 
		    font-size: 1.2em; 
		    color: #333; 
		}

        
        .mensaje-error {
		    text-align: center;
		    margin-top: 20px;
		}

		.mensaje-sin-cuentas {
		    text-align: center; 
		    margin-top: 10px; 
		    font-size: 1.2em; 
		    color: #333; 
		}

    </style>

    <script type="text/javascript">
    $(document).ready(function () {
    <script type="text/javascript">
    $(document).ready(function () {
	    $('#table_id').DataTable({
	        "language": {
	            "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json"
	        }, 
	        "aLengthMenu": [
	            [5, 25, 50, 100, -1],
	            [5, 25, 50, 100, -1],
	            [5, 25, 50, 100, "Todos"]
	        ]
	    });
	});
    </script>
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
        <span class="username"><%= usuario.getUsuarioUs() != null ? usuario.getUsuarioUs() : "Usuario no encontrado" %></span>
        <span class="username"><%= usuario.getUsuarioUs() != null ? usuario.getUsuarioUs() : "Usuario no encontrado" %></span>
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
                </li>
                <li class="menu-item">
                    <a href="#" onclick="toggleSubmenu(event)">Reportes</a>
                </li>
            </ul>
        </aside>

        <div class="content">
   <h2>Listado de Cuentas</h2>

   
   <div class="mensaje-error">
       <%
           List<Cuenta> listaCuenta = (List<Cuenta>) request.getAttribute("listaCuenta");
           if (listaCuenta != null && !listaCuenta.isEmpty()) {
       %>
       
       <table id="table_id" class="display">
           <thead>
               <tr>
                   <th>N�mero de Cuenta</th>
                   <th>CUIL</th>
                   <th>Fecha de Creaci�n</th>
                   <th>ID Tipo Cuenta</th>
                   <th>CBU</th>
                   <th>Saldo</th>
                   <th>Estado</th>
               </tr>
           </thead>
           <tbody>
               <% for (Cuenta cuenta : listaCuenta) { %>
               <tr>
                   <td><%= cuenta.getNumeroDeCuentaCu() %></td>
                   <td><%= cuenta.getCuilCliCu() %></td>
                   <td><%= cuenta.getFechaCreacionCu() %></td>
                   <td><%= cuenta.getIdTipoCuenta() %></td>
                   <td><%= cuenta.getCbuCu() %></td>
                   <td><%= cuenta.getSaldoCu() %></td>
                   <td><%= cuenta.isEstadoCu() ? "Activa" : "Inactiva" %></td>
               </tr>
               <% } %>
           </tbody>
       </table>
       <% } else { %>
          
           <p class="mensaje-sin-cuentas">No se encontraron Cuentas.</p>
       <% } %>
   </div>
</div>
</div>

    <script src="JS/MenuAdm.js"></script>
    <script src="JS/MenuAdm.js"></script>
</body>
</html>

