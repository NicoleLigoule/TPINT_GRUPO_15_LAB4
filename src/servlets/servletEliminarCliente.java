package servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocio.NegocioClientes;

@WebServlet("/servletEliminarCliente")
public class servletEliminarCliente extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public servletEliminarCliente() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Llegamos al servlet para eliminar cliente.");

        if (request.getParameter("btnBaja") != null) {
            String cuilCliente = request.getParameter("cuil");

            if (cuilCliente != null && !cuilCliente.isEmpty()) {
                NegocioClientes negocioClientes = new NegocioClientes();
                boolean exito = negocioClientes.eliminarCliente(cuilCliente);

                if (exito) {
                    System.out.println("Cliente eliminado con éxito.");
                    request.setAttribute("mensaje", "Cliente eliminado con exito.");
                } else {
                    System.out.println("Error al eliminar el cliente.");
                    request.setAttribute("mensaje", "Error al intentar eliminar el cliente.");
                }

                forwardToPage(request, response, "EliminarCliente.jsp");
            } else {
                System.out.println("CUIL inválido proporcionado.");
                request.setAttribute("mensaje", "CUIL invalido proporcionado.");
                forwardToPage(request, response, "EliminarCliente.jsp");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void forwardToPage(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher(page);
        rd.forward(request, response);
    }
}
