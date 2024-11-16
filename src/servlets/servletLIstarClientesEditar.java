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

			String cuilBuscado = (String) request.getAttribute("clienteSeleccionado");
			System.out.println(cuilBuscado);
			for (Cliente cliente : listaClientes) {

				if (cliente.getCuil().equals(cuilBuscado)) {
					System.out.println("Lo encontré");

					clienteSe = cliente;
					System.out.println(clienteSe.toString());
					break;
				}
			}
			request.setAttribute("dni",clienteSe.getDni());
			request.setAttribute("cuil",clienteSe.getDni());
			request.setAttribute("nombre",clienteSe.getDni());
			request.setAttribute("apellido",clienteSe.getDni());
			request.setAttribute("genero",clienteSe.getDni());
			request.setAttribute("nacionalidad",clienteSe.getDni());
			request.setAttribute("fecha-nacimiento",clienteSe.getDni());
			request.setAttribute("direccion",clienteSe.getDni());
			request.setAttribute("telefono",clienteSe.getDni());
			request.setAttribute("email",clienteSe.getDni());
			request.setAttribute("localidad",clienteSe.getDni());
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


