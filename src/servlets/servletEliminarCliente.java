package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.NegocioClientes;

/**
 * Servlet implementation class servletEliminarCliente
 */
@WebServlet("/servletEliminarCliente")
public class servletEliminarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletEliminarCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.print("LLEGAMOOOOOS AL SERVLET");

        if (request.getParameter("btnBaja") != null) {
            String cuilCliente = request.getParameter("cuil");

            // Verifica CUIL válido
            if (cuilCliente != null && !cuilCliente.isEmpty()) {
                NegocioClientes negocioClientes = new NegocioClientes();
                boolean exito = negocioClientes.eliminarCliente(cuilCliente);

                if (exito) {
                    System.out.println("Cliente eliminado con éxito.");
                    // reenviamos con un mensaje de éxito
                    request.setAttribute("mensaje", "Cliente eliminado con éxito.");
                    
                    
                    
                    RequestDispatcher rd = request.getRequestDispatcher("EliminarCliente.jsp");
                    rd.forward(request, response);
                } else {
                    System.out.println("Error al eliminar el cliente.");
                    // reenviamos con un mensaje de error
                    request.setAttribute("mensaje", "Error al intentar eliminar el cliente.");
                    
                    
                    RequestDispatcher rd = request.getRequestDispatcher("EliminarCliente.jsp");
                    rd.forward(request, response);
                }

            }
            
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
