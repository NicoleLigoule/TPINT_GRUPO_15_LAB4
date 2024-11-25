package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Prestamo;
import negocio.DDL;

/**
 * Servlet implementation class ServletAutorizarPrestamo
 */
@WebServlet("/ServletAutorizarPrestamo")
public class ServletAutorizarPrestamo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAutorizarPrestamo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("Param") != null) {
            DDL ddlPrestamo = new DDL();
            try {
            	System.out.print("entra a lista");
                List<Prestamo> listaPrestamo = ddlPrestamo.ListarPrestamosAprobar();

                if (listaPrestamo != null && !listaPrestamo.isEmpty()) {
                    System.out.println("Contenido de listaPrestamo:");
                    for (Object prestamo : listaPrestamo) {
                        System.out.println(prestamo);
                    }
                    request.setAttribute("listaPrestamo", listaPrestamo); 

                } else {
                    request.setAttribute("Mensaje", "No se encontraron Prestamos.");
                }

                
                RequestDispatcher rd = request.getRequestDispatcher("AutorizarPrestamo.jsp");
                rd.forward(request, response);

            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().println("Error al obtener la lista de prestamos: " + e.getMessage());
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
