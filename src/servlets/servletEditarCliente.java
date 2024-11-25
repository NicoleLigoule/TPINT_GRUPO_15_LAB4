package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoImpl.ClienteDaoImpl;
import daoImpl.LocalidadDaoImpl;
import entidades.Localidad;
import entidades.Cliente;
import entidades.Nacionalidad;
import entidades.Provincia;
import entidades.Sexo;
import negocio.DDL;
import negocio.NegocioClientes;

/**
 * Servlet implementation class servletEditarCliente
 */
@WebServlet("/servletEditarCliente")
public class servletEditarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public servletEditarCliente() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cliente cliente = null;
		if (request.getParameter("cuil") != null) {
			ClienteDaoImpl clienteDao = new ClienteDaoImpl();
			cliente = clienteDao.obtenerUnoPorCuil(request.getParameter("cuil"));
			
			
		}
		else {
			System.out.print("ERROR AL TRAER CLIENTE");
			return;
		}
		
		DDL lista = new DDL();
		ArrayList<Nacionalidad> listaNacionalidades = lista.Nacionalidad();
		if (listaNacionalidades != null && !listaNacionalidades.isEmpty()) {
			request.setAttribute("listaNacionalidad", listaNacionalidades);
		} else {
			response.getWriter().println("No se encontraron nacionalidades.");
		}

		ArrayList<Sexo> listaSexos = lista.Sexo();
		if (listaSexos != null && !listaSexos.isEmpty()) {
			request.setAttribute("listaSexo", listaSexos);
		} else {
			response.getWriter().println("No se encontraron sexos.");
		}

		ArrayList<Provincia> listaProvincias = lista.Provincia();
		if (listaProvincias != null && !listaProvincias.isEmpty()) {
			request.setAttribute("listaProvincias", listaProvincias);
		} else {
			response.getWriter().println("No se encontraron provincias.");
		}
		
		LocalidadDaoImpl daoLoc = new LocalidadDaoImpl();
		int idProvincia = daoLoc.obtenerUna(cliente.getId_localidad()).getId_provincia();
		cliente.setId_provincia(idProvincia);
		
		ArrayList<Localidad> listaLocalidad = lista.Localidad(idProvincia);
		if (listaLocalidad != null && !listaLocalidad.isEmpty()) {
			request.setAttribute("listaLocalidad", listaLocalidad);
		} else {
			response.getWriter().println("No se encontraron Localidad.");
		}
		request.setAttribute("clienteSeleccionado", cliente);
		request.getRequestDispatcher("EditarCliente.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {

		if(request.getParameter("agregarBtn") != null) {
			//submit
			Cliente cli = new Cliente();

			cli.setCuil(request.getParameter("cuil"));
			cli.setDni(Integer.parseInt(request.getParameter("dni")));
			cli.setNombre(request.getParameter("nombre"));
			cli.setApellido(request.getParameter("apellido"));
			cli.setId_sexo(Integer.parseInt(request.getParameter("genero")));
			cli.setId_nacionalidad(request.getParameter("nacionalidad"));
			cli.setFechaNacimiento(LocalDate.parse(request.getParameter("fecha-nacimiento"))); 
			cli.setDireccion(request.getParameter("direccion"));
			cli.setId_localidad(Integer.parseInt(request.getParameter("localidad")));
			cli.setId_provincia(Integer.parseInt(request.getParameter("provincia")));
			cli.setCorreo(request.getParameter("email"));
			cli.setTelefono(request.getParameter("telefono"));
			cli.setEstado(Boolean.parseBoolean(request.getParameter("estado"))); 
			
			NegocioClientes negCli = new NegocioClientes();
			boolean inserted = negCli.editarCliente(cli);
			if(inserted) {
				response.getWriter().println("CLIENTE MODIFICADO CON EXITO");
				request.getRequestDispatcher("SolicitarClienteEditar.jsp").forward(request, response);
			}else {
				response.getWriter().println("ERROR AL MODIFICAR CLIENTE");
			}
			
		}else if(request.getParameter("provincia") != null) {
			//refresh ddl
			
			Cliente cli = new Cliente();

			cli.setCuil(request.getParameter("cuil"));
			cli.setDni(Integer.parseInt(request.getParameter("dni")));
			cli.setNombre(request.getParameter("nombre"));
			cli.setApellido(request.getParameter("apellido"));
			cli.setId_sexo(Integer.parseInt(request.getParameter("genero")));
			cli.setId_nacionalidad(request.getParameter("nacionalidad"));
			cli.setFechaNacimiento(LocalDate.parse(request.getParameter("fecha-nacimiento"))); 
			cli.setDireccion(request.getParameter("direccion"));
			cli.setId_localidad(Integer.parseInt(request.getParameter("localidad")));
			cli.setId_provincia(Integer.parseInt(request.getParameter("provincia")));
			cli.setCorreo(request.getParameter("email"));
			cli.setTelefono(request.getParameter("telefono"));
			cli.setEstado(Boolean.parseBoolean(request.getParameter("estado"))); 
			
			DDL lista = new DDL();
			
			ArrayList<Nacionalidad> listaNacionalidades = lista.Nacionalidad();
			if (listaNacionalidades != null && !listaNacionalidades.isEmpty()) {
				request.setAttribute("listaNacionalidad", listaNacionalidades);
			} else {
				response.getWriter().println("No se encontraron nacionalidades.");
			}

			ArrayList<Sexo> listaSexos = lista.Sexo();
			if (listaSexos != null && !listaSexos.isEmpty()) {
				request.setAttribute("listaSexo", listaSexos);
			} else {
				response.getWriter().println("No se encontraron sexos.");
			}

			ArrayList<Provincia> listaProvincias = lista.Provincia();
			if (listaProvincias != null && !listaProvincias.isEmpty()) {
				request.setAttribute("listaProvincias", listaProvincias);
			} else {
				response.getWriter().println("No se encontraron provincias.");
			}
			
			
			
			int idProvincia = Integer.parseInt(request.getParameter("provincia"));
			ArrayList<Localidad> listaLocalidad = lista.Localidad(idProvincia);
			
			if (listaLocalidad != null && !listaLocalidad.isEmpty()) {
				request.setAttribute("listaLocalidad", listaLocalidad);
				response.getWriter().println("Localidades actualizadas");
				request.setAttribute("clienteSeleccionado", cli);
				request.getRequestDispatcher("EditarCliente.jsp").forward(request, response);
			} else {
				response.getWriter().println("No se encontraron Localidad.");
			}
		}

	}
}
