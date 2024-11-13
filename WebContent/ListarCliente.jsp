<%@ page import="java.util.List" %>
<%@ page import="entidades.Cliente" %>
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
    <meta charset="UTF-8">
    <title>Listar Clientes</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Css/ListarCli.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css"/>

    <style>
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
            font-size: 14px;
        }

        th, td {
            padding: 12px;
            text-align: left;
            border: 1px solid #ddd;
        }

        th {
            background-color: #f2f2f2;
            color: #333;
            font-weight: bold;
        }

        tr:hover {
            background-color: #f5f5f5;
        }

        .mensaje-sin-cuentas {
            text-align: center;
            margin-top: 10px;
            font-size: 1.2em;
            color: #333;
        }

        @media (max-width: 768px) {
            table {
                width: 100%;
                font-size: 12px;
            }
        }
    </style>
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
        <span class="username"><%= usuario.getUsuarioUs() %></span>
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
            <h2>Listado de Clientes</h2>
            <%                
                List<Cliente> listaClientes = (List<Cliente>) request.getAttribute("listaClientes");
                if (listaClientes != null && !listaClientes.isEmpty()) {
            %>
                <table id="table_id" class="display">
                    <thead>
                        <tr>
                            <th>CUIL</th>
                            <th>DNI</th>
                            <th>Nombre</th>
                            <th>Apellido</th>
                            <th>Sexo</th>
                            <th>Nacionalidad</th>
                            <th>Fecha Nacimiento</th>
                            <th>Dirección</th>
                            <th>Localidad</th>
                            <th>Correo</th>
                            <th>Teléfono</th>
                            <th>Estado</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%                             
                            for (Cliente cliente : listaClientes) {
                        %>
                            <tr>
                                <td><%= cliente.getCuil() %></td>
                                <td><%= cliente.getDni() %></td>
                                <td><%= cliente.getNombre() %></td>
                                <td><%= cliente.getApellido() %></td>
                                <td><%= cliente.getId_sexo() %></td>
                                <td><%= cliente.getId_nacionalidad() %></td>
                                <td><%= cliente.getFechaNacimiento() %></td>
                                <td><%= cliente.getDireccion() %></td>
                                <td><%= cliente.getId_localidad() %></td>
                                <td><%= cliente.getCorreo() %></td>
                                <td><%= cliente.getTelefono() %></td>
                                <td><%= cliente.isEstado() ? "Activo" : "Inactivo" %></td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            <% 
                } else {                    
            %>
                <p class="mensaje-sin-cuentas">No se encontraron clientes.</p>
            <% } %>
        </div>
    </div>

    
    <script type="text/javascript">
        $(document).ready(function () {
            $('#table_id').DataTable({
                "language": {
                    "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json"
                }
            });
        });
    </script>

    <script src="JS/MenuAdm.js"></script>
</body>
</html>
