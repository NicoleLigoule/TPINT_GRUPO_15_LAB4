<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="entidades.Usuario"%>
<%@ page import="entidades.Movimiento"%>
<%@ page import="entidades.Cuenta"%>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("Login.jsp");
        return;
    }

    List<Movimiento> listaMovimientos = (List<Movimiento>) request.getAttribute("listaMovimientos");
    List<Cuenta> cuentas = (List<Cuenta>) request.getAttribute("cuentas");
    Integer totalPaginas = (Integer) request.getAttribute("totalPaginas");
    Integer paginaActual = (Integer) request.getAttribute("paginaActual");

    if (totalPaginas == null || paginaActual == null) {
        totalPaginas = 0;  // Valor predeterminado
        paginaActual = 1;  // Valor predeterminado
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Movimientos</title>
    <link href="${pageContext.request.contextPath}/Css/Movimientos.css" type="text/css" rel="stylesheet"/>
   
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <script>
        $(document).ready(function() {
            function cargarPagina(page) {
                var cuentaSeleccionada = $('#cuentaSeleccionada').val();
                var tipoMovimiento = $('#tipoMovimiento').val();

                $.ajax({
                    url: 'servletHistorialMovimiento', 
                    type: 'GET',
                    data: {
                        page: page,
                        cuentaSeleccionada: cuentaSeleccionada,
                        tipoMovimiento: tipoMovimiento
                    },
                    success: function(response) {
                        $('#movimientos').html($(response).find('#movimientos').html());
                        $('#paginacion').html($(response).find('#paginacion').html());
                    },
                    error: function() {
                        alert('Hubo un error al cargar los movimientos.');
                    }
                });
            }

            $(document).on('click', '.pagina', function(e) {
                e.preventDefault();
                var page = $(this).data('page');
                cargarPagina(page);
            });

            $('form').on('submit', function(e) {
                e.preventDefault();
                cargarPagina(1); 
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
        <span class="username"><%= usuario.getUsuarioUs() %></span>
    </nav>

    <div class="main-container">
        <aside class="sidebar" id="sidebar">
            <ul>
			<li class="menu-item"><a href="servletTransferencia?Param=1">Transferencias</a></li>
			<li class="menu-item"><a href="ServletSolicitarPrestamo?Param=1">Solicitudes de prestamos</a></li>
			<li class="menu-item"><a href="ServletPagoPrestamo?Param=1">Pago de prestamos</a></li>
			<li class="menu-item"><a href="servletHistorialMovimiento?Param=1">Historial de movimientos</a></li>
            </ul>
        </aside>


        <section class="content">
            <div class="movements-list">
                <h3>Movimientos</h3>

                <form method="get" action="servletHistorialMovimiento">
                    <label for="cuentaSeleccionada">Seleccionar cuenta:</label>
                    <select name="cuentaSeleccionada" id="cuentaSeleccionada">
                        <option value="" disabled selected>Seleccione su cuenta</option>
                        <% 
                            if (cuentas != null) {
                                for (Cuenta cuenta : cuentas) {
                        %>
                            <option value="<%= cuenta.getNumeroDeCuentaCu() %>">
                                Cuenta: <%= cuenta.getNumeroDeCuentaCu() %> - Saldo: <%= cuenta.getSaldoCu() %>
                            </option>
                        <% 
                                }
                            }
                        %>
                    </select>
                    
                    <label for="tipoMovimiento">Seleccionar tipo de movimiento:</label>
                    <select name="tipoMovimiento" id="tipoMovimiento">
                        <option value="">Todos</option>
                        <option value="Alta de cuenta">Alta de cuenta</option>
                        <option value="Transferencia">Transferencia</option>
                        <option value="Alta de prestamo">Alta de prestamo</option>
                        <option value="Pago de prestamo">Pago de prestamo</option>
                    </select>
                    <button type="submit">Ver movimientos</button>
                    <button type="submit" onclick="location='/TPINT_GRUPO_15_LAB4/servletInformacionCliente'">Volver al Menu Principal</button>
                </form>
			<div id="paginacion">
                    <div class="card" style="padding: 10px; max-width: 400px; margin: 0 auto; text-align: center;">
                        <% if (paginaActual > 1) { %>
                            <button type="button" class="pagina" data-page="<%= paginaActual - 1 %>">Página anterior</button>
                        <% } %>
                        
                        <span><%= paginaActual %> de <%= totalPaginas %></span>
                        
                        <% if (paginaActual < totalPaginas) { %>
                            <button type="button" class="pagina" data-page="<%= paginaActual + 1 %>">Página siguiente</button>
                        <% } %>
                    </div>
                </div>
                <div id="movimientos">
                    <% 
                        if (listaMovimientos != null && !listaMovimientos.isEmpty()) {
                    %>
                    <div class="movement-list">
                        <% for (Movimiento movimiento : listaMovimientos) { %>
                            <div class="movement-item">
                                <h4>ID Movimiento: <%= movimiento.getIdMovimientoMov() %></h4>
                                <p><strong>Fecha:</strong> <%= movimiento.getFechaMovimientoMov() %></p>
                                <p><strong>Detalle:</strong> <%= movimiento.getDetalleMov() %></p>
                                <p><strong>Importe:</strong> <%= movimiento.getImporteMov() %></p>
                                <p><strong>Tipo Movimiento:</strong> <%= movimiento.getDescripcionTipoMov() %></p> 
                            </div>
                        <% } %>
                    </div>
                    <% 
                        } else {
                    %>
                    <p>No hay movimientos disponibles para la cuenta seleccionada.</p>
                    <% } %>
                </div>

                
            </div>
        </section>
    </div>
    <script src="JS/MenuAdm.js"></script>
</body>
</html>
