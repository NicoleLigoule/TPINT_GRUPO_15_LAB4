<%@ page import="daoImpl.ClienteCuentaDTO" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Baja de cuenta</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/Css/EliminarCun.css">
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
            <div class="form-card">
                <h2>Baja de Cuentas</h2>
                <br>
                <h3>Datos del socio</h3>
                <!-- RESOLVER USANDO 2 FORM-->
                
                 <form action="servletEliminarCuenta" method="get">
                    <label for="cuil">CUIL Cliente</label>
                    <input type="text" id="cuil" name="cuil" value="${param.cuil}" placeholder="CUIL" required>   

                    <div class="button-group">                    
                        <button type="submit" name="buscarCliente" class="submit-button">Buscar cliente</button>             
                    </div>
<!-- AQUI DEBERIA FINALIZAR PRIMER FORM -->
                    <label for="nombre">Cliente</label>
                    
                    <input type="text" id="nombre" name="nombre" value="${requestScope.nombreApellido}" placeholder="Nombre y Apellido" readonly>
					<label for="cuentasDelCliente">Cuentas del cliente</label>
<select id="cuentasDelCliente" name="cuentasDelCliente">
    <option value="" disabled selected>Seleccione una cuenta a eliminar</option>

<%
    // Obtenemos la lista de cuentas
    ArrayList<ClienteCuentaDTO> listaCuentas = (ArrayList<ClienteCuentaDTO>) request.getAttribute("listaCuentas");

    // Verificamos que la lista no sea nula
    if (listaCuentas != null) {
        for (ClienteCuentaDTO cuenta : listaCuentas) {
            
            int numeroCuenta = cuenta.getNumeroCuenta();  
            String descripcionCuenta = cuenta.getTipoCuenta();

            
            if (numeroCuenta != 0) {
%>
            <option value="<%= numeroCuenta %>"><%= numeroCuenta + " - " + descripcionCuenta %></option>
<%
            }
        }
    }
%>
</select>
                    
				
                    <div class="button-group">
                        <button type="button" class="cancel-button">Volver</button>
                        <button type="submit" class="submit-button">Solicitar baja</button>
                    </div>
                    
                    <div style="text-align: center; font-size: 18px; padding: 10px; margin-top: 20px;" >
        <%
            String mensaje = "";
            if (request.getAttribute("mensaje") != null) {
                mensaje = (String) request.getAttribute("mensaje");
            }
        %>
        <%= mensaje %>
    </div>
                    
                </form>
            </div>
        </div>
    </div>
    

<script src="JS/MenuAdm.js"></script>
</body>
</html>