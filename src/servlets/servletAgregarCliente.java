package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Nacionalidad;
import entidades.Sexo;
import negocio.DDL;

/**
 * Servlet implementation class servletAgregarCliente
 */
@WebServlet("/servletAgregarCliente")
public class servletAgregarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletAgregarCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("Param") != null) {
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

                RequestDispatcher rd = request.getRequestDispatcher("AgregarCliente.jsp");
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
