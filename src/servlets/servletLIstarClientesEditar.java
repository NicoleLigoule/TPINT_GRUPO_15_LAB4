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
import negocio.DDL;

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
               ArrayList<Cliente> listaClientes = lista.ClienteList();
		       Cliente clienteSe = new Cliente();
			System.out.println("Estoy dentro");
	        String clienteSeleccionado = request.getParameter("clienteSeleccionado");

			System.out.println(clienteSeleccionado);
			for (Cliente cliente : listaClientes) {

				if (cliente.getCuil().equals(clienteSeleccionado)) {
					System.out.println("Lo encontr�");

					clienteSe = cliente;
					System.out.println(clienteSe.toString());
					break;
				}
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
			///request.setAttribute("email",clienteSe.getDni());
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


