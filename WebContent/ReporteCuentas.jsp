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
    <title>Listar Cuentas</title>
    
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
            
            <h2>Reporte Alta de Cuentas</h2>
            

            <!-- Formulario -->
            <div class="form-container">
                <form action="servletReporteCuentas" method="POST" >
                    <% ArrayList<TipoDeCuenta> listaTipos = (ArrayList<TipoDeCuenta>) request.getAttribute("listaTipos"); %>
                    <label for="tipo">Tipo de cuenta</label>
                    <select id="tipo" name="tipo" required>
                        <option value="" disabled selected>Seleccione tipo de cuenta</option>
                        <% if (listaTipos != null) { 
                            for (TipoDeCuenta tipo : listaTipos) { %>
                        <option value="<%= tipo.getIdTipoDeCuenta() %>"
                            <%= Integer.toString(tipo.getIdTipoDeCuenta()).equals(request.getParameter("tipo")) ? "selected" : "" %> >
                            <%= tipo.getNombreTipo() %>
                        </option>
                        <% } } %>
                    </select>

                    <label>Desde</label>
                    <input type="text" id="fecha-desde" name="fecha-desde" placeholder="YYYY/MM/DD" 
                    value="<%=request.getParameter("fecha-desde") != null ? request.getParameter("fecha-desde") : ""%>" required>

                    <label>Hasta</label>
                    <input type="text" id="fecha-hasta" name="fecha-hasta" placeholder="YYYY/MM/DD" 
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
        // Obtenemos la lista de cuentas desde el request
        List<Cuenta> listaCuenta = (List<Cuenta>) request.getAttribute("listaCuenta");
    	Integer cantidadResultados = (Integer) request.getAttribute("cantidadResultados");

        // Obtenemos los parámetros 
        String fechaDesde = request.getParameter("fecha-desde");
        String fechaHasta = request.getParameter("fecha-hasta");
        String tipoCuenta = request.getParameter("tipo");
        

        // Verificamos si los filtros fueron enviados
        boolean filtrosAplicados = (fechaDesde != null || fechaHasta != null || tipoCuenta != null);
		
     // Identificar el tipo de cuenta por su ID (esto depende de tu listaTipos)
       String nombreTipoCuenta = null;

    if (tipoCuenta != null && !tipoCuenta.isEmpty()) {
        int idTipoCuenta = Integer.parseInt(tipoCuenta); // Convertimos el parámetro a entero
        if (idTipoCuenta == 1) {
            nombreTipoCuenta = "Caja de Ahorro";
        } else if (idTipoCuenta == 2) {
            nombreTipoCuenta = "Cuenta Corriente";
        } else {
            nombreTipoCuenta = "Tipo Desconocido"; // Para casos no esperados
        }
    }
        
        // Solo mostramos la tabla si hay cuentas
        if (listaCuenta != null && !listaCuenta.isEmpty()) {
    %>
    
    
    		<div class="print-button-container">
                <button class="print-button" onclick="window.print()">
                    <i class="fas fa-print"></i> Imprimir
                </button>
            </div>
            
         <% if(nombreTipoCuenta != null){  %>	
    		<p>Tipo de cuenta seleccionado: <%= nombreTipoCuenta %></p>
   		 <%} %>
            
    	<p>Resultados encontrados: <%= cantidadResultados %> </p>
    	<br>
        <table id="table_id" class="display">
            <thead>
                <tr>
                    <th>Numero de Cuenta</th>
                    <th>CUIL</th>
                    <th>Fecha de Creación</th>                    
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
                    <td><%= cuenta.getCbuCu() %></td>
                    <td><%= cuenta.getSaldoCu() %></td>
                    <td><%= cuenta.isEstadoCu() ? "Activa" : "Inactiva" %></td>
                </tr>
                <% } %>
            </tbody>
        </table>
    <% 
        
        } else if (filtrosAplicados) { 
    %>
        <p class="mensaje-sin-cuentas">No se encontraron Cuentas en ese período.</p>
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
