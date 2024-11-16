package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Cliente;
import entidades.Localidad;
import entidades.Nacionalidad;
import entidades.Provincia;
import entidades.Sexo;
import negocio.DDL;
import negocio.NegocioClientes;

/**
 * Servlet implementation class servletLIstarClientesEditar
 */
@WebServlet("/servletLIstarClientesEditar")
public class servletLIstarClientesEditar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletLIstarClientesEditar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("Param") != null ) {
            DDL lista = new DDL();
            try {
   
                ArrayList<Cliente> listaClientes = lista.ClienteList();
                if (listaClientes != null && !listaClientes.isEmpty()) {
                    request.setAttribute("listaCliente", listaClientes);
                } else {
                    response.getWriter().println("No se encontraron Clientes.");
                }
                RequestDispatcher rd = request.getRequestDispatcher("SolicitarClienteEditar.jsp");
                rd.forward(request, response);

            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().println("Error al obtener los datos: " + e.getMessage());
            }
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 

		if (request.getParameter("clienteSeleccionado") != null) {
	           DDL lista = new DDL();
	            
	           
	           
	           
	           
	           
	           
	           
	           
	           
	           try {      
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
					
                    
	                
               ArrayList<Cliente> listaClientes = lista.ClienteList();
               
		       Cliente clienteSe = new Cliente();
			System.out.println("Estoy dentro");
	        String clienteSeleccionado = request.getParameter("clienteSeleccionado");

			System.out.println(clienteSeleccionado);
			for (Cliente cliente : listaClientes) {

				if (cliente.getCuil().equals(clienteSeleccionado)) {
					System.out.println("Lo encontré");

					clienteSe = cliente;
					System.out.println(clienteSe.toString());
					break;
				}
			}
			
			int h=0;
			NegocioClientes neg= new NegocioClientes();
			 h=neg.obtenProv(String.valueOf(clienteSe.getId_localidad()));
			 int idProvincia =h;
            if(h != 0) {
            	
            	   // Guardar 'h' como atributo del request
                
            ArrayList<Localidad> listaLocalidad = lista.Localidad(idProvincia);
            if (listaLocalidad != null && !listaLocalidad.isEmpty()) {
                request.setAttribute("listaLocalidad", listaLocalidad);
            } else {
                response.getWriter().println("No se encontraron Localidad.");
            }}
            ArrayList<Provincia> listaProvincias = lista.Provincia();
            if (listaProvincias != null && !listaProvincias.isEmpty()) {
                request.setAttribute("listaProvincias", listaProvincias);
            } else {
                response.getWriter().println("No se encontraron provincias.");
            }
			
			request.setAttribute("dni",clienteSe.getDni());
			request.setAttribute("cuil",clienteSe.getCuil() );
			request.setAttribute("nombre",clienteSe.getNombre());
			request.setAttribute("apellido",clienteSe.getApellido());
			request.setAttribute("genero",clienteSe.getId_sexo());
			request.setAttribute("nacionalidad",clienteSe.getId_nacionalidad());
			request.setAttribute("fecha-nacimiento",clienteSe.getFechaNacimiento());
			request.setAttribute("direccion",clienteSe.getDireccion());
			request.setAttribute("telefono",clienteSe.getTelefono());
			request.setAttribute("email",clienteSe.getCorreo());
			request.setAttribute("localidad",clienteSe.getId_localidad());
			request.setAttribute("idProvincia", idProvincia);


 
			RequestDispatcher rd = request.getRequestDispatcher("EditarCliente.jsp");
            rd.forward(request, response);
    
            
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error al obtener los datos: " + e.getMessage());
        }
		} else {
			System.out.println("no entre culiado");
		}
		}
	}


