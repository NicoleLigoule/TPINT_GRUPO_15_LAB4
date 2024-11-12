<%@page import="entidades.Nacionalidad"%>
<%@ page import="entidades.Sexo" %> 
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Alta de Cliente</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/Css/AgregarCli.css">
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
        <aside id="sidebar" class="sidebar">
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
                        <li> <a href="#">Registrar Transacci�n</a></li>
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
                <h2>Agregar Cliente</h2>
                <form action="servletAgregarCliente" method="post">
                    <label for="dni">DNI</label>
                    <input type="text" id="dni" name="dni" placeholder="DNI" required>

                    <label for="cuil">CUIL</label>
                    <input type="text" id="cuil" name="cuil" placeholder="CUIL" required>

                    <label for="nombre">Nombre</label>
                    <input type="text" id="nombre" name="nombre" placeholder="Nombre" required>

                    <label for="apellido">Apellido</label>
                    <input type="text" id="apellido" name="apellido" placeholder="Apellido" required>
                    
				    <% 
						ArrayList<Sexo> listaSexo = null;
                    	if(request.getAttribute("listaSexo") != null)

						{
							listaSexo = (ArrayList<Sexo>) request.getAttribute("listaSexo");
						}
					 %>
                    <label for="genero">G�nero:</label>
		            <select id="genero" name="genero" required>
		            <option value="" disabled selected>Seleccione su g�nero</option>
		            <% 
					        if (listaSexo != null) {
					            for (Sexo sex: listaSexo) { 
					    %>
					                <option value="<%= sex.getId_sexo() %>"><%= sex.getDescripcion() %></option>

					    <% 
					            }
					        }
					    %>
		            </select>
		            
                    <% 
						ArrayList<Nacionalidad> listaSeguros = null;
						if(request.getAttribute("listaNacionalidad")!=null)
						{
							listaSeguros = (ArrayList<Nacionalidad>) request.getAttribute("listaNacionalidad");
						}
					 %>
                    <label for="nacionalidad">Nacionalidad</label>
                    <select id="nacionalidad" name="nacionalidad" required>
                    <option value="" disabled selected>Seleccione su Nacionalidad</option>
<% 
					        if (listaSeguros != null) {
					            for (Nacionalidad nac : listaSeguros) { 
					    %>
					                <option value="<%= nac.getIdNacionalidadNc() %>"><%= nac.getDescripcionNc() %></option>
					    <% 
					            }
					        }
					    %>
                    </select>

                    <label for="fecha-nacimiento">Fecha de Nacimiento</label>
                    <input type="text" id="fecha-nacimiento" name="fecha-nacimiento" placeholder="YYYY/MM/DD" required>

                    <label for="direccion">Direcci�n</label>
                    <input type="text" id="direccion" name="direccion" placeholder="Direcci�n" required>

                    <label for="numero">N�mero</label>
                    <input type="text" id="numero" name="numero" placeholder="N�mero" required>

                  <label for="provincia">Provincia</label>
					<select id="provincia" name="provincia" required>
					    <option value="" disabled selected>Seleccione su Provincia</option>
					    <option value="1">Buenos Aires</option>
					    <option value="2">C�rdoba</option>
					</select>
					
					<label for="localidad">Localidad</label>
					<select id="localidad" name="localidad" required>
					    <option value="" disabled selected>Seleccione su Localidad</option>
					    <option value="1">La Plata</option>
					    <option value="2">Mar del Plata</option>
					</select>


                    <label for="telefono">Tel�fono</label>
                    <input type="tel" id="telefono" name="telefono" placeholder="Tel�fono" required>

                    <label for="email">Correo Electr�nico</label>
                    <input type="email" id="email" name="email" placeholder="Correo Electr�nico" required>

                    <div class="button-group">
                        <button type="button" class="cancel-button">Cancelar</button>
                        <button type="submit" class="submit-button" name="submit-button">Agregar</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

<script src="JS/MenuAdm.js"></script>
</body>
</html>