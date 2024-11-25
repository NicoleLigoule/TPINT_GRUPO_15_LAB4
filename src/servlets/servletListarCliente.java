package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Cliente;
import negocio.NegocioClientes;

/**
 * Servlet implementation class servletListarCliente
 */
@WebServlet("/servletListarCliente")
public class servletListarCliente extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletListarCliente() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.print("LLEGA AL SERVLET");
        if (request.getParameter("Param") != null) {
            NegocioClientes negClie = new NegocioClientes();
            try {
            	System.out.print("entra a lista");
                List<Cliente> listaClientes = negClie.obtenerClientesTodos();

                if (listaClientes != null && !listaClientes.isEmpty()) {
                    System.out.println("Contenido de listaClientes:");
                    for (Object cliente : listaClientes) {
                        System.out.println(cliente);
                    }
                    request.setAttribute("listaClientes", listaClientes); // Pasa la lista de clientes con este nombre

                } else {
                    request.setAttribute("Mensaje", "No se encontraron clientes.");
                }

                // Redirigir a la vista que va a mostrar los clientes
                RequestDispatcher rd = request.getRequestDispatcher("ListarCliente.jsp");
                rd.forward(request, response);

            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().println("Error al obtener la lista de clientes: " + e.getMessage());
            }
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // No es necesario procesar nada en POST para esta operación de listado
    }
}
