<%@page import="entidades.Cliente"%>
<%@page import="entidades.Provincia"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="entidades.Cuenta"%>
<%@ page import="entidades.TipoDeCuenta"%>
<%@ page import="entidades.Usuario"%>
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
    <link href="${pageContext.request.contextPath}/Css/EditarCun.css" type="text/css" rel="stylesheet" />
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Listar Clientes por edades</title>
    
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css" />
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">

    <style>
        /* Estilos para la tabla y el formulario */
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

        .print-button-container {
            margin-bottom: 15px;
        }

        .print-button {
            background-color: #d6a6d6;
            color: white;
            border: none;
            padding: 10px 15px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }

        .print-button i {
            margin-right: 5px;
        }

        /* Nuevo estilo para alinear formulario y tabla */
        .content {
            display: flex;
            flex-direction: column;
            gap: 20px;
        }

        .form-container {
            margin-bottom: 20px;
            border: 1px solid #ccc;
            padding: 20px;
            border-radius: 5px;
        }

        .button-group {
            margin-top: 10px;
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
        <span class="username"><%= usuario.getUsuarioUs() != null ? usuario.getUsuarioUs() : "Usuario no encontrado" %></span>
    </nav>

    <div class="main-container">
        <jsp:include page="SubMenu_Admin.jsp" />
        <div class="content">
            
            <h2>Reporte de Clientes por Edades</h2>
            

            <!-- Formulario -->
            <div class="form-container">
                <form action="servletReporteEdades" method="get" >                    
                    <label for="desde">Desde</label>
                    <input type="text" id="fecha-desde"  name="fecha-desde" placeholder="Ingrese edad"  style="width: 150px; padding: 5px;"
                    value="<%=request.getParameter("fecha-desde") != null ? request.getParameter("fecha-desde") : ""%>" required>
					<br>
					<br>
                    <label for="hasta">Hasta </label>
                    <input type="text" id="fecha-hasta" name="fecha-hasta" placeholder="Ingrese edad"  style="width: 150px; padding: 5px;"
                    value="<%=request.getParameter("fecha-hasta") != null ? request.getParameter("fecha-hasta") : ""%>" required>

                    

                    <div class="button-group">
                        <button type="button" class="cancel-button" onclick="window.history.back();">Volver</button>
                        <button type="submit" class="submit-button" name="mostrarBtn">Mostrar</button>
                    </div>
                </form>
            </div>

            <!-- Tabla -->
            <div class="mensaje-error">
            
             <%
                String mensaje = (String) request.getAttribute("Mensaje");
                if (mensaje != null) {
            %>
                <p class="mensaje-sin-cuentas"><%= mensaje %></p>
            <% 
                }
            %>
            
            
    <%
        // Obtenemos la lista de cuentas desde el request
        List<Cliente> clientesPorRango = (List<Cliente>) request.getAttribute("clientesPorEdades");
    	Integer cantidadResultados = (Integer) request.getAttribute("cantidadResultados");

    	 boolean hayResultados = (cantidadResultados != null && cantidadResultados > 0);        

                 
        
        // Solo mostramos la tabla si hay clientes
        if (hayResultados) {
        
    %>
    
    
    		<div class="print-button-container">
                <button class="print-button" onclick="window.print()">
                    <i class="fas fa-print"></i> Imprimir
                </button>
            </div>
            
        
            
    	<p>Clientes activos que coinciden con el rango de edad: <%= cantidadResultados %> </p>
    	<br>
        <table id="table_id" class="display">
            <thead>
                <tr>
                    <th>CUIL</th>
                    <th>DNI</th>
                    <th>Nombre</th>                    
                    <th>Apellido</th>
                    <th>Tel�fono</th>
                    <th>Correo</th> 
                    <th>Localidad</th>
                    <th>Provincia</th>
                    
                </tr>
            </thead>
            <tbody>
                <% for (Cliente cli : clientesPorRango) { %>
                 <tr>
            		<td><%= cli.getCuil() %></td>
            		<td><%= cli.getDni() %></td>
            		<td><%= cli.getNombre() %></td>
            		<td><%= cli.getApellido() %></td>
            		<td><%= cli.getTelefono() %></td>
            		<td><%= cli.getCorreo() %></td>
            		<td><%= cli.getDescripcion_localidad() %></td>
					<td><%= cli.getDescripcion_provincia() %></td>        
       			 </tr>
                <% } %>
            </tbody>
        </table>
    <% 
        
        } 
    %>
      
    <br>
</div>

        </div>
    </div>

    <script src="JS/MenuAdm.js"></script>
    
    
</body>
</html>
