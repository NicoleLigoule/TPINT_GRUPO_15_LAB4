<%@ page import="java.util.List" %>
<%@ page import="entidades.Cliente" %>


<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Listar Clientes</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Css/EditarCli.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>

    <style>
        /* Agregar estilo a la tabla */
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
            font-size: 14px;
        }

        /* Estilo para las celdas */
        th, td {
            padding: 12px;
            text-align: left;
            border: 1px solid #ddd; /* Borde gris claro */
        }

        /* Estilo para los encabezados */
        th {
            background-color: #f2f2f2;
            color: #333;
            font-weight: bold;
        }

        /* Estilo para las filas al pasar el mouse */
        tr:hover {
            background-color: #f5f5f5;
        }

        /* Estilo para los botones (si los usas) */
        button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            cursor: pointer;
        }

        button:hover {
            background-color: #45a049;
        }

        /* Estilo de la tabla responsiva (para pantallas pequeñas) */
        @media (max-width: 768px) {
            table {
                width: 100%;
                font-size: 12px;
            }
        }
    </style>
</head>
<body>
    <h2>Listado de Clientes</h2>

    <%
        // Obtén la lista de clientes del request
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
                    // Recorre la lista de clientes y muestra cada uno
                    for (Cliente cliente : listaClientes) {
                %>
                    <tr>
                        <td><%= cliente.getCuil() %></td>
                        <td><%= cliente.getDni() %></td>
                        <td><%= cliente.getNombre() %></td>
                        <td><%= cliente.getApellido() %></td>
                        <td><%= cliente.getId_sexo() %></td> <!-- Aquí puedes adaptar este valor si quieres mostrar el nombre del sexo -->
                        <td><%= cliente.getId_nacionalidad() %></td>
                        <td><%= cliente.getFechaNacimiento() %></td>
                        <td><%= cliente.getDireccion() %></td>
                        <td><%= cliente.getId_localidad() %></td> <!-- Puedes adaptarlo para mostrar la descripción de la localidad -->
                        <td><%= cliente.getCorreo() %></td>
                        <td><%= cliente.getTelefono() %></td>
                        <td><%= cliente.isEstado() ? "Activo" : "Inactivo" %></td> <!-- Muestra "Activo" o "Inactivo" según el estado -->
                    </tr>
                <% } %>
            </tbody>
        </table>
    <% 
        } else {
            out.println("<p>No se encontraron clientes.</p>");
        }
    %>

    <script type="text/javascript">
        $(document).ready(function () {
            $('#table_id').DataTable({
                "language": {
                    "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json"
                }
            });
        });
    </script>
</body>
</html>
