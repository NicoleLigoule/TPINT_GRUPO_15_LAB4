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
	%>
	
	<!DOCTYPE html>
	<html lang="es">
	<head>
	    <meta charset="UTF-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <title>Movimientos</title>
	   
	    <link href="${pageContext.request.contextPath}/Css/Movimientos.css" type="text/css" rel="stylesheet"/>
	
	 
	
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
	</head>
	<body>
	    <nav class="navbar">
	        
	        <a href="${pageContext.request.contextPath}/Login.jsp">
	            <img src="${pageContext.request.contextPath}/img/png_logo.png" class="img_logo" alt="Logo UTN">
	        </a>
	        <span class="username"><%= usuario.getUsuarioUs() %></span>
	    </nav>
	
	    <div class="main-container">
	        <aside class="sidebar" id="sidebar">
	            <ul>
	                <li><a href="#">Transferencias</a></li>
	                <li><a href="#">Solicitudes de préstamos</a></li>
	                <li><a href="#">Pago de préstamos</a></li>
	                <li><a href="#">Información personal</a></li>
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
				  <button type="button" class="submit-button" onclick="location='/TPINT_GRUPO_15_LAB4/servletInformacionCliente'">Volver al Menú Principal</button>
				</form>
	
	
	               
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
		
	          
	            <div class="pagination">
	                <span>1</span>
	                <a href="#">2</a>
	                <a href="#">3</a>
	                <a href="#">Siguiente ></a>
	            </div>
	           
	        </section>
			  
	</form>
	    </div>
	
	    <script src="JS/MenuAdm.js"></script>
	</body>
	</html>