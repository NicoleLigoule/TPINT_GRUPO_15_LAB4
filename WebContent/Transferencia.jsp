<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page import="entidades.Usuario"%>
<%@ page import="java.util.List"%>
<%@ page import="entidades.Transferencias"%>
<%@ page import="entidades.Cuenta"%>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("Login.jsp");
        return;
    }

    // Obtener las cuentas desde el atributo de la sesión o desde la base de datos
    List<Cuenta> cuentas = (List<Cuenta>) request.getAttribute("cuentas"); // O el método que uses para pasar las cuentas
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Realizar Transferencia</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/Transferencia.css">
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

        <div class="content">
            <div class="form-card">
                <h2>Realizar Transferencia</h2>
                <p>Completa los datos para realizar una transferencia</p>
                <!-- Cambiar acción a Servlet para procesar la transferencia -->
                <form action="servletTransferencia" method="post">
			    <div class="form-group">
			        <label for="cuentaOrigen">Cuenta Origen:</label>
			        <select id="cuentaOrigen" name="cuentaOrigen" required>
			            <option value="" disabled selected>Seleccione su cuenta</option>
			            <% if (cuentas != null) {
			                for (Cuenta cuenta : cuentas) { %>
			                    <option value="<%= cuenta.getNumeroDeCuentaCu() %>">
			                        Cuenta: <%= cuenta.getNumeroDeCuentaCu() %> - Saldo: <%= cuenta.getSaldoCu() %>
			                    </option>
			            <%  } } %>
			        </select>
			    </div>
			    <div class="form-group">
			        <label for="cbu">Cuenta Destino:</label>
			        <input type="text" id="cbu" name="cbu" required pattern="\d+" placeholder="Cuenta Destino">
			    </div>
			    <div class="form-group">
			        <label for="monto">Monto:</label>
			        <input type="text" id="monto" name="monto" required pattern="\d+(\.\d{1,2})?" placeholder="Monto (ejemplo: 1000.50)">
			    </div>
			    <div class="form-group">
			        <label for="detalle">Detalle:</label>
			        <input type="text" id="detalle" name="detalle" placeholder="Detalle de la transferencia">
			    </div>
			    <button type="submit" class="btn" name="RealizarBtn">Siguiente</button>
			    
			    <div style="margin-top: 30px;"></div>
                <button type="button" class="submit-button" onclick="location='/TPINT_GRUPO_15_LAB4/servletInformacionCliente'">Volver al Menú Principal</button>
			</form>


             
		</script>
		                
            </div>
        </div>
        		
    </div>

    <script src="JS/MenuAdm.js"></script>
    <script>
        function toggleSidebar() {
            const sidebar = document.getElementById('sidebar');
            sidebar.classList.toggle('active');
        }

        function toggleSubmenu(event) {
            const menuItem = event.target.parentElement;
            menuItem.classList.toggle('active');
        }
    </script>
</body>
</html>