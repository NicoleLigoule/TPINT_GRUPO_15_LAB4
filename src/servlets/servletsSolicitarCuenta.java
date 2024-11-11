package servlets;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entidades.TipoDeCuenta;
import negocio.DDL;
/**
 * Servlet implementation class servletsSolicitarCuenta
 */
@WebServlet("/Admin/servletsSolicitarCuenta")
public class servletsSolicitarCuenta extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("Param") != null) {
            DDL lista = new DDL();
            try {
                ArrayList<TipoDeCuenta> listaSeg = lista.TipoDecuenta();
                if (listaSeg != null && !listaSeg.isEmpty()) {
                    request.setAttribute("listaTCuentas", listaSeg);
                    RequestDispatcher rd = request.getRequestDispatcher("Admin/AgregarCuenta.jsp");
                    rd.forward(request, response);
                } else {
                    response.getWriter().println("No se encontraron tipos de cuenta.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().println("Error al obtener los tipos de cuenta: " + e.getMessage());
            }
        }
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}