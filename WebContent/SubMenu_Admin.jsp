<aside class="sidebar" id="sidebar">
		<ul>
			<li class="menu-item"><a href="#" onclick="toggleSubmenu(event)">Clientes</a>
				<ul class="submenu">
					<li><a href="servletAgregarCliente?Param=1">Agregar Cliente</a></li>
					<li><a href="EliminarCliente.jsp">Baja Cliente</a></li>
					<li><a href="SolicitarClienteEditar.jsp">Editar Cliente</a></li>
					<li><a href="servletListarCliente?Param=1">Listar Cliente</a></li>
				</ul></li>
			<li class="menu-item"><a href="#" onclick="toggleSubmenu(event)">Cuentas</a>
				<ul class="submenu">
					<li><a href="servletAgregarCuenta?Param=1">Agregar Cuenta</a></li>
					<li><a href="EliminarCuenta.jsp">Baja Cuenta</a></li>
					<li><a href="servletEditarCuenta?Param=1">Editar Cuenta</a></li>
					<li><a href="ServletListarCuenta?Param=1">Listar Cuenta</a></li>
					<li><a href="AsignarCuenta.jsp">Asignar Cuenta</a></li>
				</ul></li>
			<li class="menu-item"><a href="ServletAutorizarPrestamo?Param=1" >Autorizacion de Prestamos</a>
				<!-- <ul class="submenu">
                        <li> <a href="#">Transaccion</a></li>
                        <li> <a href="#">Ver Historial</a></li>
                    </ul>
                     --></li>
			<li class="menu-item"><a href="#" onclick="toggleSubmenu(event)">Reportes</a>
				<!-- <ul class="submenu">
                        <li> <a href="#">Reporte de Clientes</a></li>
                        <li> <a href="#">Reporte de Cuentas</a></li>
                    </ul>
                     --></li>
		</ul>
		</aside>